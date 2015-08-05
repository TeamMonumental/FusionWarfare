package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRFConverter extends TileEntityBase implements IEnergyHandler {
	
	public EnergyStorage storage = new EnergyStorage(500000);
	
	public boolean outputFusion = true;
	
	public TileEntityRFConverter() {
		maxEnergy = 5000;
	}
	
	@Override
	public void updateEntity() {
		
		if (!worldObj.isRemote) {
			
			if (outputFusion) {
			
				if (storage.getEnergyStored() >= 1000 && EnergyUtil.canAddEnergy(this, 5)) {
				
					storage.extractEnergy(1000, false);
				
					EnergyUtil.addEnergy(this, 5);
				}			
			} 
		
			else {
			
				if ((storage.getMaxEnergyStored() - storage.getEnergyStored()) >= 1000 && EnergyUtil.canSubtractEnergy(this, 5)) {
				
					storage.receiveEnergy(1000, false);
				
					EnergyUtil.subtractEnergy(this, 5);
				}
			}
		}		
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return outputFusion ? storage.receiveEnergy(maxReceive, simulate) : 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return outputFusion ? 0 : storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getMaxEnergyStored();
	}
	
	@SideOnly(Side.CLIENT)
	public int getCurrentRFScaled(int i) {
		return storage.getEnergyStored() * i / storage.getMaxEnergyStored();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		nbt.setBoolean("outFusion", outputFusion);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		outputFusion = nbt.getBoolean("outFusion");
	}
	
	//--------------------------------------------------------------------
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}

	@Override
	public EnumIO getIOType() {
		return outputFusion ? EnumIO.OUTPUT : EnumIO.INPUT;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
	
	//-----------------------------------------------------------
	
	@Override
	public Packet getDescriptionPacket() {
		
		NBTTagCompound syncData = new NBTTagCompound();
	
		writeToNBT(syncData);
		
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		
		readFromNBT(pkt.func_148857_g());
	}
}

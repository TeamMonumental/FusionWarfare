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
	
	public EnergyStorage storage = new EnergyStorage(50000);
	
	public boolean outputFusion = true;
	
	public TileEntityRFConverter() {
		maxEnergy = 5000;
	}
	
	@Override
	public void updateEntity() {
		
		System.out.println(energy);
		
		if (!worldObj.isRemote) {
			
			if (outputFusion) {
			
				if (storage.getEnergyStored() >= 10 && EnergyUtil.canAddEnergy(this, 1)) {
				
					storage.extractEnergy(10, false);
				
					EnergyUtil.addEnergy(this, 1);
				}			
			} 
		
			else {
			
				if ((storage.getMaxEnergyStored() - storage.getEnergyStored()) >= 10 && EnergyUtil.canSubtractEnergy(this, 1)) {
				
					storage.receiveEnergy(10, false);
				
					EnergyUtil.subtractEnergy(this, 1);
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
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return storage.extractEnergy(maxExtract, simulate);
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
	
	/*public void update() {
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}*/
		
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
		return EnumIO.OUTPUT;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
	
	//-----------------------------------------------------------
	
	@Override
	public Packet getDescriptionPacket() {
		
		NBTTagCompound syncData = new NBTTagCompound();
	
		syncData.setInteger("FE", energy);
		syncData.setInteger("progress", progress);
		
		storage.readFromNBT(syncData);
		
		syncData.setBoolean("outFusion", outputFusion);
		
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		
		energy = pkt.func_148857_g().getInteger("FE");
		progress = pkt.func_148857_g().getInteger("progress");
		
		storage.writeToNBT(pkt.func_148857_g());
		
		outputFusion = pkt.func_148857_g().getBoolean("outFusion");
	}
}

package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.Location;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRFConverter extends TileEntityBase implements IEnergyHandler {

	public EnergyStorage storage = new EnergyStorage(500000, 5000, 5000);

	public boolean outputFE = false;

	public static final int FERatio = 5;
	public static final int RFRatio = 500;
	
	public TileEntityRFConverter() {
		maxEnergy = 5000;
	}

	@Override
	public void updateEntity() {
				
		if (!worldObj.isRemote) {
			
			if (outputFE) {

				if (EnergyUtil.canAddEnergy(this, FERatio) && storage.getEnergyStored() >= RFRatio) {

					EnergyUtil.addEnergy(this, FERatio);
					storage.extractEnergy(RFRatio, false);
				}
			}

			else {		

				if (EnergyUtil.canSubtractEnergy(this, FERatio) && (storage.getMaxEnergyStored() - storage.getEnergyStored()) >= RFRatio) {

					EnergyUtil.subtractEnergy(this, FERatio);
					storage.receiveEnergy(RFRatio, false);
				}
				
				for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
					
					Location loc = new Location(worldObj, xCoord, yCoord, zCoord).add(dir);
					TileEntity entity = loc.getTileEntity();
					
					if (entity != null && entity instanceof IEnergyReceiver) {
						
						IEnergyReceiver receiver = (IEnergyReceiver) entity;
						
						int maxExtract = storage.getMaxExtract(); //Gets the maximum amount of energy that can be extracted from this tile in one tick.
						int maxAvailable = storage.extractEnergy(maxExtract, true); //Simulates removing "maxExtract" to find out how much energy is actually available.
						int energyTransferred = receiver.receiveEnergy(dir.getOpposite(), maxAvailable, false); //Sends "maxAvailable" to the target tile and records how much energy was accepted. 

						storage.extractEnergy(energyTransferred, false);//Extract the energy transferred from the internal storage.
					}
				}
			}
		}
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

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public int getCurrentRFScaled(int i) {
		return storage.getEnergyStored() * i / storage.getMaxEnergyStored();
	}

	public void toggle() {
		outputFE = !outputFE;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		outputFE = nbt.getBoolean("outputFE");
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setBoolean("outputFE", outputFE);
		storage.writeToNBT(nbt);
	}

	// --------------------------------------------------------------------------------------

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return false;
	}

	@Override
	public EnumIO getIOType() {
		return outputFE ? EnumIO.OUTPUT : EnumIO.INPUT;
	}

	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
	
	//-------------------------------------------------Packets
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
		
		syncData.setInteger("FE", energy);
		syncData.setInteger("progress", progress);
		
		storage.writeToNBT(syncData);
		syncData.setBoolean("outputFE", outputFE);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
			
		energy = pkt.func_148857_g().getInteger("FE");
		progress = pkt.func_148857_g().getInteger("progress");
		
		storage.readFromNBT(pkt.func_148857_g());
		outputFE = pkt.func_148857_g().getBoolean("outputFE");
	}
}

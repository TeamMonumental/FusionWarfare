package calemi.fusionwarfare.tileentity.network;

import java.util.ArrayList;
import java.util.List;

import calemi.fusionwarfare.block.BlockBasicMachineBase;
import calemi.fusionwarfare.block.BlockNetworkController;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.IEnergyItem;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.IEnergy;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyItemUtil;
import calemi.fusionwarfare.util.EnergyUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityNetworkController extends TileEntityBase {

	public List<IEnergy> mechs = new ArrayList<IEnergy>();
	public int tier;

	@Override
	public EnumIO getIOType() {
		return EnumIO.NONE;
	}
		
	@Override
	public void updateEntity() {
		
		// Slots
		
		int amount = 10;
		
		int transferRate = 5 + (slots[0] == null ? 0 : slots[0].stackSize * 5);

		if (!worldObj.isRemote) {

			if (getStackInSlot(1) != null) {

				// Discharge
				if (getStackInSlot(1).getItem() instanceof IEnergyItem) {

					ItemStack stack = getStackInSlot(1);
					IEnergyItem energyItem = (IEnergyItem) stack.getItem();
					
					EnergyItemUtil.transferEnergyToBlock(stack, this, transferRate);
				}
			}

			// Charge
			if (getStackInSlot(2) != null && getStackInSlot(2).getItem() instanceof IEnergyItem) {

				ItemStack stack = getStackInSlot(2);
				IEnergyItem energyItem = (IEnergyItem) stack.getItem();

				EnergyItemUtil.transferEnergyFromBlock(this, stack, transferRate);
			}
			
			// Controller

			mechs.clear();

			for (ForgeDirection dir : ForgeDirection.values()) {
				checkBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
			}

			IEnergy lowest = findLowestEnergy();

			if (lowest != null)	EnergyUtil.transferEnergy(this, lowest, transferRate);

			for (IEnergy tempMech : mechs) {

				if (tempMech.getIOType() == EnumIO.OUTPUT) {

					EnergyUtil.transferEnergy(tempMech, this, transferRate);
				}
			}
		}	
	}

	private IEnergy findLowestEnergy() {

		IEnergy currentLowest = null;

		for (IEnergy tempBase : mechs) {

			if (tempBase.getIOType() == EnumIO.INPUT && tempBase.getEnergy() < tempBase.getMaxEnergy()) {

				if (currentLowest == null || tempBase.getEnergy() < currentLowest.getEnergy()) {
					currentLowest = tempBase;
				}
			}
		}

		return currentLowest;
	}

	public void checkBlock(int x, int y, int z) {
		
		TileEntity entity = worldObj.getTileEntity(x, y, z);

		if (entity != null && entity instanceof IEnergy && ((IEnergy) entity).getIOType() != EnumIO.REJECTED) {

			IEnergy tempMech = (IEnergy) entity;

			if (!mechs.contains(tempMech)) {

				mechs.add(tempMech);

				for (ForgeDirection dir : ForgeDirection.values()) {
					checkBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
				}
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	
		nbt.setInteger("tier", tier);		
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	
		tier = nbt.getInteger("tier");	
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
	
		writeToNBT(syncData);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {

		readFromNBT(pkt.func_148857_g());
	}
	
	//------------------------------------------------------------\\
	
	
	// --------------------------------------------------------------------------------

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return null;
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
		return 3;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}
}

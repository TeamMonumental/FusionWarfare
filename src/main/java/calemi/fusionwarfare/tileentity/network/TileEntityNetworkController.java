package calemi.fusionwarfare.tileentity.network;

import java.util.ArrayList;
import java.util.List;

import calemi.fusionwarfare.api.EnergyItemUtil;
import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.api.IEnergy;
import calemi.fusionwarfare.block.BlockBasicMachineBase;
import calemi.fusionwarfare.block.BlockNetworkController;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.IEnergyItem;
import calemi.fusionwarfare.item.ItemEnergyBase;
import calemi.fusionwarfare.item.ItemEnergyConsumable;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.BlockScanUtil;
import calemi.fusionwarfare.util.Location;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
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
	
	private int ticks;

	@Override
	public EnumIO getIOType() {
		return EnumIO.NONE;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
		
	@Override
	public void updateEntity() {
		
		// Slots
		
		int transferRate = 5 + (slots[0] == null ? 0 : slots[0].stackSize * 5);

		if (!worldObj.isRemote) {

			if (getStackInSlot(1) != null) {
			
				// Discharge
				if (getStackInSlot(1).getItem() instanceof IEnergyItem) {
					
					if (getStackInSlot(1).getItem() instanceof ItemEnergyConsumable) {
					
						IEnergyItem energyItem = (IEnergyItem)getStackInSlot(1).getItem();
					
						if (EnergyUtil.canAddEnergy(this, energyItem.getMaxEnergy())) {
							EnergyUtil.addEnergy(this, energyItem.getMaxEnergy());
							decrStackSize(1, 1);
						}								
					}
					
					else {
						ItemStack stack = getStackInSlot(1);
						IEnergyItem energyItem = (IEnergyItem) stack.getItem();
					
						EnergyItemUtil.transferEnergyToBlock(stack, this, transferRate);
					}					
				}			
			}

			// Charge
			if (getStackInSlot(2) != null && getStackInSlot(2).getItem() instanceof IEnergyItem) {

				ItemStack stack = getStackInSlot(2);
				IEnergyItem energyItem = (IEnergyItem) stack.getItem();

				EnergyItemUtil.transferEnergyFromBlock(this, stack, transferRate);
			}
			
			// Controller

			ticks++;
			ticks %= 20;
			
			if (ticks == 0) {
				mechs.clear();
				
				mechs = BlockScanUtil.scan(new Location(worldObj, xCoord, yCoord, zCoord));			
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
		return new int[]{1,2};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		
		if (side == 1 && slot == 1) {
			return true;
		}
		
		if (side > 1 && slot == 2) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		
		if (side == 0 && slot == 2) {
			
			if (stack.getItem() instanceof IEnergyItem) {
				
				IEnergyItem energyStack = (IEnergyItem)stack.getItem();
				
				if (energyStack.getEnergy(stack) >= energyStack.getMaxEnergy()) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 3;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}
}

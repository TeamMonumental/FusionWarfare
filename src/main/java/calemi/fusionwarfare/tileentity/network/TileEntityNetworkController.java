package calemi.fusionwarfare.tileentity.network;

import java.util.ArrayList;
import java.util.List;

import calemi.fusionwarfare.api.EnergyItemUtil;
import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.api.IEnergy;
import calemi.fusionwarfare.api.INetwork;
import calemi.fusionwarfare.block.BlockBasicMachineBase;
import calemi.fusionwarfare.block.BlockNetworkController;
import calemi.fusionwarfare.gui.GuiNetworkController;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.inventory.ContainerNetworkController;
import calemi.fusionwarfare.item.IEnergyItem;
import calemi.fusionwarfare.item.ItemEnergyBase;
import calemi.fusionwarfare.item.ItemEnergyConsumable;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.util.BlockScanUtil;
import calemi.fusionwarfare.util.Location;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityNetworkController extends TileEntityEnergyBase implements ITileEntityGuiHandler {

	public List<INetwork> mechs = new ArrayList<INetwork>();
		
	private int ticks;
		
	public TileEntityNetworkController() {
		maxEnergy = 25000;
	}
	
	public TileEntityNetworkController(int tier) {
		
		int energy = 25000;
		if (tier == 2) energy = 50000; 
		if (tier == 3) energy = 100000; 
		
		maxEnergy = energy;
	}
	
	@Override
	public void updateEntity() {
		
		// Slots
		
		int transferRate = overclockedDifference();

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
				
			for (INetwork tempMech : mechs) {
				
				if (tempMech instanceof IEnergy && tempMech.getIOType() == EnumIO.OUTPUT) {

					EnergyUtil.transferEnergy((IEnergy) tempMech, this, transferRate);
				}
			}
		}	
	}
	
	@Override
	public int overclockedDifference() {
		return 5 + (getOverclockingSlot() == null ? 0 : getOverclockingSlot().stackSize * 5);
	}
	
	public int getTierFromEnergy() {
		
		int tier = 1;
		if (maxEnergy == 50000) tier = 2; 
		if (maxEnergy == 100000) tier = 3; 
		return tier;
	}

	private IEnergy findLowestEnergy() {

		IEnergy currentLowest = null;

		for (INetwork tempBase : mechs) {

			if (tempBase instanceof IEnergy) {
				
				IEnergy tempEnergy = (IEnergy)tempBase;
				
				if (tempEnergy.getIOType() == EnumIO.INPUT && tempEnergy.getEnergy() < tempEnergy.getMaxEnergy()) {
							
					if (currentLowest == null || tempEnergy.getEnergy() < currentLowest.getEnergy()) {
						currentLowest = tempEnergy;
					}
				}
			}
		}

		return currentLowest;
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
	
	@Override
	public EnumIO getIOType() {
		return EnumIO.NONE;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return slots[0];
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerNetworkController(player, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiNetworkController(player, this);
	}
}

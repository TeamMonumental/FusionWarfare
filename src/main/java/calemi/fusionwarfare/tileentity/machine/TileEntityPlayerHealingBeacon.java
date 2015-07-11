package calemi.fusionwarfare.tileentity.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;

public class TileEntityPlayerHealingBeacon extends TileEntityBase {
	
	private int energyCost = 500;
	
	public TileEntityPlayerHealingBeacon() {
		maxEnergy = 5000;
		maxProgress = 25;
	}
	
	@Override
	public void updateEntity() {
				
		if (!isDone() && worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
			progress++;
		}

		else {
			resetProgress();
		}
		
		if (!worldObj.isRemote) {
			
			if (isDone()) {
				
				for (Object o : worldObj.loadedEntityList) {
					
					if (o instanceof EntityPlayer) {
						
						EntityPlayer player = (EntityPlayer)o;
												
						if (player.getDistance(xCoord, yCoord, zCoord) <= 32 && player.getHealth() < player.getMaxHealth()) {
							
							if (EnergyUtil.canSubtractEnergy(this, energyCost)) {
								EnergyUtil.subtractEnergy(this, energyCost);
								player.heal(2);
								break;
							}						
						}
					}
				}
			}			
		}	
	}
	
	//-------------------------------------------------------------------------------------------
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[]{};
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
		return EnumIO.INPUT;
	}
}

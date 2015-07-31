package calemi.fusionwarfare.tileentity.machine;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;

public class TileEntityAntiMobBeacon extends TileEntityBase {
	
	private int energyCost = 50;
	
	public TileEntityAntiMobBeacon() {
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
					
					if (o instanceof EntityMob) {
						
						EntityMob mob = (EntityMob)o;
						
						int energyCost = (int)mob.getHealth() * 5;
						
						if (mob.getDistance(xCoord, yCoord, zCoord) <= 32 && EnergyUtil.canSubtractEnergy(this, energyCost)) {
							EnergyUtil.subtractEnergy(this, energyCost);
							mob.setDead();
							break;
						}
					}
				}
			}			
		}	
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(0, 2, 0);
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
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
}

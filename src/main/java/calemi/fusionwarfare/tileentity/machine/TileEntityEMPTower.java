package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.entity.EntityMissile;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;

public class TileEntityEMPTower extends TileEntityBase {

	private int energyCost = 100000;
	
	public TileEntityEMPTower() {
		maxEnergy = 100000;
		maxProgress = 100;
	}
	
	@Override
	public void updateEntity() {
		if (energy >= energyCost) {
			
			if (progress < maxProgress) {
				progress++;
			}
			
			if (progress >= maxProgress && worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
				
				for (Object entity : worldObj.loadedEntityList) {
					
					if (entity instanceof EntityMissile) {
						
						EntityMissile missile = (EntityMissile) entity;
						
						if (missile.motionY < 0 && missile.getDistanceSq(xCoord, yCoord + 16, zCoord) <= 16) {
							
							progress = 0;
							energy -= energyCost;
							missile.setDead();
							
							//worldObj.playSound(xCoord, yCoord, zCoord, "mob.wither.spawn", 1, 1, false);
							
							EntityLightningBolt bolt = new EntityLightningBolt(worldObj, missile.posX, missile.posY, missile.posZ);
							
							worldObj.spawnEntityInWorld(bolt);
							
							break;
						}		
					}
				}				
			}
		} else {
			progress = 0;
		}
	}
	
	//----------------------------------------------------------------------------------------------------------
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[0];
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
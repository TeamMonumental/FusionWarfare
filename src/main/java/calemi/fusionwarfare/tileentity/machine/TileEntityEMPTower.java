package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.entity.EntityMissile;
import calemi.fusionwarfare.gui.GuiEnergyTank;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityEMPTower extends TileEntityEnergyBase implements ITileEntityGuiHandler {

	private int energyCost = 25000;
	
	public TileEntityEMPTower() {
		maxEnergy = 50000;
		maxProgress = 100;
	}
	
	@Override
	public void updateEntity() {
		
		if (!worldObj.isRemote) {
			
			if (!isDone() && EnergyUtil.canSubtractEnergy(this, energyCost) && worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
				progress++;
			}
			
			else resetProgress();
			
			if (isDone()) {
				
				for (Object entity : worldObj.loadedEntityList) {
					
					if (entity instanceof EntityMissile) {
						
						EntityMissile missile = (EntityMissile) entity;
						
						if (!missile.isDead && missile.motionY < 0 && missile.getDistance(xCoord, yCoord + 8, zCoord) <= 16) {
							
							resetProgress();
							EnergyUtil.subtractEnergy(this, energyCost);
							missile.setDead();
							
							EntityLightningBolt bolt = new EntityLightningBolt(worldObj, missile.posX, missile.posY, missile.posZ);
							
							worldObj.spawnEntityInWorld(bolt);
							
							break;
						}		
					}
				}				
			}
		} 
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(0, 1, 0);
	}
	
	@Override
	public int getSizeInventory() {
		return 0;
	}
	
	@Override
	public EnumIO getIOType() {
		return EnumIO.INPUT;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerEnergyTank(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiEnergyTank(player, this, "EMP Tower", true);
	}
}
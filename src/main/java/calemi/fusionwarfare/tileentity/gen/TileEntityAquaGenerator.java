package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.gui.GuiEnergyTank;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.util.Location;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityAquaGenerator extends TileEntityEnergyBase implements ITileEntityGuiHandler {

	public TileEntityAquaGenerator() {
		maxEnergy = 500;
		maxProgress = 0;
	}

	@Override
	public void updateEntity() {
	
		if (!worldObj.isRemote) {
			
			if (worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature < 1.5F) {
				
				if (worldObj.getTotalWorldTime() % 40L == 0L) {
					
					if (worldObj.isRaining() && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)) {
						EnergyUtil.addEnergy(this, 5);
					}
					
					findWater();
				}		
			}				
		}
	}
	
	private void findWater() {

		ForgeDirection[] sides = {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST, ForgeDirection.DOWN};
				
		for (ForgeDirection dir : sides) {
			
			if (getBlockinDir(dir).getBlockMetadata() == 0 && getBlockinDir(dir).getBlock() == Blocks.water) {		
				EnergyUtil.addEnergy(this, 1);
			}				
		}			
	}
	
	private Location getBlockinDir(ForgeDirection dir) {
		
		return new Location(worldObj, xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);	
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
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
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiEnergyTank(player, this, "Aqua Generator", false);
	}
}
package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntityBaseWithNoInv;
import calemi.fusionwarfare.util.EnergyUtil;
import calemi.fusionwarfare.util.Location;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityAquaGenerator extends TileEntityBaseWithNoInv {

	public TileEntityAquaGenerator() {
		maxEnergy = 500;
	}

	@Override
	public void updateEntity() {

		if (!worldObj.isRemote) {
			
			if (worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature < 1.5F) {
				
				if (worldObj.getTotalWorldTime() % 40L == 0L) {
					
					if (worldObj.isRaining() && worldObj.canBlockSeeTheSky(xCoord, yCoord + 1, zCoord)) {
						EnergyUtil.addEnergy(this, 4);
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
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}
}
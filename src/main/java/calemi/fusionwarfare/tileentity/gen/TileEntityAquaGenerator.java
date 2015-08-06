package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import calemi.fusionwarfare.util.Location;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityAquaGenerator extends TileEntityBase {

	public TileEntityAquaGenerator() {
		maxEnergy = 500;
		maxProgress = 0;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

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

	//----------------------------------------------------------------\\
	
	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return false;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
}
package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import calemi.fusionwarfare.util.Location;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class TileEntityGeothermalGenerator extends TileEntityBase {

	public TileEntityGeothermalGenerator() {
		maxEnergy = 750;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (!worldObj.isRemote) {
			
			if (worldObj.getTotalWorldTime() % 40L == 0L) {			
				findLava();
			}		
		}
	}

	private void findLava() {

		for (int x = xCoord - 1; x <= xCoord + 1; x++) {
			for (int z = zCoord - 1; z <= zCoord + 1; z++) {
				
				Location location = new Location(worldObj, x, yCoord, z);

				if (location.getBlock() == Blocks.lava && location.getBlockMetadata() == 0 ) {
					EnergyUtil.addEnergy(this, 5);
				}
			}
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return new int[]{};
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

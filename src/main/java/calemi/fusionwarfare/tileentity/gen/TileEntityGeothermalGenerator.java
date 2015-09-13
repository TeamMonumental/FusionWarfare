package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
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
		return EnumIO.OUTPUT;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
}

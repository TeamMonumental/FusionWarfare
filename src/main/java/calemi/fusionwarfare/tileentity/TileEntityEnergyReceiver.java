package calemi.fusionwarfare.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class TileEntityEnergyReceiver extends TileEntityBase {

	public TileEntityEnergyReceiver() {
		maxEnergy = 5000;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[]{};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {		
		return false;
	}
}

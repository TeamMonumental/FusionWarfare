package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.item.ItemStack;

public class TileEntityEMPTower extends TileEntityBase {

	public TileEntityEMPTower() {
		maxEnergy = 100000;
		maxProgress = 100;
	}
	
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
		return EnumIO.INPUT;
	}	
}

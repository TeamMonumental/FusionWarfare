package calemi.fusionwarfare.tileentity.network;

import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityNetworkGate extends TileEntityBase {

	@Override
	public void updateEntity() {

		if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 2);
		}
		
		else {
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 2);
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
		return getBlockMetadata() == 0 ? EnumIO.NONE : EnumIO.REJECTED;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
}

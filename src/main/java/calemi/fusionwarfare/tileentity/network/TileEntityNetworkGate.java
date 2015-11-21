package calemi.fusionwarfare.tileentity.network;

import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityNetworkGate extends TileEntityEnergyBase {

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
	public int getSizeInventory() {
		return 0;
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

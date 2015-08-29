package calemi.fusionwarfare.tileentity.network;

import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.IEnergy;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityNetworkCable extends TileEntityBase {

	public ForgeDirection[] connections = new ForgeDirection[6];

	@Override
	public EnumIO getIOType() {
		return EnumIO.NONE;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		this.updateConnections();
	}

	public void updateConnections() {
		
		if (this.worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof IEnergy && ((IEnergy)worldObj.getTileEntity(xCoord, yCoord + 1, zCoord)).getIOType() != EnumIO.REJECTED)
			connections[0] = ForgeDirection.UP;
		else
			connections[0] = null;
		if (this.worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof IEnergy && ((IEnergy)worldObj.getTileEntity(xCoord, yCoord - 1, zCoord)).getIOType() != EnumIO.REJECTED)
			connections[1] = ForgeDirection.DOWN;
		else
			connections[1] = null;
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord - 1) instanceof IEnergy && ((IEnergy)worldObj.getTileEntity(xCoord, yCoord, zCoord - 1)).getIOType() != EnumIO.REJECTED)
			connections[2] = ForgeDirection.NORTH;
		else
			connections[2] = null;
		if (this.worldObj.getTileEntity(xCoord + 1, yCoord, zCoord) instanceof IEnergy && ((IEnergy)worldObj.getTileEntity(xCoord + 1, yCoord , zCoord)).getIOType() != EnumIO.REJECTED)
			connections[3] = ForgeDirection.EAST;
		else
			connections[3] = null;
		if (this.worldObj.getTileEntity(xCoord, yCoord, zCoord + 1) instanceof IEnergy && ((IEnergy)worldObj.getTileEntity(xCoord, yCoord, zCoord + 1)).getIOType() != EnumIO.REJECTED)
			connections[4] = ForgeDirection.SOUTH;
		else
			connections[4] = null;
		if (this.worldObj.getTileEntity(xCoord - 1, yCoord, zCoord) instanceof IEnergy && ((IEnergy)worldObj.getTileEntity(xCoord - 1, yCoord, zCoord)).getIOType() != EnumIO.REJECTED)
			connections[5] = ForgeDirection.WEST;
		else
			connections[5] = null;
	}

	public boolean onlyOneOpposite(ForgeDirection[] directions) {

		ForgeDirection mainDirection = null;
		boolean isOpposite = false;

		for (int i = 0; i < directions.length; i++) {

			if (mainDirection == null && directions[i] != null)
				mainDirection = directions[i];

			if (directions[i] != null && mainDirection != directions[i]) {

				if (!isOpposite(mainDirection, directions[i]))
					return false;
				else
					isOpposite = true;
			}
		}

		return isOpposite;
	}

	public boolean isOpposite(ForgeDirection firstDirection, ForgeDirection secondDirection) {

		if ((firstDirection.equals(ForgeDirection.NORTH) && secondDirection.equals(ForgeDirection.SOUTH)) || (firstDirection.equals(ForgeDirection.SOUTH) && secondDirection.equals(ForgeDirection.NORTH)))
			return true;
		if ((firstDirection.equals(ForgeDirection.UP) && secondDirection.equals(ForgeDirection.DOWN)) || (firstDirection.equals(ForgeDirection.DOWN) && secondDirection.equals(ForgeDirection.UP)))
			return true;
		if ((firstDirection.equals(ForgeDirection.EAST) && secondDirection.equals(ForgeDirection.WEST)) || (firstDirection.equals(ForgeDirection.WEST) && secondDirection.equals(ForgeDirection.EAST)))
			return true;

		return false;
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
}

package calemi.fusionwarfare.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntitySupplyCrate extends TileEntityBase {

	public boolean isEmpty = false;
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (isEmpty()) {
			
			if (!worldObj.isRemote) {
				worldObj.setBlockToAir(xCoord, yCoord, zCoord);	
			}
			
			isEmpty = true;
		}
	}
	
	private boolean isEmpty() {
		
		for (ItemStack slot : slots) {
			
			if (slot != null) {			
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);	
		nbt.setBoolean("isEmpty", isEmpty);	
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);	
		isEmpty = nbt.getBoolean("isEmpty");
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
	
		writeToNBT(syncData);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {

		readFromNBT(pkt.func_148857_g());
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
		return 27;
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return false;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.NONE;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
}

package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.util.EnergyUtil;
import calemi.fusionwarfare.util.Location;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMissileSiloCore extends TileEntitySecurity {

	public int currentDelay;
	public int targetX;
	public int targetZ;
	
	public boolean sprayMode;
	
	public TileEntityMissileSiloCore() {
		maxEnergy = 10000;
		maxProgress = 0;
	}

	@Override
	public void updateEntity() {
		
	}
	
	public void update() {
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	
		nbt.setInteger("currentDelay", currentDelay);
		nbt.setInteger("targetX", targetX);
		nbt.setInteger("targetZ", targetZ);
		
		nbt.setBoolean("sprayMode", sprayMode);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	
		currentDelay = nbt.getInteger("currentDelay");
		targetX = nbt.getInteger("targetX");
		targetZ = nbt.getInteger("targetZ");
		
		sprayMode = nbt.getBoolean("sprayMode");
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
	
	//------------------------------------------------------------\\
	
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
		return 9;
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
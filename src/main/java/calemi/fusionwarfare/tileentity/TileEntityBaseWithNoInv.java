package calemi.fusionwarfare.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityBaseWithNoInv extends TileEntity implements IEnergy {

	public int maxProgress, progress;
	public int maxEnergy, energy;
	
	@Override
	public int getEnergy() {
		return energy;
	}

	@Override
	public int getMaxEnergy() {
		return maxEnergy;
	}
	
	@Override
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	//---------------------------------------------------------------------------------------------------------
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		nbt.setInteger("FE", energy);
		nbt.setInteger("progress", progress);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		energy = nbt.getInteger("FE");
		progress = nbt.getInteger("progress");
	}
	
	//---------------------------------------------------------------------------------------------------------
	
	@Override
	public Packet getDescriptionPacket() {
		
		NBTTagCompound nbt = new NBTTagCompound();
		
		writeToNBT(nbt);
		
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		
		NBTTagCompound nbt = packet.func_148857_g();
		
		readFromNBT(nbt);
	}
}
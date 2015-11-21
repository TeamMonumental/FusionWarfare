package calemi.fusionwarfare.tileentity.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import calemi.fusionwarfare.api.IEnergy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class TileEntityEnergyBase extends TileEntityInventoryBase implements IEnergy {

	public int energy;
	public int maxEnergy;
	
	public int progress;
	public int maxProgress;
	
	public abstract ItemStack getOverclockingSlot();
	
	public int overclockedDifference() {
		
		if (getOverclockingSlot() != null) {
			
			return (getOverclockingSlot().stackSize) * (maxProgress / 11);
		}
		
		return 0;
	}
	
	@Override
	public int getEnergy() {
		return energy;
	}
	
	@Override
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	@Override
	public int getMaxEnergy() {
		return maxEnergy;
	}
	
	@Override
	public void setMaxEnergy(int energy) {
		this.maxEnergy = energy;
	}
	
	public int getCurrentEnergyScaled(int i) {
		return this.energy * i / maxEnergy;
	}
	
	public int getCurrentProgress() {
		return progress;
	}
	
	public int getMaxProgress() {
		return maxProgress - overclockedDifference();
	}
	
	public void resetProgress() {
		progress = 0;
	}
	
	public boolean isDone() {
		return progress >= getMaxProgress();
	}
	
	public int getCurrentProgressScaled(int i) {
		return this.progress * i / getMaxProgress();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		readSyncNBT(nbt);		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		writeSyncNBT(nbt);
	}
	
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		energy = nbt.getInteger("FE");
		maxEnergy = nbt.getInteger("maxFE");
		progress = nbt.getInteger("progress");
		maxProgress = nbt.getInteger("maxProgress");
	}
	
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);		
		nbt.setInteger("FE", energy);
		nbt.setInteger("maxFE", maxEnergy);
		nbt.setInteger("progress", progress);	
		nbt.setInteger("maxProgress", maxProgress);
	}
}

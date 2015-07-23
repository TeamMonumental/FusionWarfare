package calemi.fusionwarfare.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class TileEntityBase extends TileEntity implements ISidedInventory, IEnergy {

	public ItemStack[] slots = new ItemStack[getSizeInventory()];

	public int energy;
	public int maxEnergy;
	
	public int progress;
	public int maxProgress;
	
	public abstract ItemStack getOverclockingSlot();
	
	public int overclockedDifference() {
		
		if (getOverclockingSlot() != null) {
			
			return getOverclockingSlot().stackSize * (maxProgress / 15);
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
	
	@SideOnly(Side.CLIENT)
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
	
	@SideOnly(Side.CLIENT)
	public int getCurrentProgressScaled(int i) {
		return this.progress * i / getMaxProgress();
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	
		for (int i = 0; i < slots.length; i++) {

			NBTTagCompound tempTag = new NBTTagCompound();

			if (slots[i] != null) {

				slots[i].writeToNBT(tempTag);
				tempTag.setBoolean("null", false);

			} else {
				tempTag.setBoolean("null", true);
			}

			nbt.setTag("slot_" + i, tempTag);
		}
		
		nbt.setInteger("energy", energy);
		nbt.setInteger("maxEnergy", maxEnergy);
		nbt.setInteger("progress", progress);	
		nbt.setInteger("maxProgress", maxProgress);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	
		for (int i = 0; i < slots.length; i++) {

			NBTTagCompound tempTag = nbt.getCompoundTag("slot_" + i);

			if (!tempTag.getBoolean("null")) {

				slots[i] = ItemStack.loadItemStackFromNBT(tempTag);
			}
		}
		
		energy = nbt.getInteger("energy");
		maxEnergy = nbt.getInteger("maxEnergy");
		progress = nbt.getInteger("progress");
		maxProgress = nbt.getInteger("maxProgress");
	}
	
	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {

		if (this.slots[i] != null) {

			ItemStack itemstack;

			if (this.slots[i].stackSize <= j) {

				itemstack = this.slots[i];
				this.slots[i] = null;
				return itemstack;

			} else {

				itemstack = this.slots[i].splitStack(j);

				if (this.slots[i].stackSize == 0) {

					this.slots[i] = null;
				}

				return itemstack;
			}
		}

		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {

		if (this.slots[i] != null) {

			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		}

		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {

		this.slots[i] = itemstack;

		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {

			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}
	
	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}
	
	//-------------------------------------------------Packets
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
	
		syncData.setInteger("energy", energy);
		syncData.setInteger("progress", progress);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		
		energy = pkt.func_148857_g().getInteger("energy");
		progress = pkt.func_148857_g().getInteger("progress");
	}
}

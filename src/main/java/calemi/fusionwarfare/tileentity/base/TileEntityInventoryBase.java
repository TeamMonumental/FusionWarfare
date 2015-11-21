package calemi.fusionwarfare.tileentity.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class TileEntityInventoryBase extends TileEntityBase implements ISidedInventory {

	public ItemStack[] slots = new ItemStack[getSizeInventory()];
	
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
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		
		int[] slots = new int[getSizeInventory()];
		
		for (int i = 0; i < slots.length; i++) {
			slots[i] = i;
		}
		
		return slots;
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
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	
		for (int i = 0; i < slots.length; i++) {

			NBTTagCompound tempTag = nbt.getCompoundTag("slot_" + i);

			if (!tempTag.getBoolean("null")) {

				slots[i] = ItemStack.loadItemStackFromNBT(tempTag);
			}
		}
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
	}
}

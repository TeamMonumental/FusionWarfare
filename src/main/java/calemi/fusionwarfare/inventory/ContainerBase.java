package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.tileentity.base.TileEntityInventoryBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBase extends Container {

	public TileEntityInventoryBase tileEntity;
	public EntityPlayer player;
	
	public ContainerBase(EntityPlayer player, TileEntityInventoryBase tileEntity) {
		this.tileEntity = tileEntity;
		this.player = player;
	}
	
	public void addPlayerInv(int x, int y) {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {		
				
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, x + (j * 18), y + (i * 18)));
			}
		}
	}

	public void addHotbar(int x, int y) {
		
		for (int i = 0; i < 9; i++) {
			
			this.addSlotToContainer(new Slot(player.inventory, i, x + i * 18, y));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotId);
			
		if (slot != null && slot.getHasStack()) {
			
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
					
			if (slotId < 27) {
				
				if (!this.mergeItemStack(itemstack1, 27, 35, false)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			
			else {
				
				if (!this.mergeItemStack(itemstack1, 0, 26, false)) {
					return null;
				}	
				
				slot.onSlotChange(itemstack1, itemstack);
			}
			
			if (itemstack1.stackSize == 0) {				
				slot.putStack((ItemStack)null);
			}
			
			else {				
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}
		
		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
}

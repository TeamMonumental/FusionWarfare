package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.ItemMissile;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerReactorCore extends ContainerBase {

	private int[] slots = {0,1,2,3,4,5};
	
	public ContainerReactorCore(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);		
		
		//Charge					
		for (int y = 0; y < 2; y++) {			
			for (int x = 0; x < 3; x++) {
				
				addSlotToContainer(new Slot(tileentity, x + y * 3, 26 + (x * 21), 31 + (y * 21)));			
			}
		}
			
		addPlayerInv(8, 99);
		addHotbar(8, 157);
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotId);
			
		if (slot != null && slot.getHasStack()) {
			
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
				
			if (slotId < slots.length) {
				
				if (!this.mergeItemStack(itemstack1, slots.length, slots.length + 36, false)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			
			else if (slotId >= slots.length) {
				
				if (itemstack1.getItem() == InitItems.advanced_infused_catalyst) {								
					
					if (!this.mergeItemStack(itemstack1, slots[0], slots.length, false)) {
						return null;
					}				
					
					slot.onSlotChange(itemstack1, itemstack);
				}
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
}

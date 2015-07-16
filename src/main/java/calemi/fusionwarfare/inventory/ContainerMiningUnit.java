package calemi.fusionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.TileEntityBase;

public class ContainerMiningUnit extends ContainerBase {

	private int[] slots = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
	
	public ContainerMiningUnit(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);
		
		for (int y = 0; y < 2; y++) {
			for (int x = 0; x < 7; x++) {
				addSlotToContainer(new Slot(tileentity, x + (y * 7), 26 + (x * 18), 27 + (y * 18)));
			}
		}
		
		addSlotToContainer(new SlotOverclocking(tileentity, slots[14], 154, 45, 15));
		
		addPlayerInv(8, 85);
		addHotbar(8, 143);
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
				
				if (itemstack1.getItem() == InitItems.overclocking_chip) {								
					
					if (!this.mergeItemStack(itemstack1, slots[14], slots[14] + 1, false)) {
						return null;
					}				
					
					slot.onSlotChange(itemstack1, itemstack);
				}
				
				else {
					
					if (!this.mergeItemStack(itemstack1, slots[0], slots[13] + 1, false)) {
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
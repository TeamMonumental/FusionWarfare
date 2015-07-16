package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ContainerOneInput extends ContainerBase {

	private int[] slots = {0,1,2};

	public ContainerOneInput(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);

		// new Slot(inv, id, x, y)

		// Gui's Slots

		// Input Slot
		addSlotToContainer(new Slot(tileentity, slots[0], 56, 36));

		// Output Slot
		addSlotToContainer(new SlotOutput(tileentity, slots[1], 116, 36));

		// Overclocking
		addSlotToContainer(new SlotOverclocking(tileentity, slots[2], 152, 62, 15));

		addPlayerInv(8, 84);
		addHotbar(8, 142);
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
					
					if (!this.mergeItemStack(itemstack1, slots[2], slots[2] + 1, false)) {
						return null;
					}				
					
					slot.onSlotChange(itemstack1, itemstack);
				}
				
				else {
					
					if (!this.mergeItemStack(itemstack1, slots[0], slots[0] + 1, false)) {
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
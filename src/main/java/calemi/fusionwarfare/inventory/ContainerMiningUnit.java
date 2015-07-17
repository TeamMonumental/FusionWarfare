package calemi.fusionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.TileEntityBase;

public class ContainerMiningUnit extends ContainerBase {

	private int[] slots = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
	
	public ContainerMiningUnit(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);
		
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 7; x++) {
				addSlotToContainer(new Slot(tileentity, x + (y * 7), 26 + (x * 18), 27 + (y * 18)));
			}
		}
		
		addSlotToContainer(new SlotOverclocking(tileentity, slots[21], 152, 45, 10));
		
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
				
				if (itemstack1.getItem() == InitItems.overclocking_chip) {								
					
					Slot slot2 = (Slot)this.inventorySlots.get(21);
					int space = slot2.getStack() != null ? slot2.getSlotStackLimit() - slot2.getStack().stackSize : slot2.getSlotStackLimit();
					
					if (space > 0) {
						
						if (itemstack1.stackSize > space) {
						
							if (slot2.getHasStack()) slot2.getStack().stackSize += space;
							else slot2.putStack(new ItemStack(itemstack1.getItem(), space));							
							
							itemstack1.stackSize -= space;
							
							slot.onSlotChange(itemstack1, itemstack);
						}
						
						else {
							
							if (slot2.getHasStack()) slot2.getStack().stackSize += itemstack1.stackSize;							
							else slot2.putStack(new ItemStack(itemstack1.getItem(), itemstack1.stackSize));
							
							itemstack1.stackSize -= itemstack1.stackSize;
							
							slot.onSlotChange(itemstack1, itemstack);
						}					
					}					
				}
				
				else {
					
					if (!this.mergeItemStack(itemstack1, slots[0], slots[20] + 1, false)) {
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
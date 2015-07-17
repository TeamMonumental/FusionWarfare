package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerOverclockedEnergyTank extends ContainerBase {

	public ContainerOverclockedEnergyTank(EntityPlayer player, TileEntityBase tileentity, int overClockNumber) {
		super(player, tileentity);		
		
		//Overclocking
		addSlotToContainer(new SlotOverclocking(tileentity, 0, 143, 64, overClockNumber));
	
		addPlayerInv(8, 99);
		addHotbar(8, 157);
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotId);
			
		if (slot != null && slot.getHasStack()) {
			
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
				
			if (itemstack1.getItem() == InitItems.overclocking_chip) {								
				
				Slot slot2 = (Slot)this.inventorySlots.get(0);
				int space = slot2.getStack() != null ? slot2.getSlotStackLimit() - slot2.getStack().stackSize : slot2.getSlotStackLimit();
				
				if (space > 0) {
					
					slot2.putStack(new ItemStack(itemstack1.getItem(), space));
					itemstack1.stackSize -= space;
					
					slot.onSlotChange(itemstack1, itemstack);
				}					
			}
			
			else if (slotId < 28) {
				
				if (!this.mergeItemStack(itemstack1, 28, 36, false)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			
			else {
				
				if (!this.mergeItemStack(itemstack1, 1, 27, false)) {
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
}
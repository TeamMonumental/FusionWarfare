package calemi.fusionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.ItemMissile;
import calemi.fusionwarfare.tileentity.TileEntityBase;

public class ContainerMissileLauncher extends ContainerBase {

	public ContainerMissileLauncher(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);	
		
		//new Slot(inv, id, x, y)
		
		//Gui's Slots

		//Input Slot
		this.addSlotToContainer(new SlotMissile(tileentity, 0, 79, 33));
		
		addPlayerInv(8, 84);		
		addHotbar(8, 142);		
	}	
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slotId) {
		
		ItemStack itemstack = null;
		Slot slot = (Slot)this.inventorySlots.get(slotId);
			
		if (slot != null && slot.getHasStack()) {
			
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
				
			if (itemstack1.getItem() instanceof ItemMissile) {
				
				if (slotId > 0) {
					
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return null;
					}

					slot.onSlotChange(itemstack1, itemstack);
				}	
				
				else {
					
					if (!this.mergeItemStack(itemstack1, 1, 36, false)) {
						return null;
					}

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

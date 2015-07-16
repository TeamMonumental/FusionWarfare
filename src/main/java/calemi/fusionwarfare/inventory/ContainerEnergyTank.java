package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEnergyTank extends ContainerBase {

	public ContainerEnergyTank(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);		
		
		addPlayerInv(8, 99);
		addHotbar(8, 157);
	}

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
}

package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.IEnergyItem;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerNetworkController extends ContainerBase {

	private int[] slots = {0,1,2};
	
	public ContainerNetworkController(EntityPlayer player, TileEntityBase tileentity, int tier) {
		super(player, tileentity);		
		
		//Overclocking
		addSlotToContainer(new SlotOverclocking(tileentity, slots[0], 143, 64, tier * 5));
		//Discharge	
		addSlotToContainer(new Slot(tileentity, slots[1], 17, 23));
		//Charge
		addSlotToContainer(new Slot(tileentity, slots[2], 17, 61));
		
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
					
					if (!this.mergeItemStack(itemstack1, slots[0], slots[0] + 1, false)) {
						return null;
					}				
					
					slot.onSlotChange(itemstack1, itemstack);
				}
				
				else if (itemstack1.getItem() instanceof IEnergyItem) {
					
					if (!this.mergeItemStack(itemstack1, slots[1], slots[2] + 1, false)) {
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

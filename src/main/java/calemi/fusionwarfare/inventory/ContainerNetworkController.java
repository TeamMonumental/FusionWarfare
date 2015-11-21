package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.IEnergyItem;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerNetworkController extends ContainerEnergyBase {

	private int[] slots = {0,1,2};
	
	public ContainerNetworkController(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(player, tileEntity);		
		
		TileEntityNetworkController tileEntityController = (TileEntityNetworkController)tileEntity;
					
		//Overclocking
		addSlotToContainer(new SlotOverclocking(tileEntity, slots[0], 143, 64, tileEntityController.getTierFromEnergy() * 5));
		//Discharge	
		addSlotToContainer(new Slot(tileEntity, slots[1], 17, 23));
		//Charge
		addSlotToContainer(new Slot(tileEntity, slots[2], 17, 61));
		
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
					
					Slot slot2 = (Slot)this.inventorySlots.get(0);
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
				
				else if (itemstack1.getItem() == InitItems.infused_crystal) {

					if (!this.mergeItemStack(itemstack1, slots[1], slots[1] + 1, false)) {
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

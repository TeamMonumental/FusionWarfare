package calemi.fusionwarfare.inventory;

import java.util.ArrayList;
import java.util.Iterator;

import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerTwoInputs extends ContainerEnergyBase {

	private int[] slots = {0,1,2,3};
	
	public ContainerTwoInputs(EntityPlayer player, TileEntityEnergyBase tileentity) {
		super(player, tileentity);	
		
		//new Slot(inv, id, x, y)
		
		//Gui's Slots

		//Input Slot
		addSlotToContainer(new Slot(tileentity, slots[0], 56, 25));
		
		//Input Slot 2
		addSlotToContainer(new Slot(tileentity, slots[1], 56, 47));
		
		//Output Slot
		addSlotToContainer(new SlotOutput(tileentity, slots[2], 116, 36));	

		//Overclocking
		addSlotToContainer(new SlotOverclocking(tileentity, slots[3], 152, 62, 10));
		
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
					
					Slot slot2 = (Slot)this.inventorySlots.get(3);
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
					
					if (!this.mergeItemStack(itemstack1, slots[0], slots[1] + 1, false)) {
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
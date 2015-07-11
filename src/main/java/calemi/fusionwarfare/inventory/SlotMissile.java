package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.item.ItemMissile;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMissile extends Slot {

	public SlotMissile(IInventory iinv, int id, int x, int y) {
		super(iinv, id, x, y);
	}
	
	public boolean isItemValid(ItemStack stack) {
        return stack.getItem() instanceof ItemMissile;
    }			
}

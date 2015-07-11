package calemi.fusionwarfare.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOutput extends Slot {

	public SlotOutput(IInventory iinv, int id, int x, int y) {
		super(iinv, id, x, y);
	}
	
	public boolean isItemValid(ItemStack p_75214_1_) {
        return false;
    }		
}

package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.ItemMissile;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOverclocking extends Slot {

	private int stackLimit;
	
	public SlotOverclocking(IInventory iinv, int id, int x, int y, int stackLimit) {
		super(iinv, id, x, y);
		this.stackLimit = stackLimit;
	}
	
	@Override
	public int getSlotStackLimit() {
		return stackLimit;
	}
	
	public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == InitItems.overclocking_chip;
    }			
}

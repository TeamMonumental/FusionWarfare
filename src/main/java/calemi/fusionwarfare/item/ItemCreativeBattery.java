package calemi.fusionwarfare.item;

import net.minecraft.item.ItemStack;

public class ItemCreativeBattery extends ItemBase implements IEnergyItem {

	public ItemCreativeBattery() {
		super("creative_battery", true, true);
		setMaxStackSize(1);
	}

	@Override
	public int getEnergy(ItemStack stack) {
		return Integer.MAX_VALUE - (Integer.MAX_VALUE / 2);
	}

	@Override
	public void setEnergy(ItemStack stack, int energy) {}
	
	@Override
	public boolean addEnergy(ItemStack stack, int energy) {
		return true;
	}

	@Override
	public boolean subtractEnergy(ItemStack stack, int energy) {
		return true;
	}
	
	@Override
	public int getMaxEnergy() {
		return Integer.MAX_VALUE;
	}	
}
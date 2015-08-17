package calemi.fusionwarfare.item;

import net.minecraft.item.ItemStack;

public class ItemEnergyConsumable extends ItemBase implements IEnergyItem {

	private int energy;
	
	public ItemEnergyConsumable(String imagePath, int energy, boolean hasEffect) {
		super(imagePath, true, hasEffect);
		this.energy = energy;
	}

	@Override
	public int getEnergy(ItemStack stack) {
		return getMaxEnergy();
	}

	@Override
	public void setEnergy(ItemStack stack, int energy) {}

	@Override
	public boolean addEnergy(ItemStack stack, int energy) {
		return false;
	}

	@Override
	public boolean subtractEnergy(ItemStack stack, int energy) {		
		return false;
	}

	@Override
	public int getMaxEnergy() {
		return energy;
	}
}

package calemi.fusionwarfare.item;

import net.minecraft.item.ItemStack;

public interface IEnergyItem {

	public int getEnergy(ItemStack stack);
	public void setEnergy(ItemStack stack, int energy);	
	public boolean addEnergy(ItemStack stack, int energy);
	public boolean subtractEnergy(ItemStack stack, int energy);
	public int getMaxEnergy();
}

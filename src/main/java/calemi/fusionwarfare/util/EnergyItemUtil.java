package calemi.fusionwarfare.util;

import net.minecraft.item.ItemStack;
import calemi.fusionwarfare.api.IEnergy;
import calemi.fusionwarfare.item.IEnergyItem;

public class EnergyItemUtil {

	// ITEM ---> BLOCK
	public static void transferEnergyToBlock(ItemStack stack, IEnergy to, int amount) {

		if (!(stack.getItem() instanceof IEnergyItem)) {
			return;
		}

		IEnergyItem energyItem = (IEnergyItem) stack.getItem();

		if (EnergyUtil.isFull(to) || energyItem.getEnergy(stack) <= 0) {
			return;
		}

		if (energyItem.getEnergy(stack) >= amount && EnergyUtil.canAddEnergy(to, amount)) {
			energyItem.subtractEnergy(stack, amount);
			EnergyUtil.addEnergy(to, amount);
			return;
		}

		int sourceEnergy = energyItem.getEnergy(stack);
		int targetSpace = EnergyUtil.getSpace(to);

		int min = Math.min(sourceEnergy, targetSpace);

		if (min <= amount) {
			transferEnergyToBlock(stack, to, min);
		}
	}

	// BLOCK ---> ITEM
	public static void transferEnergyFromBlock(IEnergy from, ItemStack stack, int amount) {

		if (!(stack.getItem() instanceof IEnergyItem)) {
			return;
		}

		IEnergyItem energyItem = (IEnergyItem) stack.getItem();

		if (energyItem.getEnergy(stack) >= energyItem.getMaxEnergy() || from.getEnergy() <= 0) {
			return;
		}

		if (from.getEnergy() >= amount && getSpace(energyItem, stack) >= amount) {
			energyItem.addEnergy(stack, amount);
			EnergyUtil.subtractEnergy(from, amount);
			return;
		}

		int sourceEnergy = from.getEnergy();
		int targetSpace = getSpace(energyItem, stack);

		int min = Math.min(sourceEnergy, targetSpace);

		if (min <= amount) {
			transferEnergyFromBlock(from, stack, min);
		}
	}

	// ITEM ---> ITEM
	public static void transferEnergy(ItemStack from, ItemStack to, int amount) {

		if (!(from.getItem() instanceof IEnergyItem) || !(to.getItem() instanceof IEnergyItem)) {
			return;
		}

		IEnergyItem energyFrom = (IEnergyItem) from.getItem();
		IEnergyItem energyTo = (IEnergyItem) to.getItem();

		if (energyTo.getEnergy(to) >= energyTo.getMaxEnergy() || energyFrom.getEnergy(from) <= 0) {
			return;
		}

		if (energyFrom.getEnergy(from) >= amount && getSpace(energyTo, to) >= amount) {
			energyTo.addEnergy(to, amount);
			energyFrom.subtractEnergy(from, amount);
			return;
		}

		int sourceEnergy = energyFrom.getEnergy(from);
		int targetSpace = getSpace(energyTo, to);

		int min = Math.min(sourceEnergy, targetSpace);

		if (min <= amount) {
			transferEnergy(from, to, min);
		}
	}

	public static int getSpace(IEnergyItem e, ItemStack stack) {
		return e.getMaxEnergy() - e.getEnergy(stack);
	}
}

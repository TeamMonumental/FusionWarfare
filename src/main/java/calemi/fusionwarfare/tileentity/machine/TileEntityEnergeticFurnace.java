package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class TileEntityEnergeticFurnace extends TileEntityBase {

	public TileEntityEnergeticFurnace() {
		maxEnergy = 5000;
		maxProgress = 100;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		int progressMultiplier = (slots[2] == null ? 1 : slots[2].stackSize + 1);
		
		int energyCost = 10;

		if (!worldObj.isRemote) {
			
			if (EnergyUtil.hasEnergy(this, energyCost) && canSmelt()) {
				progress += progressMultiplier;
			}
			
			else {
				resetProgress();
			}

			if (isDone()) {

				resetProgress();
				EnergyUtil.subtractEnergy(this, energyCost);

				ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(slots[0]).copy();

				if (slots[1] == null) {
					slots[1] = itemstack;
				}

				else if (slots[1].getItem() == itemstack.getItem()) {
					slots[1].stackSize += itemstack.stackSize;
				}
				
				decrStackSize(0, 1);
			}
		}

	}

	private boolean canSmelt() {

		if (this.slots[0] == null) {
			return false;
		}

		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(slots[0]);
		if (itemstack == null) return false;
		if (slots[1] == null) return true;
		if (!slots[1].isItemEqual(itemstack)) return false;
		int result = slots[1].stackSize + itemstack.stackSize;
		return result <= getInventoryStackLimit() && result <= slots[1].getMaxStackSize();
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.INPUT;
	}

	@Override
	public int getSizeInventory() {
		return 3;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[]{0, 1};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return side != 0 && slot != 1;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return side == 0 && slot == 1;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		
		ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(is);
		if (itemstack == null) return false;
		return true;
	}
}

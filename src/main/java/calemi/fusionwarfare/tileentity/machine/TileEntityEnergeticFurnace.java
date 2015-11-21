package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.gui.GuiOneInput;
import calemi.fusionwarfare.inventory.ContainerOneInput;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class TileEntityEnergeticFurnace extends TileEntityEnergyBase implements ITileEntityGuiHandler {
	
	public static int energyCost = 10;
	
	public TileEntityEnergeticFurnace() {
		maxEnergy = 5000;
		maxProgress = 100;
	}
	
	@Override
	public void updateEntity() {

		if (!worldObj.isRemote) {
			
			if (!isDone() && EnergyUtil.canSubtractEnergy(this, energyCost) && canSmelt()) {
				progress++;
			}
			
			else resetProgress();

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
	public ItemStack getOverclockingSlot() {
		return slots[2];
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

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerOneInput(player, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiOneInput(player, this, "Energetic Furnace");
	}
}

package calemi.fusionwarfare.tileentity.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityOreEnricher extends TileEntityBase {

	public static Map<Block, ItemStack> recipes = new HashMap<Block, ItemStack>();
	
	private int energyCost = 50;
	
	static {
		recipes.put(Blocks.coal_ore, new ItemStack(Items.coal, 2));
		recipes.put(Blocks.iron_ore, new ItemStack(Items.iron_ingot, 2));
		recipes.put(Blocks.gold_ore, new ItemStack(Items.gold_ingot, 2));
		recipes.put(Blocks.diamond_ore, new ItemStack(Items.diamond, 2));
		recipes.put(Blocks.emerald_ore, new ItemStack(Items.emerald, 2));
		recipes.put(Blocks.lapis_ore, new ItemStack(Items.dye, 12, 4));
		recipes.put(Blocks.redstone_ore, new ItemStack(Items.redstone, 8));
		recipes.put(Blocks.quartz_ore, new ItemStack(Items.quartz, 2));
		recipes.put(InitBlocks.infused_crystal_ore, new ItemStack(InitItems.infused_crystal, 4));
		recipes.put(InitBlocks.infused_azurite_ore, new ItemStack(InitItems.infused_azurite, 4));
	}
	
	public TileEntityOreEnricher() {
		maxEnergy = 5000;
		maxProgress = 100;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (!worldObj.isRemote) {
			
			if (canSmelt()) progress ++;
			else resetProgress();
			
			if (isDone()) {
				resetProgress();
				EnergyUtil.subtractEnergy(this, energyCost);
				ItemStack output = recipes.get(Block.getBlockFromItem(slots[0].getItem())).copy();
				
				if (slots[1] == null) slots[1] = output;
				else slots[1].stackSize += output.stackSize;
				
				decrStackSize(0, 1);
			}
		}
	}
	
	private boolean canSmelt() {
			
		if (slots[0] == null) return false;
	
		boolean b1 = Block.getBlockFromItem(slots[0].getItem()) != null && recipes.containsKey(Block.getBlockFromItem(slots[0].getItem()));
		
		ItemStack output = recipes.get(Block.getBlockFromItem(slots[0].getItem()));
		
		boolean b2 = slots[1] == null || (output != null && slots[1] != null && slots[1].getItem() == output.getItem());
		boolean b3 = slots[1] == null || (slots[1].getMaxStackSize() - slots[1].stackSize) >= output.stackSize;
		boolean b4 = energy >= energyCost;
				
		return b1 && b2 && b3 && b4;
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
		return true;
	}
}

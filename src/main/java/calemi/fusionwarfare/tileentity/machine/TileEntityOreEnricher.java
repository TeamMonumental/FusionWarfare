package calemi.fusionwarfare.tileentity.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityOreEnricher extends TileEntityBase {

	public static Map<ItemStack, ItemStack> recipes = new HashMap<ItemStack, ItemStack>();
	
	public static int energyCost = 50;
	
	public TileEntityOreEnricher() {
		maxEnergy = 5000;
		maxProgress = 100;
	}

	static {
		
		recipes.put(new ItemStack(Blocks.coal_ore), new ItemStack(Items.coal, 2));
		recipes.put(new ItemStack(Blocks.diamond_ore), new ItemStack(Items.diamond, 2));
		recipes.put(new ItemStack(Blocks.emerald_ore), new ItemStack(Items.emerald, 2));
		recipes.put(new ItemStack(Blocks.lapis_ore), new ItemStack(Items.dye, 4, 4));
		recipes.put(new ItemStack(Blocks.redstone_ore), new ItemStack(Items.redstone, 4));
		recipes.put(new ItemStack(Blocks.quartz_ore), new ItemStack(Items.quartz, 2));
		recipes.put(new ItemStack(InitBlocks.infused_crystal_ore), new ItemStack(InitItems.infused_crystal, 2));
		recipes.put(new ItemStack(InitBlocks.infused_azurite_ore), new ItemStack(InitItems.infused_azurite, 2));
				
		for (String name : OreDictionary.getOreNames()) {
			
			if (name.startsWith("ore")) {			
				
				String ingot = name.replaceFirst("ore", "ingot");
				
				for (int i = 0; i < OreDictionary.getOres(name).size(); i++) {				
					
					if (!OreDictionary.getOres(ingot).isEmpty()) {
						
						ItemStack oreStack = OreDictionary.getOres(name).get(i).copy();
												
						ItemStack ingotStack = OreDictionary.getOres(ingot).get(0).copy();
						ingotStack.stackSize = 2;						
						
						recipes.put(oreStack, ingotStack);
					}
				}
			}
		}
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (!worldObj.isRemote) {
			
			if (canSmelt()) progress++;
			else resetProgress();
			
			if (isDone()) {
				
				resetProgress();
				EnergyUtil.subtractEnergy(this, energyCost);
				ItemStack output = getRecipe(slots[0]).copy();
				
				if (slots[1] == null) slots[1] = output;
				else slots[1].stackSize += output.stackSize;
				
				decrStackSize(0, 1);
			}
		}
	}
	
	private boolean canSmelt() {
			
		if (slots[0] == null) return false;
	
		boolean b1 = slots[0] != null && getRecipe(slots[0]) != null;
		
		ItemStack output = getRecipe(slots[0]);
		
		boolean b2 = slots[1] == null || (output != null && slots[1] != null && slots[1].getItem() == output.getItem());
		boolean b3 = slots[1] == null || (output != null && (slots[1].getMaxStackSize() - slots[1].stackSize) >= output.stackSize);
		boolean b4 = energy >= energyCost;
				
		return b1 && b2 && b3 && b4;
	}
	
	private ItemStack getRecipe(ItemStack key) {
		
		for (ItemStack stack : recipes.keySet()) {
			
			if (stack.isItemEqual(key)) {
				
				return recipes.get(stack);				
			}
		}	
		
		return null;
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

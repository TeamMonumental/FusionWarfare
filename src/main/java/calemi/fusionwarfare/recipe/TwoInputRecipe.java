package calemi.fusionwarfare.recipe;

import net.minecraft.item.ItemStack;

public class TwoInputRecipe {

	public EnumRecipeType recipeType;
	public ItemStack output;
	public ItemStack input1, input2;
	public int progressTime;
	public int energyCost;
	
	public TwoInputRecipe(ItemStack output, ItemStack input1, ItemStack input2, int seconds, int energyCost) {		
		this.output = output;
		this.input1 = input1;
		this.input2 = input2;
		this.progressTime = seconds * 20;
		this.energyCost = energyCost;
	}
	
	public boolean isRecipe(ItemStack in1, ItemStack in2) {
		
		if (in1 == null || in2 == null) return false;
		
		boolean b1 = input1.isItemEqual(in1);
		boolean b2 = input2.isItemEqual(in2);
			
		return b1 && b2 && isSameAmounts(in1, in2);
	}
	
	public boolean isSameAmounts(ItemStack in1, ItemStack in2) {
		
		boolean b1 = in1.stackSize >= input1.stackSize;
		boolean b2 = in2.stackSize >= input2.stackSize;
		
		return b1 && b2;
	}
}

package calemi.fusionwarfare.init.recipe;

import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class InitInfusionFoundryRecipes {

	public static void init() {
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_FOUNDRY, new TwoInputRecipe(new ItemStack(InitItems.steel_ingot, 2), 
				new ItemStack(Items.coal), 				
				new ItemStack(Items.iron_ingot), 
				5, 40));
	
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_FOUNDRY, new TwoInputRecipe(new ItemStack(InitItems.infused_steel_ingot, 2), 
				new ItemStack(InitItems.infused_crystal, 4),
				new ItemStack(InitItems.steel_ingot, 4),
				10, 200));
	
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_FOUNDRY, new TwoInputRecipe(new ItemStack(InitItems.infused_redstone, 2), 
				new ItemStack(Items.redstone, 4), 				
				new ItemStack(Items.iron_ingot, 4), 
				5, 20));
	}	
}

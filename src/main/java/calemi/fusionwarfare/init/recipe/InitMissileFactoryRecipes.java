package calemi.fusionwarfare.init.recipe;

import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import net.minecraft.item.ItemStack;

public class InitMissileFactoryRecipes {

	public static void init() {
		
		//#-#-#-#-#-VELOCITY-#-#-#-#-#\\
		
		TwoInputRecipeRegistry.register(EnumRecipeType.MISSILE_FACTORY, new TwoInputRecipe(new ItemStack(InitItems.velocity_missile_T1), 
				new ItemStack(InitItems.evaporation_cell), 				
				new ItemStack(InitItems.missile_module_1), 
				10, 500));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.MISSILE_FACTORY, new TwoInputRecipe(new ItemStack(InitItems.velocity_missile_T2), 
				new ItemStack(InitItems.evaporation_cell), 				
				new ItemStack(InitItems.missile_module_2), 
				20, 1000));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.MISSILE_FACTORY, new TwoInputRecipe(new ItemStack(InitItems.velocity_missile_T3), 
				new ItemStack(InitItems.evaporation_cell), 				
				new ItemStack(InitItems.missile_module_3), 
				30, 1500));
		
		//#-#-#-#-#-BREACHING-#-#-#-#-#\\
		
		TwoInputRecipeRegistry.register(EnumRecipeType.MISSILE_FACTORY, new TwoInputRecipe(new ItemStack(InitItems.breaching_missile_T1), 
				new ItemStack(InitItems.steel.pickaxe), 				
				new ItemStack(InitItems.missile_module_1), 
				10, 500));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.MISSILE_FACTORY, new TwoInputRecipe(new ItemStack(InitItems.breaching_missile_T2), 
				new ItemStack(InitItems.steel.pickaxe), 				
				new ItemStack(InitItems.missile_module_2), 
				20, 1000));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.MISSILE_FACTORY, new TwoInputRecipe(new ItemStack(InitItems.breaching_missile_T3), 
				new ItemStack(InitItems.steel.pickaxe), 				
				new ItemStack(InitItems.missile_module_3), 
				30, 1500));
		
		//#-#-#-#-#-EMP-#-#-#-#-#\\
		
		TwoInputRecipeRegistry.register(EnumRecipeType.MISSILE_FACTORY, new TwoInputRecipe(new ItemStack(InitItems.emp_missile_T1), 
				new ItemStack(InitBlocks.network_controller_1), 				
				new ItemStack(InitItems.missile_module_1), 
				10, 500));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.MISSILE_FACTORY, new TwoInputRecipe(new ItemStack(InitItems.emp_missile_T2), 
				new ItemStack(InitBlocks.network_controller_1), 				
				new ItemStack(InitItems.missile_module_2), 
				20, 1000));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.MISSILE_FACTORY, new TwoInputRecipe(new ItemStack(InitItems.emp_missile_T3), 
				new ItemStack(InitBlocks.network_controller_1), 				
				new ItemStack(InitItems.missile_module_3), 
				30, 1500));
	}
}

package calemi.fusionwarfare.init.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;

public class InitInfusionTableRecipes {

	public static void init() {
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.steel_ingot), 
				new ItemStack(Items.coal), 				
				new ItemStack(Items.iron_ingot), 
				5, 60));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.infused_steel_ingot), 
				new ItemStack(InitItems.infused_crystal, 4),
				new ItemStack(InitItems.steel_ingot, 4),
				10, 300));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.infused_redstone), 
				new ItemStack(Items.redstone, 4), 				
				new ItemStack(Items.iron_ingot, 4), 
				5, 30));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.steel_plate), 
				new ItemStack(InitItems.steel_ingot), 				
				new ItemStack(InitItems.steel_ingot), 
				5, 60));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.basic_chip), 
				new ItemStack(Items.diamond), 				
				new ItemStack(InitItems.infused_circuit),
				30, 750));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.advanced_chip), 
				new ItemStack(InitItems.basic_chip), 				
				new ItemStack(InitItems.basic_chip), 
				60, 1500));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.hyper_chip), 
				new ItemStack(InitItems.advanced_chip), 				
				new ItemStack(InitItems.advanced_chip),
				60 * 5, 5000));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.lightning_rod), 
				new ItemStack(InitItems.infused_crystal), 				
				new ItemStack(Items.blaze_rod), 
				420, 5000));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.sturdy_handle), 
				new ItemStack(InitItems.hyper_chip, 1), 				
				new ItemStack(InitItems.lightning_rod, 1), 
				20, 500));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.advanced_infused_catalyst), 
				new ItemStack(InitItems.advanced_chip), 				
				new ItemStack(InitItems.infused_catalyst), 
				60 * 2, 3000));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.evaporation_cell), 
				new ItemStack(InitItems.infused_crystal, 4), 				
				new ItemStack(Blocks.obsidian), 
				30, 200));
			
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitItems.charged_seeds, 64), 
				new ItemStack(InitItems.infused_azurite, 32), 				
				new ItemStack(Items.wheat_seeds, 32), 
				30, 1000));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitBlocks.concrete, 8), 
				new ItemStack(Blocks.sand, 8),
				new ItemStack(Blocks.gravel, 8),
				10, 200));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitBlocks.reinforced_glass, 16), 
				new ItemStack(InitItems.steel_plate, 4), 				
				new ItemStack(Blocks.glass, 4), 
				40, 400));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(InitBlocks.network_gate), 
				new ItemStack(Items.repeater), 				
				new ItemStack(InitBlocks.network_cable), 
				3, 100));
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(Blocks.brick_block), 
				new ItemStack(Items.brick), 				
				new ItemStack(Items.brick), 
				1, 5));	
		
		TwoInputRecipeRegistry.register(EnumRecipeType.INFUSION_TABLE, new TwoInputRecipe(new ItemStack(Blocks.quartz_block), 
				new ItemStack(Items.quartz), 				
				new ItemStack(Items.quartz), 
				1, 5));		
	}
}

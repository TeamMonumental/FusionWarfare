package calemi.fusionwarfare.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class TwoInputRecipeRegistry {

	private static ArrayList<TwoInputRecipe> recipes = new ArrayList<TwoInputRecipe>();
	
	public static void register(EnumRecipeType recipeType, TwoInputRecipe recipe) {		
		recipe.recipeType = recipeType;
		recipes.add(recipe);
	}
	
	public static ArrayList<TwoInputRecipe> getRecipes(EnumRecipeType recipeType) {
		
		ArrayList<TwoInputRecipe> newRecipes = new ArrayList<TwoInputRecipe>();
		
		for (TwoInputRecipe recipe : recipes) {			
			
			if (recipe.recipeType == recipeType) {			
				
				newRecipes.add(recipe);				
			}
		}
		
		return newRecipes;
	}
	
	public static ArrayList<TwoInputRecipe> getAllRecipesExcept(EnumRecipeType recipeType) {				
		
		ArrayList<TwoInputRecipe> newRecipes = new ArrayList<TwoInputRecipe>();
		
		for (TwoInputRecipe recipe : recipes) {			
			
			if (recipe.recipeType != recipeType) {			
				
				newRecipes.add(recipe);				
			}
		}
		
		return newRecipes;
	}
	
	public static int getRecipeIndex(EnumRecipeType recipeType, TwoInputRecipe recipe) {
		
		return getRecipes(recipeType).indexOf(recipe);
	}
}

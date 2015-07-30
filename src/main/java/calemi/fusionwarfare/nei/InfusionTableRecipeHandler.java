package calemi.fusionwarfare.nei;

import java.awt.Rectangle;

import calemi.fusionwarfare.gui.GuiTwoInputs;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public class InfusionTableRecipeHandler extends TwoInputRecipeHandlerBase {

	public InfusionTableRecipeHandler() {
		recipeType = EnumRecipeType.INFUSION_TABLE;
	}		
}

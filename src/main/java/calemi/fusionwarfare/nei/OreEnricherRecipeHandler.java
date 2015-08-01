package calemi.fusionwarfare.nei;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.nei.TwoInputRecipeHandlerBase.CachedInfusionRecipe;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.tileentity.machine.TileEntityOreEnricher;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class OreEnricherRecipeHandler extends TemplateRecipeHandler {	
	
	@Override
	public String getRecipeName() {
		return "Ore Enricher";
	}
	
	@Override
	public String getGuiTexture() {
		return Reference.MOD_ID + ":textures/gui/nei_one_input.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return "Ore Enricher";
	}
		
	@Override
	public void loadCraftingRecipes(ItemStack result) {
		
		Map<ItemStack, ItemStack> recipes = TileEntityOreEnricher.recipes;
		
		for (ItemStack input : recipes.keySet()) {
			
			ItemStack output = recipes.get(input);
			
			if (output.isItemEqual(result)) {
				
				arecipes.add(new CachedOreEnricherRecipe(input, output));				
			}
		}
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		
		Map<ItemStack, ItemStack> recipes = TileEntityOreEnricher.recipes;
		
		if (outputId == getOverlayIdentifier()) {		
			
			for (ItemStack input : recipes.keySet()) {			
				
				ItemStack output = recipes.get(input);
				
				arecipes.add(new CachedOreEnricherRecipe(input, output));								
			}
		}
		
		if (outputId == "item") {
			
			loadCraftingRecipes((ItemStack)results[0]);			
		}			
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
			
		Map<ItemStack, ItemStack> recipes = TileEntityOreEnricher.recipes;
		
		for (ItemStack input : recipes.keySet()) {
			
			if (input.getItem() == ingredient.getItem()) {
				
				ItemStack output = recipes.get(input);
				arecipes.add(new CachedOreEnricherRecipe(input, output));				
			}
		}
	}
		
	@Override
	public void drawBackground(int recipe) {		
		super.drawBackground(recipe);
		
		GuiDraw.changeTexture(Reference.MOD_ID + ":textures/gui/gui_textures.png");	
		
		int scaledE = TileEntityOreEnricher.energyCost * 50 / 10000;
		GuiDraw.drawTexturedModalRect(19, 56 - scaledE + 1, 49, 7, 14, scaledE);
		
		drawProgressBar(85 - 5, 37 - 11, 78, 7, 23, 12, 23, 0);		
	}
	
	@Override
	public void drawForeground(int recipe) {
		super.drawForeground(recipe);		
	}
	
	@Override
	public List<String> handleTooltip(GuiRecipe gui, List<String> currenttip, int recipe) {
				
        if (GuiContainerManager.shouldShowTooltip(gui) && currenttip.size() == 0) {
        	
            Point offset = gui.getRecipePosition(recipe);
            Point pos = GuiDraw.getMousePosition();
            Point relMouse = new Point(pos.x - (gui.width - 176) / 2 - offset.x, pos.y - (gui.height - 166) / 2 - offset.y);
            
            Rectangle energyRect = new Rectangle(24 - 5, 18 - 11, 14, 50);
            
            if (energyRect.contains(relMouse)) {

            	currenttip.add("Energy Cost: " + TileEntityOreEnricher.energyCost);
                return currenttip;
            }
            
            Rectangle progressRect = new Rectangle(85 - 5, 37 - 11, 23, 12);
            
            if (progressRect.contains(relMouse)) {

            	currenttip.add("Time: " + (100 / 20) + "s");
                return currenttip;
            }
        }
		
		return super.handleTooltip(gui, currenttip, recipe);
	}
	
	public class CachedOreEnricherRecipe extends CachedRecipe {

		private PositionedStack input;
		private PositionedStack output;
				
		public CachedOreEnricherRecipe(ItemStack input, ItemStack output) {
			this.input = new PositionedStack(input, 61 - 5, 35 - 11);
			this.output = new PositionedStack(output, 116, 24);
		}
		
		@Override
		public PositionedStack getResult() {
			return output;
		}		
		
		@Override
		public PositionedStack getIngredient() {
			return input;
		}
	}	
}

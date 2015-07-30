package calemi.fusionwarfare.nei;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.gui.GuiScreenBase;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.nei.TwoInputRecipeHandlerBase.CachedInfusionRecipe;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.recipe.GuiRecipe;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class TwoInputRecipeHandlerBase extends TemplateRecipeHandler {	
	
	public EnumRecipeType recipeType;
	
	@Override
	public String getRecipeName() {
		return recipeType.name;
	}
	
	@Override
	public String getGuiTexture() {
		return Reference.MOD_ID + ":textures/gui/nei.png";
	}
	
	@Override
	public String getOverlayIdentifier() {
		return recipeType.name;
	}
		
	@Override
	public void loadCraftingRecipes(ItemStack result) {
		
		for (TwoInputRecipe recipe : TwoInputRecipeRegistry.getRecipes(recipeType)) {		
			
			if (result != null && result.isItemEqual(recipe.output)) {			
				
				arecipes.add(new CachedInfusionRecipe(recipe));
			}
		}
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		
		if (outputId == getOverlayIdentifier()) {		
			
			for (TwoInputRecipe recipe : TwoInputRecipeRegistry.getRecipes(recipeType)) {			
				
				arecipes.add(new CachedInfusionRecipe(recipe));								
			}
		}
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient) {

		for (TwoInputRecipe recipe : TwoInputRecipeRegistry.getRecipes(recipeType)) {		
			
			if (ingredient != null && (ingredient.isItemEqual(recipe.input1) || ingredient.isItemEqual(recipe.input2))) {			
				
				arecipes.add(new CachedInfusionRecipe(recipe));
			}
		}
	}
		
	@Override
	public void drawBackground(int recipe) {		
		super.drawBackground(recipe);
		
		CachedInfusionRecipe currentRecipe = (CachedInfusionRecipe) arecipes.get(recipe);
		
		GuiDraw.changeTexture(Reference.MOD_ID + ":textures/gui/gui_textures.png");	
		
		int scaledE = currentRecipe.energy * 50 / 10000;
		GuiDraw.drawTexturedModalRect(19, 56 - scaledE + 1, 49, 7, 14, scaledE);
		
		drawProgressBar(85 - 5, 37 - 11, 78, 7, 23, 12, 23, 0);		
	}
	
	@Override
	public void drawForeground(int recipe) {
		super.drawForeground(recipe);
		
	}
	
	@Override
	public List<String> handleTooltip(GuiRecipe gui, List<String> currenttip, int recipe) {
				
		CachedInfusionRecipe rec = (CachedInfusionRecipe) arecipes.get(recipe);
		
        if (GuiContainerManager.shouldShowTooltip(gui) && currenttip.size() == 0) {
        	
            Point offset = gui.getRecipePosition(recipe);
            Point pos = GuiDraw.getMousePosition();
            Point relMouse = new Point(pos.x - (gui.width - 176) / 2 - offset.x, pos.y - (gui.height - 166) / 2 - offset.y);
            
            Rectangle energyRect = new Rectangle(24 - 5, 18 - 11, 14, 50);
            
            if (energyRect.contains(relMouse)) {

            	currenttip.add("Energy Cost: " + rec.energy);
                return currenttip;
            }
            
            Rectangle progressRect = new Rectangle(85 - 5, 37 - 11, 23, 12);
            
            if (progressRect.contains(relMouse)) {

            	currenttip.add("Time: " + (rec.time / 20) + "s");
                return currenttip;
            }
        }
		
		return super.handleTooltip(gui, currenttip, recipe);
	}
	
	public class CachedInfusionRecipe extends CachedRecipe {

		private List<PositionedStack> inputs = new ArrayList<PositionedStack>();
		private PositionedStack output;
		public int energy;
		public int time;
		
		public CachedInfusionRecipe(TwoInputRecipe recipe) {
			
			inputs.add(new PositionedStack(recipe.input1, 56, 13));
			inputs.add(new PositionedStack(recipe.input2, 56, 35));
			output = new PositionedStack(recipe.output, 116, 24);
			energy = recipe.energyCost;
			time = recipe.progressTime;
		}
		
		@Override
		public PositionedStack getResult() {
			return output;
		}
		
		@Override
		public List<PositionedStack> getIngredients() {
			return inputs;
		}
	}
}

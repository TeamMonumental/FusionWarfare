package calemi.fusionwarfare.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.item.ItemMissile;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiTwoInputsRecipe extends GuiScreenBase {

	private GuiFusionButton backButton;
	private GuiFusionButton forwardButton;
	private GuiFusionButton doneButton;
	
	private EntityPlayer player;
	
	private int currentRecipe;
	private EnumRecipeType recipeType;
	
	public GuiTwoInputsRecipe(EntityPlayer player, EnumRecipeType recipeType, int index) {
		this.player = player;
		this.recipeType = recipeType;
		currentRecipe = index;
	}
	
	@Override
	public String getGuiTextures() {
		return "recipe";
	}

	@Override
	public int getGuiSizeX() {
		return 176;
	}

	@Override
	public int getGuiSizeY() {
		return 93;
	}

	@Override
	public void initGui() {
		super.initGui();

		backButton = new GuiFusionButton(0, getScreenX() + 5, getScreenY() + 72, 32, "<");
		buttonList.add(backButton);
		forwardButton = new GuiFusionButton(1, getScreenX() + 139, getScreenY() + 72, 32, ">");
		buttonList.add(forwardButton);
		doneButton = new GuiFusionButton(2, getScreenX() + 72, getScreenY() + 72, 32, "Done");
		buttonList.add(doneButton);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
		
	@Override
	protected void actionPerformed(GuiButton button) {
		
		if (button.id == 0) {
			currentRecipe--;
		}

		if (button.id == 1) {
			currentRecipe++;
		}

		if (button.id == 2) {
			player.closeScreen();
		}

		if (currentRecipe < 0) {
			currentRecipe = TwoInputRecipeRegistry.getRecipes(recipeType).size() - 1;
		}

		currentRecipe %= TwoInputRecipeRegistry.getRecipes(recipeType).size();
	}
	
	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
			
		int dWheel = Mouse.getDWheel();
				
		if(dWheel > 0 && currentRecipe < (TwoInputRecipeRegistry.getRecipes(recipeType).size() - 1)) {
			currentRecipe++;
		}
		
		if(dWheel < 0 && currentRecipe > 0) {
			currentRecipe--; 
		}		
		
		if (getCurrentRecipe() != null) {

			int i1 = getCurrentRecipe().energyCost * 50 / 10000;
			this.drawTexturedModalRect(getScreenX() + 19, getScreenY() + 69 - i1, 176, 50 - i1, 14, i1 + 1);
		}
		
		for (int i = -4; i <= 4; i++) {
			
			TwoInputRecipe recipe = getCurrentRecipe(i);

			if (recipe != null) {
				drawItemStack(recipe.output, (getGuiSizeX() / 2) - 8 + (i * 22), -20);
			}
		}
		
		if (getCurrentRecipe() != null) {
			
			drawItemStack(getCurrentRecipe().input1, 56, 25);
			drawItemStack(getCurrentRecipe().input2, 56, 47);
			drawItemStack(getCurrentRecipe().output, 116, 36);
			
			drawLeftInfoTextBar(getCurrentRecipe().input1.stackSize + "x " + getCurrentRecipe().input1.getDisplayName(), 0);
			drawLeftInfoTextBar(getCurrentRecipe().input2.stackSize + "x " + getCurrentRecipe().input2.getDisplayName(), 1);
			
			drawRightInfoTextBar("Energy Cost: " + getCurrentRecipe().energyCost, 0);
			drawRightInfoTextBar("Time: " + (getCurrentRecipe().progressTime / 20) + "s", 1);		
			
			String missileTier = recipeType == EnumRecipeType.MISSILE_FACTORY ? " [Tier " + ((ItemMissile)getCurrentRecipe().output.getItem()).missileType.event.tier + "]" : "";
			
			drawCenteredStringBox(getCurrentRecipe().output.stackSize + "x " + getCurrentRecipe().output.getDisplayName() + missileTier, getGuiSizeX() / 2, -40);
		}	
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {

	}	

	private TwoInputRecipe getCurrentRecipe() {
		return TwoInputRecipeRegistry.getRecipes(recipeType).get(currentRecipe);
	}

	private TwoInputRecipe getCurrentRecipe(int index) {
		try {
			return TwoInputRecipeRegistry.getRecipes(recipeType).get(currentRecipe + index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}		
}	
	
	/*
	private static final ResourceLocation guiTextures = new ResourceLocation(Reference.MOD_ID + ":textures/gui/recipe.png");

	private GuiFusionButton backButton;
	private GuiFusionButton forwardButton;
	private GuiFusionButton doneButton;

	private EntityPlayer player;

	private int xSize = 176;
	private int ySize = 93;

	private int currentRecipe;
	
	private EnumRecipeType recipeType;

	public GuiTwoInputsRecipe(EntityPlayer player, EnumRecipeType recipeType) {
		this.player = player;
		this.recipeType = recipeType;
	}

	@Override
	public void initGui() {
		super.initGui();

		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

		backButton = new GuiFusionButton(0, k + 5, l + 72, 32, "<");
		buttonList.add(backButton);
		forwardButton = new GuiFusionButton(1, k + 139, l + 72, 32, ">");
		buttonList.add(forwardButton);
		doneButton = new GuiFusionButton(2, k + 72, l + 72, 32, "Done");
		buttonList.add(doneButton);
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	protected void actionPerformed(GuiButton button) {

		if (button.id == 0) {
			currentRecipe--;
		}

		if (button.id == 1) {
			currentRecipe++;
		}

		if (button.id == 2) {
			player.closeScreen();
		}

		if (currentRecipe < 0) {
			currentRecipe = TwoInputRecipeRegistry.getRecipes(recipeType).size() - 1;
		}

		currentRecipe %= TwoInputRecipeRegistry.getRecipes(recipeType).size();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {

		updateScreen();
		
		this.drawDefaultBackground();

		this.mc.getTextureManager().bindTexture(guiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		if (getCurrentRecipe() != null) {

			int i1 = getCurrentRecipe().energyCost * 50 / 10000;
			this.drawTexturedModalRect(k + 19, l + 69 - i1, 176, 50 - i1, 14, i1 + 1);
		}

		if (getCurrentRecipe() != null) {

			drawCreativeTabHoveringText(getCurrentRecipe().output.getDisplayName(), k + 79 + 90, l + 18);
			drawCreativeTabHoveringText("Energy Cost: " + getCurrentRecipe().energyCost, k + 79 + 90, l + 18 + 20);
			drawCreativeTabHoveringText("Time: " + (getCurrentRecipe().progressTime / 20) + "s", k + 79 + 90, l + 18 + 40);

			drawItemStack(getCurrentRecipe().input1, k + 56, l + 25, mouseX, mouseY);
			drawItemStack(getCurrentRecipe().input2, k + 56, l + 47, mouseX, mouseY);
			drawItemStack(getCurrentRecipe().output, k + 116, l + 36, mouseX, mouseY);
		}

		for (int i = -4; i <= 4; i++) {
			TwoInputRecipe recipe = getCurrentRecipe(i);

			if (recipe != null) {
				drawItemStack(recipe.output, k + (xSize / 2) - 8 + (i * 22), l - 20, mouseX, mouseY);
			}
		}

		super.drawScreen(mouseX, mouseY, f);
	}

	private TwoInputRecipe getCurrentRecipe() {
		return TwoInputRecipeRegistry.getRecipes(recipeType).get(currentRecipe);
	}

	private TwoInputRecipe getCurrentRecipe(int index) {
		try {
			return TwoInputRecipeRegistry.getRecipes(recipeType).get(currentRecipe + index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	private void drawItemStack(ItemStack stack, int x, int y, int mouseX, int mouseY) {
	
		RenderHelper.enableGUIStandardItemLighting();
		itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), stack, x, y);
		if (stack.stackSize > 1) itemRender.renderItemOverlayIntoGUI(fontRendererObj, this.mc.getTextureManager(), stack, x, y, Integer.toString(stack.stackSize));
	}
	
	private void drawTextBox(ItemStack stack, int x, int y, int mouseX, int mouseY) {
		
		if (isMouseOverBox(x, y, 16, 16, mouseX, mouseY)) {
			this.drawGradientRect(x, y, x + 16, y + 16, -2130706433, -2130706433);
			drawCreativeTabHoveringText(stack.getDisplayName(), mouseX, mouseY);			
		}
	}			
	
	protected boolean isMouseOverBox(int x, int y, int boxX, int boxY, int mouseX, int mouseY) {		
		return mouseX >= x - 1 && mouseX < x + boxX + 1 && mouseY >= y - 1 && mouseY < y + boxY + 1;
	}
}*/
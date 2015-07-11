package calemi.fusionwarfare.gui;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.gui.recipe.GuiTwoInputsRecipe;
import calemi.fusionwarfare.inventory.ContainerTwoInputs;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import calemi.fusionwarfare.inventory.ContainerOneInput;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityTwoInputs;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiTwoInputs extends GuiContainerBase {

	private GuiFusionButton recipeButton;

	private String name;
	private EnumRecipeType recipeType;
	
	public GuiTwoInputs(EntityPlayer player, TileEntityBase tileEntity, EnumRecipeType recipeType, String name) {
		super(new ContainerTwoInputs(player, tileEntity), player, tileEntity);
		this.name = name;
		this.recipeType = recipeType;
	}

	@Override
	public void initGui() {
		super.initGui();

		recipeButton = new GuiFusionButton(0, getScreenX() + 155, getScreenY() + 5, 16, "?");
		buttonList.add(recipeButton);
	}

	@Override
	protected void actionPerformed(GuiButton button) {

		if (button.id == 0) {

			FMLClientHandler.instance().displayGuiScreen(player, new GuiTwoInputsRecipe(player, recipeType, 0));
		}
	}
	
	@Override
	public String getGuiTextures() {
		return "two_inputs";
	}

	@Override
	public String getGuiTitle() {
		return name;
	}
	
	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		TileEntityTwoInputs tileEntityTwoInputs = (TileEntityTwoInputs)tileEntity;

		if (tileEntityTwoInputs.currentRecipe != null) {
			
			ItemStack output = tileEntityTwoInputs.currentRecipe.output;
			
			drawRightInfoTextBar("Creating:", 0);
			drawRightInfoTextBar(output.stackSize + "x " + output.getDisplayName(), 1);
			drawRightInfoTextBar((tileEntityTwoInputs.currentRecipe.energyCost > tileEntityTwoInputs.energy ? EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD : "") + "Energy Cost: " + tileEntityTwoInputs.currentRecipe.energyCost, 2);
		}
		
		drawSmallFuelBar(19, 69);
		drawSmallProgBar(79, 38);
	}
	
	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawSmallFuelBarTextBox(19, 69, mouseX, mouseY);
		drawSmallProgBarTextBox(79, 38, mouseX, mouseY);
	}
}

	/*
	private static final ResourceLocation guiTextures = new ResourceLocation(Reference.MOD_ID + ":textures/gui/two_inputs.png");

	private GuiFusionButton recipeButton;
	private EntityPlayer player;
	private TileEntityInfusionTable tileentity;

	public GuiInfusionTable(EntityPlayer player, TileEntityInfusionTable tileentity) {
		super(new ContainerTwoInputs(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}

	@Override
	public void initGui() {
		super.initGui();

		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;

		recipeButton = new GuiFusionButton(0, k + 155, l + 5, 16, "?");
		buttonList.add(recipeButton);
	}

	@Override
	protected void actionPerformed(GuiButton button) {

		if (button.id == 0) {

			player.openGui(FusionWarfare.instance, FusionWarfare.guiIDInfusionTableRecipe, player.worldObj, (int) player.posX, (int) player.posZ, (int) player.posZ);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {

		updateScreen();

		this.mc.getTextureManager().bindTexture(guiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		// (teleX, teleY, originX, originY, increaseWidth, increaseHeight)

		// Fuel Bar
		int i1 = this.tileentity.getCurrentEnergyScaled(49);

		this.drawTexturedModalRect(k + 19, l + 68 - i1, 176, 61 - i1, 14, i1 + 1);
	
		// Progress Bar
		int i2 = this.tileentity.getCurrentProgressScaled(24);
		this.drawTexturedModalRect(k + 79, l + 38, 176, 0, i2 + 1, 12);

		drawCreativeTabHoveringText("Energy: " + tileentity.energy + "/" + tileentity.maxEnergy, k + 79 + 90, l + 18);
		drawCreativeTabHoveringText("Progress: " + tileentity.progress * 100 / tileentity.maxProgress + "%", k + 79 + 90, l + 18 + 20);
		drawCreativeTabHoveringText("Creating:", k + 79 + 90, l + 18 + 40);

		if (tileentity.currentRecipe != null) {
			drawCreativeTabHoveringText(tileentity.currentRecipe.output.getDisplayName(), k + 79 + 90, l + 18 + 60);
			drawCreativeTabHoveringText(tileentity.currentRecipe.energyCost > tileentity.energy ? EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "Energy Cost: " + tileentity.currentRecipe.energyCost : "Energy Cost: " + tileentity.currentRecipe.energyCost, k + 79 + 90, l + 18 + 80);
		}
		
		//drawCenteredString(fontRendererObj, "Infusion Table", k + 88, l + 6, 4210752);
		drawCenteredStringWithoutShadow("Infusion Table", 88, 6);
		
		if (isMouseOverBox(k + 19, l + 19, 14, 50, mouseX, mouseY)) {
			drawCreativeTabHoveringText("Energy: " + tileentity.energy + "/" + tileentity.maxEnergy, mouseX, mouseY);
		}
	}
	
	public void drawCenteredStringWithoutShadow(String text, int x, int y) {
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		mc.fontRenderer.drawString(text, k + (x - (fontRendererObj.getStringWidth(text) / 2)), l + y, 4210752);	
	}

	protected boolean isMouseOverBox(int x, int y, int boxX, int boxY, int mouseX, int mouseY) {		
		return mouseX >= x - 1 && mouseX < x + boxX + 1 && mouseY >= y - 1 && mouseY < y + boxY + 1;
	}
}*/
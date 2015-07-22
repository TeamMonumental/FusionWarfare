package calemi.fusionwarfare.gui;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
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
	
	public GuiTwoInputs(EntityPlayer player, TileEntityBase tileEntity, EnumRecipeType recipeType) {
		super(new ContainerTwoInputs(player, tileEntity), player, tileEntity);
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
		return recipeType.name;
	}
	
	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		drawSmallFuelBar(19, 69);
		drawSmallProgBar(79, 38);
		
		TileEntityTwoInputs tileEntityTwoInputs = (TileEntityTwoInputs)tileEntity;

		if (tileEntityTwoInputs.currentRecipe != null) {
			
			ItemStack output = tileEntityTwoInputs.currentRecipe.output;
			
			drawInfoTextBar("Creating:", 0);
			drawInfoTextBar(output.stackSize + "x " + output.getDisplayName(), 1);
			drawInfoTextBar((tileEntityTwoInputs.currentRecipe.energyCost > tileEntityTwoInputs.energy ? EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD : "") + "Energy Cost: " + tileEntityTwoInputs.currentRecipe.energyCost, 2);
		}	
	}
	
	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawSmallFuelBarTextBox(19, 69, mouseX, mouseY);
		drawSmallProgBarTextBox(79, 38, mouseX, mouseY);
	}
}
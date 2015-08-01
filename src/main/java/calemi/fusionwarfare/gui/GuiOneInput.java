package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.gui.button.GuiFusionButton;
import calemi.fusionwarfare.inventory.ContainerOneInput;
import calemi.fusionwarfare.inventory.ContainerTwoInputs;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityEnergeticFurnace;
import calemi.fusionwarfare.tileentity.machine.TileEntityOreEnricher;
import codechicken.nei.recipe.GuiCraftingRecipe;
import cpw.mods.fml.common.Loader;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;

public class GuiOneInput extends GuiContainerBase {

	private GuiFusionButton recipeButton;
	private String name;
	
	public GuiOneInput(EntityPlayer player, TileEntityBase tileEntity, String name) {
		super(new ContainerOneInput(player, tileEntity), player, tileEntity);
		this.name = name;
	}
	
	@Override
	public void initGui() {
		super.initGui();

		if (Loader.isModLoaded("NotEnoughItems")) {		
			recipeButton = new GuiFusionButton(0, getScreenX() + 155, getScreenY() + 5, 16, "?");
			buttonList.add(recipeButton);
		}
	}

	@Override
	protected void actionPerformed(GuiButton button) {

		if (button.id == 0) {
			
			if (tileEntity instanceof TileEntityEnergeticFurnace) {
				GuiCraftingRecipe.openRecipeGui("Energetic Furnace", new Object[0]);		
			}
			
			if (tileEntity instanceof TileEntityOreEnricher) {
				GuiCraftingRecipe.openRecipeGui("Ore Enricher", new Object[0]);		
			}				
		}
	}

	@Override
	public String getGuiTextures() {
		return "one_input";
	}

	@Override
	public String getGuiTitle() {
		return name;
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawSmallFuelBar(19, 69);
		drawSmallProgBar(79, 38);
	}
	
	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawSmallFuelBarTextBox(19, 69, mouseX, mouseY);
		drawSmallProgBarTextBox(79, 38, mouseX, mouseY);
	}	
}
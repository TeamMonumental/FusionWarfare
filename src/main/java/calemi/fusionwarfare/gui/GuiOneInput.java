package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerOneInput;
import calemi.fusionwarfare.inventory.ContainerTwoInputs;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;

public class GuiOneInput extends GuiContainerBase {

	private String name;
	
	public GuiOneInput(EntityPlayer player, TileEntityBase tileEntity, String name) {
		super(new ContainerOneInput(player, tileEntity), player, tileEntity);
		this.name = name;
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
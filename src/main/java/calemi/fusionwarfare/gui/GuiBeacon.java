package calemi.fusionwarfare.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.util.ResourceLocation;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.tileentity.TileEntityBase;

public class GuiBeacon extends GuiContainerBase {

	private String name;
	
	public GuiBeacon(EntityPlayer player, TileEntityBase tileEntity, String name) {
		super(new ContainerEnergyTank(player, tileEntity), player, tileEntity);
		this.name = name;
	}

	@Override
	public int getScreenYOffset() {
		return 15;
	}
	
	@Override
	public String getGuiTextures() {
		return "beacon";
	}

	@Override
	public String getGuiTitle() {
		return name;
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawLargeFuelBar(63, 80);
		drawLongProgBar(7, 86);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawLargeFuelBarTextBox(63, 80, mouseX, mouseY);
		drawLongProgBarTextBox(7, 86, mouseX, mouseY);
	}
}
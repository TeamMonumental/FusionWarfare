package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerMiningUnit;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityMiningUnit;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiMiningUnit extends GuiContainerBase {

	public GuiMiningUnit(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerMiningUnit(player, tileEntity), player, tileEntity);
	}
	
	@Override
	public String getGuiTextures() {
		return "mining_unit";
	}

	@Override
	public String getGuiTitle() {
		return "Mining Unit";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawSmallFuelBar(8, 69);
		drawLongProgBar(7, 72);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawSmallFuelBarTextBox(8, 69, mouseX, mouseY);
		drawLongProgBarTextBox(7, 72, mouseX, mouseY);
	}	
}
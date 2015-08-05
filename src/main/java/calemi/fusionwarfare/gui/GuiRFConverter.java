package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class GuiRFConverter extends GuiContainerBase {

	public GuiRFConverter(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerEnergyTank(player, tileEntity), player, tileEntity);
	}

	@Override
	public String getGuiTextures() {
		return "rf_converter";
	}

	@Override
	public String getGuiTitle() {
		return "RF Converter";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		
	}
}

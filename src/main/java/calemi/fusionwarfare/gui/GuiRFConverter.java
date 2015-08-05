package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.inventory.ContainerRFConverter;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityRFConverter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiRFConverter extends GuiContainerBase {

	public GuiRFConverter(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerRFConverter(player, tileEntity), player, tileEntity);
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
		drawSmallFuelBar(19, 69);
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/rf_converter.png"));
		
		TileEntityRFConverter tileEntityRF = (TileEntityRFConverter)tileEntity;
		
		int scaledFuel = this.tileEntity.getCurrentEnergyScaled(50);
			
		this.drawTexturedModalRect(getScreenX() + 143, (getScreenY() + 69) - scaledFuel, 49, 57 - scaledFuel, 14, scaledFuel + 1);		
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawSmallFuelBarTextBox(19, 69, mouseX, mouseY);
	}
}

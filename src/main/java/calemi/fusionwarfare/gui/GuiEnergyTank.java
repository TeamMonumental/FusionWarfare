package calemi.fusionwarfare.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;

public class GuiEnergyTank extends GuiContainerBase {

	private String name;
	boolean hasProgBar;
	
	public GuiEnergyTank(EntityPlayer player, TileEntityBase tileEntity, String name, boolean hasProgBar) {
		super(new ContainerEnergyTank(player, tileEntity), player, tileEntity);
		this.name = name;
		this.hasProgBar = hasProgBar;
	}
	
	@Override
	public int getScreenYOffset() {
		return 15;
	}

	@Override
	public String getGuiTextures() {
		return "energy_tank";
	}

	@Override
	public String getGuiTitle() {
		return name;
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		if (tileEntity instanceof TileEntitySecurity && ((TileEntitySecurity)tileEntity).getTeam() != null) drawLeftInfoTextBar(((TileEntitySecurity)tileEntity).teamName, 0);
		
		drawLargeFuelBar(63, 80);	
		
		if (hasProgBar) {
			mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + getGuiTextures() + ".png"));
			drawTexturedModalRect(getScreenX() + 7, getScreenY() + 85, 0, getGuiSizeY(), 163, 9);
			drawLongProgBar(7, 86);
		}
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawLargeFuelBarTextBox(63, 80, mouseX, mouseY);
		if (hasProgBar) drawLongProgBarTextBox(7, 86, mouseX, mouseY);
	}
}
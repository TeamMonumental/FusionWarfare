package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerOverclockedEnergyTank;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiOverclockedEnergyTank extends GuiContainerBase {

	private String name;
	boolean hasProgBar;
	
	public GuiOverclockedEnergyTank(EntityPlayer player, TileEntityEnergyBase tileEntity, String name, int overClockNumber, boolean hasProgBar) {
		super(new ContainerOverclockedEnergyTank(player, tileEntity, overClockNumber), player, tileEntity);
		this.name = name;
		this.hasProgBar = hasProgBar;
	}
	
	@Override
	public int getScreenYOffset() {
		return 15;
	}

	@Override
	public String getGuiTextures() {
		return "overclocked_energy_tank";
	}

	@Override
	public String getGuiTitle() {
		return name;
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
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
package calemi.fusionwarfare.gui;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.api.ISecurity;
import calemi.fusionwarfare.config.FWConfig;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.tileentity.base.TileEntityInventoryBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityTwoInputs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ResourceLocation;

public abstract class GuiContainerBase extends GuiContainer {
	
	TileEntityInventoryBase tileEntity;
	TileEntityEnergyBase tileEntityEnergy;
	
	EntityPlayer player;
	
	public GuiContainerBase(Container container, EntityPlayer player, TileEntityInventoryBase tileEntity) {
		super(container);
		this.tileEntity = tileEntity;
		if (tileEntity instanceof TileEntityEnergyBase) this.tileEntityEnergy = (TileEntityEnergyBase)tileEntity;		
		this.player = player;
		this.xSize = getGuiSizeX();
		this.ySize = getGuiSizeY();
	}
	
	public abstract String getGuiTextures();
	public abstract String getGuiTitle();
	
	public int getScreenXOffset() {
		return 0;
	}
	
	public int getScreenYOffset() {
		return 0;
	}
	
	public int getGuiSizeX() {	
		return 176 + getScreenXOffset();
	}
	
	public int getGuiSizeY() {	
		return 166 + getScreenYOffset();
	}
	
	public int getScreenX() {
		return (this.width - getGuiSizeX()) / 2;
	}
	
	public int getScreenY() {
		return (this.height - getGuiSizeY()) / 2;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int mouseX, int mouseY) {
		updateScreen();
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + getGuiTextures() + ".png"));
		this.drawTexturedModalRect(getScreenX(), getScreenY(), 0, 0, getGuiSizeX(), getGuiSizeY());		
		drawGuiBackground(mouseX, mouseY);
		
		if (tileEntityEnergy instanceof ISecurity && ((ISecurity)tileEntityEnergy).getTeam() != null) {
			ISecurity tileEntitySecurity = (ISecurity)tileEntityEnergy;
			
			drawBottomStringBox(((ScorePlayerTeam)tileEntitySecurity.getTeam()).getColorPrefix() + tileEntitySecurity.getTeam().getRegisteredName());
		}
		
		drawCenteredStringWithoutShadow(getGuiTitle(), getGuiSizeX() / 2, 6);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f) {
		
		super.drawScreen(mouseX, mouseY, f);
		drawGuiForeground(mouseX, mouseY);
	}
			
	public abstract void drawGuiBackground(int mouseX, int mouseY);
	public abstract void drawGuiForeground(int mouseX, int mouseY);

	public void drawBottomStringBox(String text) {
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		
		int xPos = getScreenX() + ((getGuiSizeX() / 2) - 3);
		int yPos = getScreenY() + getGuiSizeY();
		
		int stringWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
		
		//Long Piece
		drawTexturedModalRect(xPos - (stringWidth / 2), yPos, 0, 240, stringWidth + 3, 16);
		//End
		drawTexturedModalRect(xPos + ((stringWidth / 2) + 3), yPos, 254, 240, 2, 16);
		
		mc.fontRenderer.drawString(text, (xPos - (stringWidth / 2)) + 3, yPos + 4, 0xffffff);	
	}
		
	public void drawInfoTextBar(String text, int index) {
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		
		int stringWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
		
		//Left Side
		if (!FWConfig.drawInfoTextOnRight) {
					
			//Long Piece
			drawTexturedModalRect((getScreenX() - stringWidth), (getScreenY() + 3) + (index * 19), 2, 238, stringWidth + 1, 18);
			//End
			drawTexturedModalRect((getScreenX() - stringWidth) - 2, (getScreenY() + 3) + (index * 19), 0, 238, 2, 18);
			
			mc.fontRenderer.drawString(text, (getScreenX() - stringWidth) + 1, (getScreenY() + 8) + (index * 19), 0xffffff);
		}
		
		//Right Side
		else {
			//Long Piece
			drawTexturedModalRect(getScreenX() + getGuiSizeX(), (getScreenY() + 3) + (index * 19), 2, 238, Minecraft.getMinecraft().fontRenderer.getStringWidth(text) + 1, 18);
			//End
			drawTexturedModalRect(getScreenX() + (getGuiSizeX() + (Minecraft.getMinecraft().fontRenderer.getStringWidth(text) + 1)), (getScreenY() + 3) + (index * 19), 254, 238, 2, 18);
			
			mc.fontRenderer.drawString(text, getScreenX() + (getGuiSizeX() + 1), (getScreenY() + 8) + (index * 19), 0xffffff);
		}			
	}
	
	public void drawSmallFuelBar(int x, int y) {
		
		int xPos = getScreenX() + x;
		int yPos = getScreenY() + y;
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		
		int scaledFuel = this.tileEntityEnergy.getCurrentEnergyScaled(50);
			
		this.drawTexturedModalRect(xPos, yPos - scaledFuel, 49, 57 - scaledFuel, 14, scaledFuel + 1);		
	}
	
	public void drawLargeFuelBar(int x, int y) {
		
		int xPos = getScreenX() + x;
		int yPos = getScreenY() + y;
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		
		int scaledFuel = this.tileEntityEnergy.getCurrentEnergyScaled(60);
			
		this.drawTexturedModalRect(xPos, yPos - scaledFuel, 0, 67 - scaledFuel, 49, scaledFuel + 1);	
		this.drawTexturedModalRect(xPos, yPos - 56, 0, 67, 12, 51);
	}
	
	public void drawSmallProgBar(int x, int y) {
		
		int xPos = getScreenX() + x;
		int yPos = getScreenY() + y;
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		
		int scaledProg = this.tileEntityEnergy.getCurrentProgressScaled(24);
			
		this.drawTexturedModalRect(xPos, yPos, 77, 7, scaledProg + 1, 12);
	}
	
	public void drawLongProgBar(int x, int y) {
		
		int xPos = getScreenX() + x;
		int yPos = getScreenY() + y;
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		
		int scaledProg = this.tileEntityEnergy.getCurrentProgressScaled(161);
			
		this.drawTexturedModalRect(xPos, yPos, 0, 0, scaledProg + 1, 7);
	}
	
	public void drawSmallFuelBarTextBox(int x, int y, int mouseX, int mouseY) {
		drawStringOverBox("FE: " + tileEntityEnergy.energy + "/" + tileEntityEnergy.maxEnergy, x, y - 50, 14, 50, mouseX, mouseY);
	}
	
	public void drawLargeFuelBarTextBox(int x, int y, int mouseX, int mouseY) {
		drawStringOverBox("FE: " + tileEntityEnergy.energy + "/" + tileEntityEnergy.maxEnergy, x, y - 60, 49, 60, mouseX, mouseY);
	}
	
	public void drawSmallProgBarTextBox(int x, int y, int mouseX, int mouseY) {
		drawStringOverBox("Progress: " + tileEntityEnergy.progress * 100 / tileEntityEnergy.getMaxProgress() + "%", x, y, 24, 12, mouseX, mouseY);
	}
	
	public void drawLongProgBarTextBox(int x, int y, int mouseX, int mouseY) {
		drawStringOverBox("Progress: " + tileEntityEnergy.progress * 100 / tileEntityEnergy.getMaxProgress() + "%", x, y, 161, 7, mouseX, mouseY);
	}
		
	public void drawCenteredStringWithoutShadow(String text, int x, int y) {
		
		int xPos = getScreenX() + x;
		int yPos = getScreenY() + y;
		
		mc.fontRenderer.drawString(text, xPos - (fontRendererObj.getStringWidth(text) / 2), yPos, 4210752);
	}
	
	protected boolean isMouseOverBox(int x, int y, int boxX, int boxY, int mouseX, int mouseY) {
		
		int posX = getScreenX() + x;
		int posY = getScreenY() + y;
		
		return mouseX >= posX - 1 && mouseX < posX + boxX + 1 && mouseY >= posY - 1 && mouseY < posY + boxY + 1;
	}
	
	public void drawStringOverBox(String text, int x, int y, int boxX, int boxY, int mouseX, int mouseY) {
		
		if (isMouseOverBox(x, y, boxX, boxY, mouseX, mouseY)) {
			drawCreativeTabHoveringText(text, mouseX, mouseY);
		}
	}
}

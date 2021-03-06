package calemi.fusionwarfare.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class GuiScreenBase extends GuiScreen {

	public abstract String getGuiTextures();

	public abstract int getGuiSizeX();
	public abstract int getGuiSizeY();	
	
	public abstract boolean canCloseWithInvKey();
	
	public int getScreenX() {
		return (this.width - getGuiSizeX()) / 2;
	}
	
	public int getScreenY() {
		return (this.height - getGuiSizeY()) / 2;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float f1) {
		updateScreen();
		
		drawDefaultBackground();
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + getGuiTextures() + ".png"));
		this.drawTexturedModalRect(getScreenX(), getScreenY(), 0, 0, getGuiSizeX(), getGuiSizeY());	
		
		drawGuiBackground(mouseX, mouseY);
		
		super.drawScreen(mouseX, mouseY, f1);
		
		drawGuiForeground(mouseX, mouseY);
	}
	
	public abstract void drawGuiBackground(int mouseX, int mouseY);
	public abstract void drawGuiForeground(int mouseX, int mouseY);	
	
	public void drawCenteredStringBox(String text, int x, int y) {
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		
		int xPos = getScreenX() + (x - 3);
		int yPos = getScreenY() + y;
		
		int stringWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
		
		//Long Piece
		drawTexturedModalRect(xPos - (stringWidth / 2), yPos, 0, 238, stringWidth + 4, 18);
		//End
		drawTexturedModalRect(xPos + ((stringWidth / 2) + 4), yPos, 254, 238, 2, 18);
		
		mc.fontRenderer.drawString(text, (xPos - (stringWidth / 2)) + 3, yPos + 5, 0xffffff);	
	}
	
	public void drawLimitedCenteredStringBox(String text, int x, int y, int maxWidth) {
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		
		int xPos = getScreenX() + (x - 3);
		int yPos = getScreenY() + y;
		
		int stringWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
		
		//Long Piece
		drawTexturedModalRect(xPos - (maxWidth / 2) + 2, yPos, 0, 238, maxWidth, 18);
		//End
		drawTexturedModalRect(xPos + ((maxWidth / 2) + 2), yPos, 254, 238, 2, 18);
			
		if (stringWidth > maxWidth) {
			
			//Bottom Piece
			
			//Long Piece
			drawTexturedModalRect(xPos - (maxWidth / 2) + 2, yPos + 9, 0, 220, maxWidth, 18);
			//End
			drawTexturedModalRect(xPos + (maxWidth / 2) + 2, yPos + 9, 254, 220, 2, 18);		
		
			ArrayList arraylist = new ArrayList();
	        Iterator iterator = this.fontRendererObj.listFormattedStringToWidth(text, maxWidth).iterator();

	        while (iterator.hasNext()) {
	            String s1 = (String)iterator.next();
	            arraylist.add(s1);
	        }

			String[] string = (String[])arraylist.toArray(new String[0]);
			int yOffset = yPos + 5;
						
	        for (int i = 0; i < string.length; ++i) {
	        	
	            String s = string[i];
	            this.drawCenteredString(this.fontRendererObj, s, xPos + 3, yOffset, 0xffffff);
	            yOffset += this.fontRendererObj.FONT_HEIGHT + 1;
	        }		
		}
		
		else {
			mc.fontRenderer.drawString(text, (xPos - (stringWidth / 2)) + 3, yPos + 5, 0xffffff);	
		}				
	}
	
	public void drawRightInfoTextBar(String text, int index) {
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		
		//Long Piece
		drawTexturedModalRect(getScreenX() + getGuiSizeX(), (getScreenY() + 3) + (index * 19), 2, 238, Minecraft.getMinecraft().fontRenderer.getStringWidth(text) + 1, 18);
		//End
		drawTexturedModalRect(getScreenX() + (getGuiSizeX() + (Minecraft.getMinecraft().fontRenderer.getStringWidth(text) + 1)), (getScreenY() + 3) + (index * 19), 254, 238, 2, 18);
		
		mc.fontRenderer.drawString(text, getScreenX() + (getGuiSizeX() + 1), (getScreenY() + 8) + (index * 19), 0xffffff);		
	}
	
	public void drawLeftInfoTextBar(String text, int index) {
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/gui_textures.png"));
		
		int stringWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
		
		//Long Piece
		drawTexturedModalRect((getScreenX() - stringWidth), (getScreenY() + 3) + (index * 19), 2, 238, stringWidth + 1, 18);
		//End
		drawTexturedModalRect((getScreenX() - stringWidth) - 2, (getScreenY() + 3) + (index * 19), 0, 238, 2, 18);
		
		mc.fontRenderer.drawString(text, (getScreenX() - stringWidth) + 1, (getScreenY() + 8) + (index * 19), 0xffffff);		
	}
	
	public void drawCenteredStringWithoutShadow(String text, int x, int y) {
		
		int xPos = getScreenX() + x;
		int yPos = getScreenY() + y;
		
		mc.fontRenderer.drawString(text, xPos - (fontRendererObj.getStringWidth(text) / 2), yPos, 4210752);
	}
	
	public void drawLimitedString(String text, int x, int y, int maxWidth, int mouseX, int mouseY, boolean isWhite) {
		
		int xPos = getScreenX() + x;
		int yPos = getScreenY() + y;
		
		String temp = text;
		
		if (temp.length() > 13) {
			
			temp = temp.substring(0, 12);
			temp += "...";
		}	
		
		mc.fontRenderer.drawString(temp, xPos, yPos, isWhite ? 0xffffff : 4210752);				
	}
	
	public void drawParagraphString(String text, int x, int y, int maxWidth) {
		
		int xPos = getScreenX() + x;
		int yPos = getScreenY() + y;
		
		int stringWidth = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);
		
		GL11.glPushMatrix();		
		GL11.glScalef(0.8F, 0.8F, 0.8F);
		GL11.glTranslatef(xPos / 4, yPos / 4, 100);
		fontRendererObj.drawSplitString(text, xPos, yPos, maxWidth, 4210752);	
		GL11.glPopMatrix();
	}
	
	public void drawSelectedStringOverBox(String text, int x, int y, int width, int mouseX, int mouseY) {
		drawStringOverBox(text, x, y, width, 8, mouseX, mouseY);
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
	
	public void drawItemStack(ItemStack stack, int x, int y) {
		
		int xPos = getScreenX() + x;
		int yPos = getScreenY() + y;
		
		RenderHelper.enableGUIStandardItemLighting();
		itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), stack, xPos, yPos);
		if (stack.stackSize > 1) itemRender.renderItemOverlayIntoGUI(fontRendererObj, this.mc.getTextureManager(), stack, xPos, yPos, Integer.toString(stack.stackSize));
	}
	
	@Override
	protected void keyTyped(char c, int keyID) {
		super.keyTyped(c, keyID);
		
		if (canCloseWithInvKey() && keyID == Minecraft.getMinecraft().gameSettings.keyBindInventory.getKeyCode()) {
			Minecraft.getMinecraft().thePlayer.closeScreen();
		}
	}
}

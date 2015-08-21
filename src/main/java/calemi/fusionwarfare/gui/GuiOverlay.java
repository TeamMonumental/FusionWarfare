package calemi.fusionwarfare.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import calemi.fusionwarfare.Reference;

public class GuiOverlay extends GuiScreen {

	public ResourceLocation texture;
	
	public GuiOverlay(String imagePath) {
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + imagePath + ".png");
	}
	
	public void render(RenderGameOverlayEvent event) {
		
		if(Minecraft.getMinecraft().currentScreen == null) {
			
			GL11.glPushMatrix();
		
			GL11.glDisable(GL11.GL_LIGHTING);
		
			GL11.glEnable(GL11.GL_BLEND);
		
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glScalef(1F, 1F, 0);	
			
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			
			ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
			int width = scaledresolution.getScaledWidth();
			int height = scaledresolution.getScaledHeight();
			Minecraft.getMinecraft().entityRenderer.setupOverlayRendering();
			
			drawTexturedQuad(0, 0, width, height, 0, 0, 256, 128, 256, 256, 0);
			
			GL11.glEnable(GL11.GL_LIGHTING);
		
			GL11.glPopMatrix();
		}		
	}
	
	public static void drawTexturedQuad(int x, int y, int width, int height, int u, int v, int uSize, int vSize, int texWidth, int texHeight, float zLevel) {
		float uFact = 1f / texWidth;
		float vFact = 1f / texHeight;

		int uEnd = u + uSize;
		int vEnd = v + vSize;

		Tessellator t = Tessellator.instance;
		t.startDrawingQuads();
		t.addVertexWithUV(x, y + height, zLevel, u * uFact, vEnd * vFact);
		t.addVertexWithUV(x + width, y + height, zLevel, uEnd * uFact, vEnd * vFact);
		t.addVertexWithUV(x + width, y, zLevel, uEnd * uFact, v * vFact);
		t.addVertexWithUV(x, y, zLevel, u * uFact, v * vFact);
		t.draw();
	}
}
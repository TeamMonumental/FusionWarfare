package calemi.fusionwarfare.util;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class HUDRenderer {

	public ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
	public int width = scaledresolution.getScaledWidth();
	public int height = scaledresolution.getScaledHeight();
	
	public static void renderTexture(String path, int teleX, int teleY, int texX, int texY, int zLevel, int texWidth, int texHeight, int width, int height) {
		
		float xFact = 1f / texWidth;
		float yFact = 1f / texHeight;
		
		float startX = xFact * texX;
		float startY = yFact * texY;
		
		float endX = xFact * (texX + width);
		float endY = yFact * (texY + height);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + path));
				
		Tessellator tessellator = Tessellator.instance;
		
		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(teleX, teleY + height, zLevel, startX, endY);
		tessellator.addVertexWithUV(teleX + width, teleY + height, zLevel, endX, endY);
		tessellator.addVertexWithUV(teleX + width, teleY, zLevel, endX, startY);
		tessellator.addVertexWithUV(teleX, teleY, zLevel, startX, startY);
		tessellator.draw();
		
		GL11.glDisable(GL11.GL_BLEND);
		
		GL11.glPopMatrix();
	}	
}

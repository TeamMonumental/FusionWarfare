package calemi.fusionwarfare.book;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.gui.GuiBook;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ImagePage implements IPage {

	private String imagePage;
	private String text, text2;
	
	public ImagePage(String imagePath, String text, String text2) {
		this.imagePage = imagePath;
		this.text = text;
		this.text2 = text2;
	}
	
	@Override
	public void render(GuiBook book, int screenX, int screenY) {
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + imagePage + ".png"));
		
		GL11.glPushMatrix();		
		GL11.glTranslatef(screenX, screenY, 50);
		GL11.glScalef(0.45F, 0.45F, 0.45F);
		book.drawTexturedModalRect(16, -40, 0, 0, 255, 170);
		GL11.glPopMatrix();
		
		book.drawParagraphString(text, 11, 70, 125);
		book.drawParagraphString(text2, 137, 10, 125);
	}
}

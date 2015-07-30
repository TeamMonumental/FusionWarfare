package calemi.fusionwarfare.book;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.gui.GuiBook;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class InfoPage implements IPage {

	private String text, text2;
	
	public InfoPage(String text, String text2) {
		this.text = text;
		this.text2 = text2;
	}
	
	@Override
	public void render(GuiBook book, int screenX, int screenY) {
		
		book.drawParagraphString(text, 11, 10, 125);
		book.drawParagraphString(text2, 137, 10, 125);
	}
}

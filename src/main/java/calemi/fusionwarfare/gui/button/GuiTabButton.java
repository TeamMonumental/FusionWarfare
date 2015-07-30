package calemi.fusionwarfare.gui.button;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class GuiTabButton extends GuiButton {

	private static final ResourceLocation buttonTexture = new ResourceLocation(Reference.MOD_ID + ":textures/gui/book_textures.png");

	public GuiTabButton(int buttonId, int x, int y) {

		super(buttonId, x, y, null);
		this.height = 22;
		this.width = 21;
		this.enabled = true;
		this.visible = true;
		this.id = buttonId;
		this.xPosition = x;
		this.yPosition = y;
		this.displayString = null;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {

		if (this.visible) {
			mc.getTextureManager().bindTexture(buttonTexture);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			int k = this.getHoverState(this.field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 34 + (k * 21) - 21, 159, this.width, this.height);
			this.mouseDragged(mc, mouseX, mouseY);
			int l = 14737632;

			if (packedFGColour != 0) {
				l = packedFGColour;
			} else if (!this.enabled) {
				l = 10526880;
			} else if (this.field_146123_n) {
				l = 16777120;
			}
		}
	}
}
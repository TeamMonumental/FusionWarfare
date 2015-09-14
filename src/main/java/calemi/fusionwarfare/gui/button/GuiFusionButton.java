package calemi.fusionwarfare.gui.button;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class GuiFusionButton extends GuiButton {

	private static final ResourceLocation buttonTexture = new ResourceLocation(Reference.MOD_ID + ":textures/gui/fusion_button.png");

	public boolean clicked = false;
	
	public GuiFusionButton(int buttonId, int x, int y, int widthIn, String text) {

		super(buttonId, x, y, widthIn, 16, "");
		this.enabled = true;
		this.visible = true;
		this.id = buttonId;
		this.xPosition = x;
		this.yPosition = y;
		this.width = widthIn;
		this.displayString = text;
	}

	public void drawButton(Minecraft mc, int mouseX, int mouseY) {

		if (this.visible) {
			FontRenderer fontrenderer = mc.fontRenderer;
			mc.getTextureManager().bindTexture(buttonTexture);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
			int k = this.getHoverState(this.field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, clicked ? 0 : (k * 16), this.width / 2, this.height);
			this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, clicked ? 0 : (k * 16), this.width / 2, this.height);
			this.mouseDragged(mc, mouseX, mouseY);
			int l = 14737632;

			if (packedFGColour != 0) {
				l = packedFGColour;
			} else if (!this.enabled) {
				l = 10526880;
			} else if (this.field_146123_n) {
				l = 16777120;
			}

			this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, (this.yPosition + (this.height - 8) / 2), l);
		}
	}
}
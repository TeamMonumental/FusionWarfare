package calemi.fusionwarfare.book;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.gui.GuiBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class StackInfoPage implements IPage {
	
	private long lastTime;
	private long targetTime = 5;
	private float rot;
	
	private String text;
	private ItemStack stack;
	private boolean rotateOffset;
	private int xOffset, yOffset;
	private int rotateX, rotateZ;
	private int scale;
	
	public StackInfoPage(String text, ItemStack stack, boolean rotateOffset, int xOffset, int yOffset, int rotateX, int rotateZ, int scale) {
		lastTime = System.currentTimeMillis();
		this.text = text;
		this.stack = stack;
		this.rotateOffset = rotateOffset;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.rotateX = rotateX;
		this.rotateZ = rotateZ;
		this.scale = scale;
	}

	@Override
	public void render(GuiBook book, int screenX, int screenY) {
		
		book.drawLimitedCenteredStringBox(stack.getDisplayName(), 64, 80, 100);
		
		book.drawParagraphString(text, 137, 10, 125);
		
		renderRotatingBlockIntoGUI(stack, screenX, screenY, rotateOffset, xOffset, yOffset, rotateX, rotateZ, scale);
	}
	
	public void renderRotatingBlockIntoGUI(ItemStack stack, int screenX, int screenY, boolean rotateOffset, int xOffset, int yOffset, int rotateX, int rotateZ, int scale) {
	       
		if (System.currentTimeMillis() - lastTime >= targetTime) {
			lastTime = System.currentTimeMillis();
			rot += 1F;
			rot %= 360;
		}
		
		EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().theWorld);
		
		entityItem.setEntityItemStack(stack);
		entityItem.hoverStart = 0;

        GL11.glPushMatrix();
        GL11.glTranslated(screenX + 64 + xOffset, screenY + 70 + yOffset, 100);
        GL11.glScaled(130 + scale, 130 + scale, (-130 - scale));
        GL11.glRotated(180, 1, 0, 0);
        
        if (rotateOffset) {
        	GL11.glRotated(30, 1, 0, 0);
        	GL11.glTranslated(0, 0.15D, 0);
        }
        
        if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
        	GL11.glRotated(rot, 0, 1, 0);
        }
        
        else GL11.glRotated(0, 0, 1, 0);
        
        GL11.glRotated(rotateX, 1, 0, 0);
        GL11.glRotated(rotateZ, 0, 0, 1);
      
        RenderManager.instance.renderEntityWithPosYaw(entityItem, 0.0, 0.0, 0, 0, 0);
        GL11.glPopMatrix();
	}
}

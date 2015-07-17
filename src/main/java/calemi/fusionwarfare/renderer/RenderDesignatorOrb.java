package calemi.fusionwarfare.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import calemi.fusionwarfare.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDesignatorOrb extends Render {

	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/entity/designator_orb.png");

	public void doRender(Entity e, double x, double y, double z, float par8, float par9) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		float f6 = 1.0F;
		float f7 = 0.5F;
		float f8 = 0.25F;
		GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		this.bindEntityTexture(e);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		tessellator.addVertexWithUV((double) (0.0F - f7), (double) (0.0F - f8), 0.0D, 0, 1);
		tessellator.addVertexWithUV((double) (f6 - f7), (double) (0.0F - f8), 0.0D, 1, 1);
		tessellator.addVertexWithUV((double) (f6 - f7), (double) (1.0F - f8), 0.0D, 1, 0);
		tessellator.addVertexWithUV((double) (0.0F - f7), (double) (1.0F - f8), 0.0D, 0, 0);
		tessellator.draw();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}

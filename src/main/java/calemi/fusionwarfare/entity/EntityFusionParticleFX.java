package calemi.fusionwarfare.entity;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFusionParticleFX extends EntityFX {

	private ResourceLocation texture;

	public EntityFusionParticleFX(World world, String imagePath, double x, double y, double z) {
		super(world, x, y, z);
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/particle/" + imagePath + ".png");
		this.particleMaxAge = 100;
		setScale(18F);
	}

	@Override
	public void renderParticle(Tessellator tess, float particleTicks, float par3, float par4, float par5, float par6, float par7) {
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.0F);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		float f6 = ((float) this.particleAge + particleTicks) / (float) this.particleMaxAge;
		this.particleScale *= (1.0F - f6 * f6 * 0.5F);
		float PScale = 0.01F * this.particleScale;
		float x = (float) (this.prevPosX + (this.posX - this.prevPosX) * particleTicks - interpPosX);
		float y = (float) (this.prevPosY + (this.posY - this.prevPosY) * particleTicks - interpPosY);
		float z = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * particleTicks - interpPosZ);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		tess.startDrawingQuads();
		tess.setBrightness(240);
		tess.addVertexWithUV((double) (x - par3 * PScale - par6 * PScale), (double) (y - par4 * PScale), (double) (z - par5 * PScale - par7 * PScale), 0, 0);
		tess.addVertexWithUV((double) (x - par3 * PScale + par6 * PScale), (double) (y + par4 * PScale), (double) (z - par5 * PScale + par7 * PScale), 1, 0);
		tess.addVertexWithUV((double) (x + par3 * PScale + par6 * PScale), (double) (y + par4 * PScale), (double) (z + par5 * PScale + par7 * PScale), 1, 1);
		tess.addVertexWithUV((double) (x + par3 * PScale - par6 * PScale), (double) (y - par4 * PScale), (double) (z + par5 * PScale - par7 * PScale), 0, 1);
		tess.draw();
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		GL11.glEnable(GL11.GL_LIGHTING);
	}

	public int getFXLayer() {
		return 3;
	}

	public EntityFusionParticleFX setGravity(float gravity) {
		particleGravity = gravity;
		return this;
	}

	public EntityFusionParticleFX setScale(float scale) {
		particleScale = scale;
		return this;
	}
}
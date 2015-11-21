package calemi.fusionwarfare.renderer;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.entity.EntityMissile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMissile extends Render {
	
	@Override
	public void doRender(Entity e, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
		
		EntityMissile missile = (EntityMissile) e;
	
		GL11.glPushMatrix();
		
		GL11.glTranslated(x, y + 1.15F, z);	
		
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(45, 0, 1, 0);
		
		GL11.glScalef(0.7F, 0.7F, 0.7F);
		
		if (e.motionY < 0) {
			GL11.glRotatef(180, 1, 0, 0);
		}
		
		missile.missileType.missileType.packedModel.render();
		
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e) {
		return null;
	}
}

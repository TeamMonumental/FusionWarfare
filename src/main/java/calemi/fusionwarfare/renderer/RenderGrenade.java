package calemi.fusionwarfare.renderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.entity.EntityGrenade;
import calemi.fusionwarfare.item.ItemGrenade;
import calemi.fusionwarfare.model.ModelGrenade;
import calemi.fusionwarfare.model.ModelFusionSniperRifle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderGrenade extends Render {

	private final ModelGrenade model = new ModelGrenade();
		
	@Override
	public void doRender(Entity e, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
		
		GL11.glPushMatrix();
		
		GL11.glTranslated(x, y + 1.5, z);	
		GL11.glRotatef(180, 1, 0, 0);
		
		if (((EntityGrenade)e).grenade!= null) {
			Minecraft.getMinecraft().renderEngine.bindTexture(getEntityTexture(e));
			model.render(e, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		}	
		
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity e) {
		return new ResourceLocation(Reference.MOD_ID + ":textures/models/grenade_" + ((ItemGrenade)((EntityGrenade)e).grenade).event.getTextureName() + ".png");
	}
}

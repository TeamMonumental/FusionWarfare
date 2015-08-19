package calemi.fusionwarfare.renderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.model.ModelGrenade;
import calemi.fusionwarfare.model.ModelFusionSniperRifle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderFusionGrenade extends Render {

	private final ModelGrenade model = new ModelGrenade();
	private ResourceLocation texture;
	
	public RenderFusionGrenade(String image) {
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/fusion_grenade_" + image + ".png");
	}
	
	@Override
	public void doRender(Entity e, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
		
		GL11.glPushMatrix();
		
		GL11.glTranslated(x, y + 1.5, z);	
		GL11.glRotatef(180, 1, 0, 0);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);		
		model.render(e, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return null;
	}

}

package calemi.fusionwarfare.renderer;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.entity.EntityBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderBlock extends Render {

	private RenderBlocks blockRenderer = new RenderBlocks();
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float f1, float f2) {
				
		EntityBlock entityGhostBlock = (EntityBlock)entity;
		
		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 0.5F, z + 0.5F);
		this.bindEntityTexture(entity);			
		this.blockRenderer.renderBlockAsItem(entityGhostBlock.block, 0, entity.getBrightness(f2));
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TextureMap.locationBlocksTexture;
	}
}
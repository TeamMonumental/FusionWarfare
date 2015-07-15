package calemi.fusionwarfare.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.block.BlockSupplyCrate;
import calemi.fusionwarfare.entity.EntityFallingSupplyCrate;
import calemi.fusionwarfare.entity.EntityMissile;
import calemi.fusionwarfare.entity.EntityRocket;
import calemi.fusionwarfare.model.ModelFallingSupplyCrate;
import calemi.fusionwarfare.model.ModelMissileLauncher;
import calemi.fusionwarfare.model.ModelRocket;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderRocket extends Render {
	
	ModelRocket model = new ModelRocket();
	
	public void doRender(Entity entity, double x, double y, double z, float f1, float f2) {
		this.renderEntityModel((EntityRocket)entity, x, y, z, f1, f2);
	}

	public void renderEntityModel(EntityRocket entity, double x, double y, double z, float f1, float f2) {

		GL11.glPushMatrix();
		
		GL11.glTranslated(x, y, z);
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		
		GL11.glRotatef(entity.prevRotationYaw + 90F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(entity.prevRotationPitch, 0.0F, 0.0F, 1.0F);

		Minecraft.getMinecraft().renderEngine.bindTexture(getEntityTexture(entity));
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);		
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(Reference.MOD_ID + ":textures/models/rocket.png");
	}
}

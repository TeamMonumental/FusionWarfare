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
import calemi.fusionwarfare.model.ModelFallingSupplyCrate;
import calemi.fusionwarfare.model.ModelMissileLauncher;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFallingSupplyCrate extends Render {
	
	ModelFallingSupplyCrate model = new ModelFallingSupplyCrate();
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float f1, float f2) {
		
		EntityFallingSupplyCrate entityCrate = (EntityFallingSupplyCrate) entity;
				
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.50F, (float)z + 0.5F);
		GL11.glRotatef(180, 1, 0, 0);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/models/falling_supply_crate_" + BlockSupplyCrate.textureNames[entityCrate.meta] + ".png"));
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);		
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}
}

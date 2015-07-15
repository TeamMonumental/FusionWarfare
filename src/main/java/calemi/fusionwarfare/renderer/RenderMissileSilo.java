package calemi.fusionwarfare.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.model.ModelMissileLauncher;
import calemi.fusionwarfare.model.ModelMissileSilo;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileSiloCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMissileSilo extends TileEntitySpecialRenderer {

	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/missile_silo.png");

	ModelMissileSilo model = new ModelMissileSilo();
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
		
		TileEntityMissileSiloCore tileEntity = (TileEntityMissileSiloCore)entity;
		
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.50F, (float)z + 0.5F);
		GL11.glTranslatef(tileEntity.direction.offsetX, 3, tileEntity.direction.offsetZ);
		GL11.glRotatef(180, 1, 0, 0);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		if (tileEntity.isAssembled) model.render(null, 0, 0, 0, 0, 0, 0.0625F);		
		GL11.glPopMatrix();
	}
}

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
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMissileLauncher extends TileEntitySpecialRenderer {

	private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/missile_launcher.png");

	ModelMissileLauncher model = new ModelMissileLauncher();
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
		
		TileEntityMissileLauncher tileEntity = (TileEntityMissileLauncher)entity;
		
		GL11.glPushMatrix();
		
		if (tileEntity.getStackInSlot(0) != null) {
			
			ItemStack stack = new ItemStack(tileEntity.slots[0].getItem(), 1, 0);
			EntityItem entItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, stack);	
			
			GL11.glPushMatrix();						
			RenderItem.renderInFrame = true;
			GL11.glTranslatef((float)x + 0.5F, (float)y + 0.25F, (float)z + 0.5F);
			GL11.glScalef(1.5F, 1.5F, 1.5F);
			GL11.glRotatef(45, 0, 1, 0);
			RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;
			GL11.glPopMatrix();	
		}		
		
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.50F, (float)z + 0.5F);
		GL11.glRotatef(180, 1, 0, 0);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F);		
		GL11.glPopMatrix();
	}
}

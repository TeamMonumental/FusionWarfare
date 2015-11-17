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
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.model.ModelAuraBase;
import calemi.fusionwarfare.model.ModelMissileLauncher;
import calemi.fusionwarfare.tileentity.machine.TileEntityAuraBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityAuraPlayerTurret;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import calemi.fusionwarfare.util.EnumColorUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAuraBase extends TileEntitySpecialRenderer {

	ModelAuraBase model = new ModelAuraBase();
	
	private long lastTime;
	private long targetTime = 10;
	private float rot;
	
	private int red, green, blue;
	
	public RenderAuraBase(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
		
		TileEntityAuraBase tileEntityAuraBase = (TileEntityAuraBase)entity;
		
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.50F, (float)z + 0.5F);
		GL11.glRotatef(180, 1, 0, 0);
		
		if (System.currentTimeMillis() - lastTime >= targetTime) {
			lastTime = System.currentTimeMillis();
			rot += 1F;
			rot %= 360;
		}
				
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/models/aura_base.png"));
		model.render(null, 0, 0, 0, 0, 0, 0.0625F, rot, red, green, blue, false);		
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/models/aura_base_overlay.png"));
		model.render(null, 0, 0, 0, 0, 0, 0.0624F, rot, red, green, blue, true);	
		
		GL11.glPopMatrix();
	}
}

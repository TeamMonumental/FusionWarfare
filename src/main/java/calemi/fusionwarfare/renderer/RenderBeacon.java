package calemi.fusionwarfare.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.model.ModelBeacon;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBeacon extends TileEntitySpecialRenderer {
	
	private ResourceLocation texture;
	ModelBeacon model = new ModelBeacon();
	
	public RenderBeacon(String image) {
		this.texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/" + image + ".png");
	}
	
	@Override
	public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float f) {
		
		TileEntityEnergyBase tileEntity = (TileEntityEnergyBase)entity;
		
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float)x + 0.5F, (float)y + 2.5F, (float)z + 0.5F);
		GL11.glRotatef(180, 1, 0, 0);
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		model.render(null, 0, 0, 0, 0, 0, 0.0625F, tileEntity.getCurrentProgressScaled(360));			
		
		GL11.glPopMatrix();
	}
}

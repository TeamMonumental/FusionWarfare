package calemi.fusionwarfare.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
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
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import calemi.fusionwarfare.tileentity.machine.TileEntityTwoInputs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFloatingItem extends TileEntitySpecialRenderer {

	private ItemStack stack;
	private float xOffset;
	private float yOffset;
	private float zOffset;
	private float scale;

	private long lastTime;
	private long targetTime = 10;
	private float rot;

	public RenderFloatingItem(ItemStack stack, float xOffset, float yOffset, float zOffset, float scale) {
		this.stack = stack;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.zOffset = zOffset;
		this.scale = scale;
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {

		boolean b = true;

		if (tileEntity instanceof TileEntityTwoInputs) {

			TileEntityTwoInputs tileEntityTwoInputs = (TileEntityTwoInputs) tileEntity;

			if (tileEntityTwoInputs.recipeType != EnumRecipeType.MISSILE_FACTORY) {
				b = false;
			}
		}

		if (System.currentTimeMillis() - lastTime >= targetTime) {
			lastTime = System.currentTimeMillis();
			rot += 3F;
			rot %= 360;
		}

		if (b) {
			
			GL11.glPushMatrix();

			EntityItem entItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, stack);

			GL11.glPushMatrix();
			entItem.hoverStart = 0.0F;
			RenderItem.renderInFrame = false;
			GL11.glTranslatef((float) x + xOffset, (float) y + yOffset, (float) z + zOffset);
			GL11.glScalef(scale, scale, scale);

			if (Minecraft.getMinecraft().gameSettings.fancyGraphics) {
				GL11.glRotatef(rot, 0, 1, 0);
			}

			RenderManager.instance.renderEntityWithPosYaw(entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
			RenderItem.renderInFrame = false;
			GL11.glPopMatrix();
			GL11.glPopMatrix();

		}

	}
}

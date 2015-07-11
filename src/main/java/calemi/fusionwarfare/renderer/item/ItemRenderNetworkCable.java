package calemi.fusionwarfare.renderer.item;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

public class ItemRenderNetworkCable implements IItemRenderer {

	TileEntitySpecialRenderer render;
	private TileEntity tileentity;

	public ItemRenderNetworkCable(TileEntitySpecialRenderer render, TileEntity tileentity) {
		this.tileentity = tileentity;
		this.render = render;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glTranslatef(-1F, -1.0F, -1F);
			GL11.glScalef(2F, 2F, 2F);
		}

		if (type == IItemRenderer.ItemRenderType.INVENTORY) {
			GL11.glScalef(2F, 2F, 2F);
			GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		}

		if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
			GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(-1.7F, -0.7F, -0.5F);
			GL11.glScalef(2F, 2F, 2F);
		}
		
		if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glScalef(1.5F, 1.5F, 1.5F);
			GL11.glTranslatef(-0.4F, -0.0F, -0.1F);
		}

		this.render.renderTileEntityAt(this.tileentity, 0.0D, 0.0D, 0.0D, 0.0F);
	}
}

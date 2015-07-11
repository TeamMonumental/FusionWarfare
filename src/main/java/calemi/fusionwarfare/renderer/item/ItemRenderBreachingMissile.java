package calemi.fusionwarfare.renderer.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.item.ItemMissile;
import calemi.fusionwarfare.util.missile.MissileType;

public class ItemRenderBreachingMissile implements IItemRenderer {
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return type == ItemRenderType.INVENTORY;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		if (type == IItemRenderer.ItemRenderType.INVENTORY) {
			GL11.glTranslatef(0, -0.1F, -0.1F);
			GL11.glRotatef(20, 1, 0, 0);
			GL11.glRotatef(50, 0, 0, 1);
			GL11.glScalef(0.55F, 0.55F, 0.55F);
		}
		
		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glTranslatef(0, 1.3F, 0);
		}
		
		if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslatef(2F, 1.1F, 1.6F);
			GL11.glScalef(1.5F, 1.5F, 1.5F);
			GL11.glRotatef(240, 0, 1, 0);
			GL11.glRotatef(20, 1, 0, 0);
			GL11.glRotatef(-10, 0, 0, 1);
		}

		if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
			GL11.glTranslatef(0.6F, 1F, -0.5F);
			GL11.glRotatef(-40, 1, 0, 0);
			GL11.glScalef(0.7F, 0.7F, 0.7F);
		}	
		
		render(item);		
	}

	private void render(ItemStack item) {

		GL11.glPushMatrix();

		GL11.glRotatef(180, 1, 0, 0);
	
		((ItemMissile)item.getItem()).missileType.packedModel.render();
		
		GL11.glPopMatrix();
	}
}

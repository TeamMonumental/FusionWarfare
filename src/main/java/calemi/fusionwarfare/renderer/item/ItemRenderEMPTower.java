package calemi.fusionwarfare.renderer.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.model.ModelEMPTower;
import calemi.fusionwarfare.model.ModelMissileLauncher;

public class ItemRenderEMPTower implements IItemRenderer {

	private final ModelEMPTower model = new ModelEMPTower();
	public final ResourceLocation texture;

	public ItemRenderEMPTower() {
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/emp_tower.png");
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return type == ItemRenderType.INVENTORY || type == ItemRenderType.ENTITY;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

		if (type == IItemRenderer.ItemRenderType.INVENTORY) {
			GL11.glTranslatef(0, 0.95F, 0);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
		}
		
		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glTranslatef(0, 1.9F, 0);
			GL11.glScalef(1.5F, 1.5F, 1.5F);
		}
		
		if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslatef(2F, 1.7F, 1.6F);
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
						
		render();		
	}

	private void render() {

		GL11.glPushMatrix();

		GL11.glRotatef(180, 1, 0, 0);
	
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);

		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, 0);
		GL11.glPopMatrix();
	}
}

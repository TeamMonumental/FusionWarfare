package calemi.fusionwarfare.renderer.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.item.ItemMissile;
import calemi.fusionwarfare.model.ModelRocket;
import calemi.fusionwarfare.util.missile.MissileType;

public class ItemRenderRocket implements IItemRenderer {
	
	ModelRocket model = new ModelRocket();
	public final ResourceLocation texture;
	
	public ItemRenderRocket() {
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/rocket.png");
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
			GL11.glTranslatef(-0.2F, -0.1F, 0);
			GL11.glRotatef(-10, 1, 0, 0);
			GL11.glRotatef(50, 0, 1, 0);
			GL11.glRotatef(140, 0, 0, 1);
		}
		
		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glTranslatef(-0.1F, 1F, 0);
			GL11.glRotatef(90, 0, 0, 1);
			
		}
		
		if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslatef(2F, 1.1F, 1.6F);
			GL11.glScalef(1.5F, 1.5F, 1.5F);
			GL11.glRotatef(240, 0, 1, 0);
			GL11.glRotatef(20, 1, 0, 0);
			GL11.glRotatef(80, 0, 0, 1);
		}

		if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
			GL11.glTranslatef(0.25F, 0.3F, 0F);
			GL11.glRotatef(100, 0, 0, 1);
			GL11.glRotatef(-10, 0, 1, 0);
			GL11.glRotatef(15, 1, 0, 0);
		}	
		
		render(item);		
	}

	private void render(ItemStack item) {

		GL11.glPushMatrix();

		GL11.glRotatef(180, 1, 0, 0);
		GL11.glScalef(2F, 2F, 2F);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);			
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);	
		
		GL11.glPopMatrix();
	}
}

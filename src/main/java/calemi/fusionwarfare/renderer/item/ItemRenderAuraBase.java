package calemi.fusionwarfare.renderer.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.model.ModelMissileLauncher;
import calemi.fusionwarfare.util.EnumColorUtil;
import javafx.scene.paint.Color;
import calemi.fusionwarfare.model.ModelAuraBase;

public class ItemRenderAuraBase implements IItemRenderer {

	private final ModelAuraBase model = new ModelAuraBase();
	private int red, green, blue;

	public ItemRenderAuraBase(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
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
			GL11.glTranslatef(0, 1F, 0);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
		}
		
		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glTranslatef(0, 1.3F, 0);
		}
		
		if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslatef(2F, 1.7F, 1.6F);
			GL11.glScalef(1.7F, 1.7F, 1.7F);
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
	
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/models/aura_base.png"));
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, 0, red, green, blue, false);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/models/aura_base_overlay.png"));
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0624F, 0, red, green, blue, true);
		
		GL11.glPopMatrix();
	}
}

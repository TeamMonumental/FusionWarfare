package calemi.fusionwarfare.renderer.item;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.model.ModelFusionAutoPistol;
import calemi.fusionwarfare.model.ModelFusionPistol;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderFusionAutoPistol implements IItemRenderer {

	private final ModelFusionAutoPistol model = new ModelFusionAutoPistol();
	public final ResourceLocation texture;
	
	public ItemRenderFusionAutoPistol() {
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/fusion_auto_pistol.png");
	}
	
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
			GL11.glTranslatef(0.5F, 2.95F, 0F);
			GL11.glScalef(2.5F, 2.5F, 2.5F);
			GL11.glRotatef(-180, 0, 1, 0);
		}
		
		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glTranslatef(0, 1.5F, 0F);
			GL11.glScalef(1.3F, 1.3F, 1.3F);
		}
		
		if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
			GL11.glTranslatef(0.9F, 4.8F, 1.4F);
			GL11.glScalef(4F, 4F, 4F);
			GL11.glRotatef(20, 0, 0, 1);
		}

		if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
			GL11.glRotatef(35, 0, 0, 1);
			GL11.glRotatef(-5, 0, 1, 0);
			GL11.glTranslatef(0.8F, 1.48F, -0F);
			GL11.glScalef(1.3F, 1.3F, 1.3F);
		}	
	
		render();
	}
	
	private void render() {
		
		GL11.glPushMatrix();
		
		GL11.glRotatef(180, 1, 0, 0);
	
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);			
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);			
		GL11.glPopMatrix();
	}
}

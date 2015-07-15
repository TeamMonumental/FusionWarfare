package calemi.fusionwarfare.renderer.item;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.item.ItemFusionGun;
import calemi.fusionwarfare.model.ModelFusionAutoPistol;
import calemi.fusionwarfare.model.ModelFusionPistol;
import calemi.fusionwarfare.model.ModelFusionSniperRifle;
import calemi.fusionwarfare.model.ModelRocketLauncher;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderRocketLauncher implements IItemRenderer {

	private final ModelRocketLauncher model = new ModelRocketLauncher();
	public final ResourceLocation texture;

	public ItemRenderRocketLauncher() {
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/rocket_launcher.png");
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
			GL11.glTranslatef(-0.2F, 1.1F, 0F);
			GL11.glRotatef(-10, 0, 1, 0);
			GL11.glScalef(1.1F, 1.1F, 1.1F);
		}
		
		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glTranslatef(0, 1.5F, 0F);
			GL11.glScalef(1.3F, 1.3F, 1.3F);
		}
		
		if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
			
			GL11.glTranslatef(1F, 4.2F, 1.6F);
			GL11.glScalef(4F, 4F, 4F);
			GL11.glRotatef(25, 0, 0, 1);
			GL11.glRotatef(175, 0, 1, 0);
		}

		if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
			GL11.glRotatef(15, 0, 0, 1);
			GL11.glRotatef(15, 1, 0, 0);
			GL11.glRotatef(100, 0, 1, 0);
			GL11.glTranslatef(0F, 1.75F, 0.37F);
			GL11.glScalef(1.6F, 1.6F, 1.6F);
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

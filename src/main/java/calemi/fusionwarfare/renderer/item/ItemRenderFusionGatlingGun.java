package calemi.fusionwarfare.renderer.item;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.item.ItemFusionGatlingGun;
import calemi.fusionwarfare.item.ItemFusionGun;
import calemi.fusionwarfare.model.ModelFusionAutoPistol;
import calemi.fusionwarfare.model.ModelFusionGatlingGun;
import calemi.fusionwarfare.model.ModelFusionPistol;
import calemi.fusionwarfare.model.ModelFusionSniperRifle;
import calemi.fusionwarfare.util.gun.GunData;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderFusionGatlingGun implements IItemRenderer {

	private final ModelFusionGatlingGun model = new ModelFusionGatlingGun();
	public final ResourceLocation texture;
	
	public ItemRenderFusionGatlingGun() {
		texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/fusion_gatling_gun.png");
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return type == ItemRenderType.INVENTORY;
	}

	float rot = 0;
	float acc = 0;
	
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		if (type == IItemRenderer.ItemRenderType.INVENTORY) {
			GL11.glTranslatef(0.1F, 1.4F, 0F);
			GL11.glScalef(1.2F, 1.2F, 1.2F);
			GL11.glRotatef(-190, 0, 1, 0);
		}
		
		if (type == IItemRenderer.ItemRenderType.ENTITY) {
			GL11.glRotatef(90, 1, 0, 0);
			GL11.glTranslatef(0, 1.5F, 0F);
			GL11.glScalef(1.3F, 1.3F, 1.3F);
		}
		
		if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
			
			GunData gunData = new GunData(item);
						
			if (gunData.usingTicks > 0 && acc < 0.1F) {
				acc += 0.001F;
			}
			
			if (gunData.usingTicks == 0 && acc > 0) {
				acc -= 0.001F;
			}		
			
			gunData.flush();
					
			rot += acc;
			
			GL11.glTranslatef(1.2F, 3.5F, 1.5F);
			GL11.glScalef(3.5F, 3.5F, 3.5F);
			GL11.glRotatef(28, 0, 0, 1);
			GL11.glRotatef(-10, 0, 1, 0);	
			renderWithRotation(rot);	
			return;
		}

		if (type == IItemRenderer.ItemRenderType.EQUIPPED) {
			GL11.glRotatef(35, 0, 0, 1);
			GL11.glRotatef(-5, 0, 1, 0);
			GL11.glTranslatef(1.1F, 0.7F, -0.08F);
			GL11.glScalef(1.3F, 1.3F, 1.3F);
		}	
					
		render();			
	}
	
	private void render() {
			
		GL11.glPushMatrix();
		
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(180, 0, 1, 0);
	
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);	
				
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, 0);	
			
		GL11.glPopMatrix();
	}
	
	private void renderWithRotation(float r) {
		
		GL11.glPushMatrix();
		
		GL11.glRotatef(180, 1, 0, 0);
		GL11.glRotatef(180, 0, 1, 0);
	
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);	
				
		model.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, r);	
			
		GL11.glPopMatrix();
	}
}

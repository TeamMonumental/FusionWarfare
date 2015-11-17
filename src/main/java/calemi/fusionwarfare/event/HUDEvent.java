package calemi.fusionwarfare.event;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.ItemFusionGun;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class HUDEvent {

	@SubscribeEvent
	public void updateFOV(FOVUpdateEvent event) {
		
		ItemStack stack = event.entity.inventory.getCurrentItem();
				
		if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && stack != null && stack.getItem() == InitItems.fusion_sniper_rifle && ((ItemFusionGun)stack.getItem()).getNBT(stack).getFloat("Scoping") > 0) {
			
			event.newfov = 1 - (((ItemFusionGun)stack.getItem()).getNBT(stack).getFloat("Scoping") / 2);		
		}
		
		else {
			event.newfov = event.fov;
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
    public void overlayRender(RenderGameOverlayEvent.Pre event) {
		
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		ItemStack stack = player.inventory.getCurrentItem();
		
		ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		int width = scaledresolution.getScaledWidth();
		int height = scaledresolution.getScaledHeight();
		
		if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
			
			if (stack != null && stack.getItem() == InitItems.fusion_sniper_rifle && ((ItemFusionGun)stack.getItem()).getNBT(stack).getFloat("Scoping") > 0) {
				
				renderTexture("scope_hud.png", 0, 0, 0, 0, 100, width, height * 2, width, height);
			}
		
			if (event.type == ElementType.CHAT) {
			
				for (ItemStack armor : player.inventory.armorInventory) {
			
					if (armor != null && armor.getItem() == InitItems.scuba_helmet) {
				
						renderTexture("scuba_hud.png", 0, 0, 0, 0, -100, width, height * 2, width, height);
					
						renderTexture("scuba_hud.png", 0, (height / 2) - (105 / 2), 0, 128, 0, 256, 256, 31, 105);
					
						int depthScale = scale((float) (player.isInsideOfMaterial(Material.water) ? MathHelper.clamp_float((float) (62 - player.posY), 0, 30) : 0), 30, 79);
					
						renderTexture("scuba_hud.png", 2, ((height / 2) - 31) + depthScale, 0, 128, 0, 256, 256, 7, 1);				
					}		
				}
			}	
		}	
	}
	
	private void renderTexture(String path, int teleX, int teleY, int texX, int texY, int zLevel, int texWidth, int texHeight, int width, int height) {
		
		float xFact = 1f / texWidth;
		float yFact = 1f / texHeight;
		
		float startX = xFact * texX;
		float startY = yFact * texY;
		
		float endX = xFact * (texX + width);
		float endY = yFact * (texY + height);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + path));
				
		Tessellator tessellator = Tessellator.instance;
		
		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(teleX, teleY + height, zLevel, startX, endY);
		tessellator.addVertexWithUV(teleX + width, teleY + height, zLevel, endX, endY);
		tessellator.addVertexWithUV(teleX + width, teleY, zLevel, endX, startY);
		tessellator.addVertexWithUV(teleX, teleY, zLevel, startX, startY);
		tessellator.draw();
		
		GL11.glDisable(GL11.GL_BLEND);
		
		GL11.glPopMatrix();
	}
	
	private int scale(float i, float max, float maxScale) {		
		return (int) ((i * maxScale) / max);
	}
}

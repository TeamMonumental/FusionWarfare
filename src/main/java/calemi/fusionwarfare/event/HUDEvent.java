package calemi.fusionwarfare.event;

import org.lwjgl.opengl.GL11;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.config.FWConfig;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.ItemFusionGun;
import calemi.fusionwarfare.util.HUDRenderer;
import calemi.fusionwarfare.util.Timer;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
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
	
	private Timer timer = new Timer(20);
	private int growWidth;	
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
    public void overlayRender(RenderGameOverlayEvent.Pre event) {
		
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		ItemStack stack = player.inventory.getCurrentItem();
		
		HUDRenderer hud = new HUDRenderer();
		
		if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
			
			if (!FWConfig.disableScopeHUD && stack != null && stack.getItem() == InitItems.fusion_sniper_rifle && ((ItemFusionGun)stack.getItem()).getNBT(stack).getFloat("Scoping") > 0) {
				
				hud.renderTexture("scope_hud.png", 0, 0, 0, 0, 100, hud.width, hud.height * 2, hud.width, hud.height);
			}
		
			if (!FWConfig.disableScubaHUD && event.type == ElementType.CHAT) {
			
				for (ItemStack armor : player.inventory.armorInventory) {
			
					if (armor != null && armor.getItem() == InitItems.scuba_helmet) {
				
						hud.renderTexture("scuba_hud.png", 0, 0, 0, 0, -100, hud.width, hud.height * 2, hud.width, hud.height);
						
						int water = 0;
						
						if (player.isInsideOfMaterial(Material.water)) {
							
							if (timer.isDoneAndReset()) if (growWidth > 0) growWidth--;
											
							for (int i = 1; i <= 60; i++) {
								
								if (player.worldObj.getBlock((int)(player.posX - 1F), (int)player.posY + i, (int)(player.posZ - 1F)).getMaterial() == Material.water) {								
									water++;
								}
								
								else break;
							}
						}	
												
						else {
							if (timer.isDoneAndReset()) if (growWidth < 30) growWidth++;							
						}	
						
						hud.renderTexture("scuba_hud.png", 0 - growWidth, (hud.height / 2) - (85 / 2), 0, 128, 0, 256, 256, 31, 85);
						hud.renderTexture("scuba_hud.png", 2 - growWidth, ((hud.height / 2) - 21) + water, 0, 128, 0, 256, 256, 7, 1);
					}		
				}
			}	
		}	
	}	
	private int scale(float i, float max, float maxScale) {		
		return (int) ((i * maxScale) / max);
	}
}

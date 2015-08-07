package calemi.fusionwarfare.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

import org.lwjgl.opengl.GL11;

import com.sun.javafx.iio.common.SmoothMinifier;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.gui.GuiOverlay;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.ItemFusionGun;
import calemi.fusionwarfare.item.tool.ItemScubaGear;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FOVEvent {
	
	GuiOverlay snipingOverlay = new GuiOverlay("scope");

	@SubscribeEvent
	public void updateFOV(FOVUpdateEvent event) {
		
		ItemStack stack = event.entity.inventory.getCurrentItem();
				
		if (stack != null && stack.getItem() == InitItems.fusion_sniper_rifle && ((ItemFusionGun)stack.getItem()).getNBT(stack).getFloat("Scoping") > 0) {
			
			event.newfov = 1 - (((ItemFusionGun)stack.getItem()).getNBT(stack).getFloat("Scoping") / 2);		
		}
		
		else {
			event.newfov = event.fov;
		}
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
    public void overlayRender(RenderGameOverlayEvent.Post event) {
		
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		
		ItemStack stack = player.inventory.getCurrentItem();
		
		if (stack != null && stack.getItem() == InitItems.fusion_sniper_rifle && ((ItemFusionGun)stack.getItem()).getNBT(stack).getFloat("Scoping") > 0) {
				
			snipingOverlay.render(event);
		}
	}	
}

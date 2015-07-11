package calemi.fusionwarfare.event;

import calemi.fusionwarfare.item.ItemFusionGun;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FusionGunRenderEvent {

	@SubscribeEvent(priority=EventPriority.LOW)
	public void gunRenderEvent(RenderPlayerEvent.Pre event) {

		ItemStack item = event.entityPlayer.getHeldItem();

		RenderPlayer rp = event.renderer;
		
		if (item != null && item.getItem() instanceof ItemFusionGun) {

			rp.modelBipedMain.aimedBow = true;
			rp.modelArmor.aimedBow = true;
			rp.modelArmorChestplate.aimedBow = true;
		} else {
			rp.modelBipedMain.aimedBow = false;
			rp.modelArmor.aimedBow = false;
			rp.modelArmorChestplate.aimedBow = false;
		}
	}
}
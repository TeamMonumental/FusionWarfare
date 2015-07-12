package calemi.fusionwarfare.event;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.item.tool.ItemArmorBase;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class LivingEntityEvent {

	private boolean canReset;
	
	@SubscribeEvent
	public void onLivingEntityUpdate(LivingUpdateEvent event) {	
		
		/*if (event.entityLiving instanceof EntityPlayer) {		
			
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			
			ItemStack item = player.inventory.armorInventory[2];	
				
			if (item != null && item.getItem() instanceof ItemArmorBase) {				
				
				if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindJump.getKeyCode())) {
					
					player.motionY += 0.1D;
				}
				
				if (!player.onGround) {
					player.capabilities.setPlayerWalkSpeed(0.5F);
					canReset = true;
					System.out.println("airborne");	
				}
				
				else {
					resetPlayerAbilities(player);
				}			
			}
				
			else resetPlayerAbilities(player);			
		}*/
	}
	
	private void resetPlayerAbilities(EntityPlayer player) {		
		if (canReset) player.capabilities.setPlayerWalkSpeed(0.1F);	
		canReset = false;
	}
}

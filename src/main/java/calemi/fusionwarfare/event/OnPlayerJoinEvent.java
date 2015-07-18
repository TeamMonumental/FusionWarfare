package calemi.fusionwarfare.event;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import calemi.fusionwarfare.entity.FWPlayerStats;
import calemi.fusionwarfare.init.InitItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class OnPlayerJoinEvent {

	@SubscribeEvent
    public void onEntityConstructing (EntityEvent.EntityConstructing event) {
		
        if (event.entity instanceof EntityPlayer && FWPlayerStats.get((EntityPlayer) event.entity) == null) {
        	FWPlayerStats.register((EntityPlayer) event.entity);
        }
    }
	
	@SubscribeEvent
	public void onEntityJoinWorldEvent(PlayerLoggedInEvent event) {
		
		FWPlayerStats stats = FWPlayerStats.get(event.player);
		
		if (!stats.loggedIn) {
				
			stats.loggedIn = true;
				
			ItemStack wrench = new ItemStack(InitItems.wrench);
				
			event.player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Welcome to Fusion Warfare!"));
			
            if (!event.player.inventory.addItemStackToInventory(wrench)) {
                EntityItem item = new EntityItem(event.player.worldObj, event.player.posX, event.player.posY, event.player.posZ, wrench);
                item.delayBeforeCanPickup = 0;
                event.player.worldObj.spawnEntityInWorld(item);
            }				
		}		
	}	
}

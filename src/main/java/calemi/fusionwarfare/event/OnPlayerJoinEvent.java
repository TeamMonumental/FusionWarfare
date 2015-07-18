package calemi.fusionwarfare.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import calemi.fusionwarfare.entity.FWPlayerStats;
import calemi.fusionwarfare.init.InitItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
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
		
		if (!stats.isLoggedIn) {
			stats.isLoggedIn = true;
		}
		
		if (!stats.firstLogIn) {
				
			stats.firstLogIn = true;
				
			ItemStack wrench = new ItemStack(InitItems.wrench);
				
			event.player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Welcome to Fusion Warfare!"));
			
            if (!event.player.inventory.addItemStackToInventory(wrench)) {
                EntityItem item = new EntityItem(event.player.worldObj, event.player.posX, event.player.posY, event.player.posZ, wrench);
                item.delayBeforeCanPickup = 0;
                event.player.worldObj.spawnEntityInWorld(item);
            }				
		}
		
		else {
			
			event.player.addChatMessage(new ChatComponentText(""));
			event.player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Welcome back to Fusion Warfare!"));
			event.player.addChatMessage(new ChatComponentText(""));
			
			for (Object t : event.player.worldObj.getScoreboard().getTeams()) {			
					
				Team team = (Team)t;
				
				ArrayList<String> names = new ArrayList<String>();
				
				for (Object p : ((ScorePlayerTeam)team).getMembershipCollection()) {
					
					if (event.player.worldObj.getPlayerEntityByName(p.toString()) != null) {
						FWPlayerStats stats2 = FWPlayerStats.get(event.player.worldObj.getPlayerEntityByName(p.toString()));
						names.add((stats2.isLoggedIn ? EnumChatFormatting.GREEN + "" : EnumChatFormatting.GRAY) + p.toString() + EnumChatFormatting.AQUA);
					}
					
					else names.add(EnumChatFormatting.GRAY + p.toString() + EnumChatFormatting.AQUA);
				}
				
				if (team != null && names != null) {
					event.player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + team.getRegisteredName() + ": " + names));
				}			
			}
		}
	}	
	
	public void onPlayerLeave(PlayerLoggedOutEvent event) {
		
		FWPlayerStats stats = FWPlayerStats.get(event.player);
		stats.isLoggedIn = false;
	}
}

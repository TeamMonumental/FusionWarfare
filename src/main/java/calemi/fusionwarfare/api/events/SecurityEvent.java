package calemi.fusionwarfare.api.events;

import calemi.fusionwarfare.api.ISecurity;
import calemi.fusionwarfare.event.CraftingEvent;
import calemi.fusionwarfare.tileentity.TileEntityReinforcedDoor;
import calemi.fusionwarfare.util.Location;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;

public class SecurityEvent {

	@SubscribeEvent
	public void onBlockPlace(PlaceEvent event) {
		
		if (!event.world.isRemote) {
			
			TileEntity tileEntity = event.world.getTileEntity(event.x, event.y, event.z);
			
			if (tileEntity instanceof ISecurity) {
				
				ISecurity security = (ISecurity) tileEntity;
				
				if (event.player.getTeam() == null) {
					
					event.player.addChatMessage(new ChatComponentText("You are not on a team, No security will be added!"));
					
				} else {
					
					security.setTeam(event.player.getTeam());					
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		if (!event.world.isRemote && event.action == Action.RIGHT_CLICK_BLOCK) {
			
			TileEntity tileEntity = event.world.getTileEntity(event.x, event.y, event.z);
			
			if (tileEntity instanceof ISecurity) {
				
				ISecurity security = (ISecurity) tileEntity;
				
				if (event.world.getTileEntity(event.x, event.y - 1, event.z) instanceof TileEntityReinforcedDoor) {
										
					onPlayerInteract(new PlayerInteractEvent(event.entityPlayer, event.action, event.x, event.y - 1, event.z, event.face, event.world));
				}
				
				if (!security.isSameTeam(event.entityPlayer.getTeam())) {
					
					event.entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This unit doesn't belong to you!"));
					event.setCanceled(true);
				}
			}
		}
	}
}

package calemi.fusionwarfare.api.events;

import calemi.fusionwarfare.api.ISecurity;
import calemi.fusionwarfare.event.CraftingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import net.minecraftforge.event.world.BlockEvent;

public class SecurityEvent {

	@SubscribeEvent
	public void onBlockPlace(BlockEvent.PlaceEvent event) {
		
		if (!event.world.isRemote) {
			
			TileEntity tileEntity = event.world.getTileEntity(event.x, event.y, event.z);
			
			if (tileEntity instanceof ISecurity) {
				
				ISecurity security = (ISecurity) tileEntity;
				
				if (event.player.getTeam() == null) {
					
					event.player.addChatMessage(new ChatComponentText("You are not on a team. No security will be added"));
					
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
				
				if (!security.isSameTeam(event.entityPlayer.getTeam())) {
					
					event.entityPlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This unit doesn't belong to you!"));
					event.setCanceled(true);
				}
			}
		}
	}
	
	private Team getTeamFromItem(ItemStack stack, World world) {
		
		NBTTagCompound nbt = getNBT(stack);
		
		Team team = null;
		
		if (nbt.hasKey("team")) {
			team = world.getScoreboard().getTeam(nbt.getString("team"));
		}
		
		return team;
	}
	
	private void setTeamToItem(ItemStack stack, Team team) {
		
		NBTTagCompound nbt = getNBT(stack);
		
		if (team != null) {
			nbt.setString("team", team.getRegisteredName());
		}
	}
	
	private NBTTagCompound getNBT(ItemStack stack) {
		
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		return stack.getTagCompound();
	}
}

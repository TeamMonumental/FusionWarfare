package calemi.fusionwarfare.event;

import java.util.List;

import sun.net.www.content.text.plain;
import calemi.fusionwarfare.item.ItemTeamCard;
import calemi.fusionwarfare.util.ToolSet;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.FakePlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftingEvent {

	@SubscribeEvent
	public void onCrafted(ItemCraftedEvent event) {
		
		if (event.crafting.getItem() instanceof ItemTeamCard) {			
			
			ItemStack tempCard = doesContainCard(event.craftMatrix);
			
			if (tempCard != null) {
				
				ItemTeamCard item = (ItemTeamCard)tempCard.getItem();
				
				List<String> list =	item.getNames(tempCard);
				
				item.setNames(event.crafting, list);
				EntityItem entity = new EntityItem(event.player.worldObj, event.player.posX, event.player.posY, event.player.posZ, tempCard);
				
				if (!event.player.worldObj.isRemote) {
					event.player.worldObj.spawnEntityInWorld(entity);
					if (!(event.player instanceof FakePlayer)) entity.onCollideWithPlayer(event.player);	
				}							
			}
		}			
	}
	
	private ItemStack doesContainCard(IInventory inventory) {
		
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			
			if (inventory.getStackInSlot(i) != null && inventory.getStackInSlot(i).getItem() instanceof ItemTeamCard) {			
				
				return inventory.getStackInSlot(i);				
			}
		}
		
		return null;
	}

}

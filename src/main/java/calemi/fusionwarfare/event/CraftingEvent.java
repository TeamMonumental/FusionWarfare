package calemi.fusionwarfare.event;

import java.util.List;

import sun.net.www.content.text.plain;
import calemi.fusionwarfare.item.ItemBattery;
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
			
		for (int i = 0; i < 8; i++) {		
			
			ItemStack stack = event.craftMatrix.getStackInSlot(i);
			
			if (stack != null && stack.getItem() instanceof ItemBattery) {
				
				int energy = ((ItemBattery)stack.getItem()).getNBT(stack).getInteger("energy");
				
				if (event.crafting != null) {			
					((ItemBattery)event.crafting.getItem()).getNBT(event.crafting).setInteger("energy", energy);
				}
			}
		}
	}
}

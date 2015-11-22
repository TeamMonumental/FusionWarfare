package calemi.fusionwarfare.event;

import calemi.fusionwarfare.api.IEnergy;
import calemi.fusionwarfare.block.BlockNetworkController;
import calemi.fusionwarfare.item.IEnergyItem;
import calemi.fusionwarfare.item.ItemBattery;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class CraftingEvent {

	private NBTTagCompound getNBT(ItemStack stack) {
		
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}		
		
		return stack.getTagCompound();
	}
	
	@SubscribeEvent
	public void onCrafted(ItemCraftedEvent event) {
				
		System.out.println(event.crafting);
		
		for (int i = 0; i < 8; i++) {		
			
			ItemStack stack = event.craftMatrix.getStackInSlot(i);
			
			if (stack != null) {
								
				if (stack.getItem() instanceof ItemBattery) {
				
					int energy = ((ItemBattery)stack.getItem()).getNBT(stack).getInteger("energy");
				
					if (event.crafting != null) {			
						((ItemBattery)event.crafting.getItem()).getNBT(event.crafting).setInteger("energy", energy);
					}
				}
				
				/*if (Block.getBlockFromItem(stack.getItem()) instanceof BlockNetworkController) {
					
					int energy = getNBT(stack).getInteger("energy");
					int maxEnergy = getNBT(stack).getInteger("maxEnergy");
												
					if (event.crafting != null) {
						getNBT(event.crafting).setInteger("energy", energy);									
						getNBT(event.crafting).setInteger("maxEnergy", maxEnergy + (getNBT(stack).getInteger("maxEnergy") == 50000 ? 50000 : 25000));
					}
				}*/
			}
		}
	}
}

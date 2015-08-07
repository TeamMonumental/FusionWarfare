package calemi.fusionwarfare.event;

import java.util.List;

import sun.net.www.content.text.plain;
import calemi.fusionwarfare.block.BlockNetworkController;
import calemi.fusionwarfare.item.ItemBattery;
import calemi.fusionwarfare.item.ItemBlockEnergyBase;
import calemi.fusionwarfare.item.tool.ItemArmorBase;
import calemi.fusionwarfare.item.tool.ItemFusionMatterDeconstructor;
import calemi.fusionwarfare.item.tool.ItemScubaGear;
import calemi.fusionwarfare.util.ToolSet;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraftforge.common.util.FakePlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftingEvent {

	@SubscribeEvent
	public void onCrafted(ItemCraftedEvent event) {
			
		for (int i = 0; i < 8; i++) {		
			
			ItemStack stack = event.craftMatrix.getStackInSlot(i);
			
			if (stack != null) {
								
				if (stack.getItem() instanceof ItemBattery) {
				
					int energy = ((ItemBattery)stack.getItem()).getNBT(stack).getInteger("energy");
				
					if (event.crafting != null) {			
						((ItemBattery)event.crafting.getItem()).getNBT(event.crafting).setInteger("energy", energy);
					}
				}
				
				if (Block.getBlockFromItem(stack.getItem()) instanceof BlockNetworkController) {
					
					int energy = ((ItemBlockEnergyBase)stack.getItem()).getEnergy(stack);
				
					if (event.crafting != null) {
						((ItemBlockEnergyBase)event.crafting.getItem()).setMaxEnergy(((BlockNetworkController)Block.getBlockFromItem(event.crafting.getItem())).tier * 25000, event.crafting);;
						((ItemBlockEnergyBase)event.crafting.getItem()).setEnergy(energy, event.crafting);
					}
				}
				
				if (stack.getItem() instanceof ItemFusionMatterDeconstructor) {
					
					int energy = ((ItemFusionMatterDeconstructor)stack.getItem()).getNBT(stack).getInteger("energy");
				
					if (event.crafting != null) {
						((ItemFusionMatterDeconstructor)event.crafting.getItem()).getNBT(event.crafting).setInteger("energy", energy);
					}
				}
			}
			
			if (event.crafting != null && event.crafting.getItem() instanceof ItemScubaGear) {
				
				event.crafting.addEnchantment(Enchantment.aquaAffinity, 5);
			}
		}
	}
}

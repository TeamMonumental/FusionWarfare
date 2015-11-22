package calemi.fusionwarfare.event;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.config.FWConfig;
import calemi.fusionwarfare.item.tool.ItemFusionMatterDeconstructor;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class TooltipEvent {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void tooltipEvent(ItemTooltipEvent event) {

		if (!FWConfig.disableTooltips) {
			
			if (event.itemStack.getItem() instanceof ItemArmor) {

				ItemArmor itemArmor = (ItemArmor) event.itemStack.getItem();

				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {

					event.toolTip.add(EnumChatFormatting.GOLD + "Protection: " + EnumChatFormatting.AQUA + itemArmor.damageReduceAmount);
					if (event.itemStack.getMaxDamage() > 0) event.toolTip.add(EnumChatFormatting.GOLD + "Durability: " + EnumChatFormatting.AQUA + (event.itemStack.getMaxDamage() - event.itemStack.getItemDamage()) + "/" + event.itemStack.getMaxDamage());

				} else {
					event.toolTip.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
				}
			}

			if (event.itemStack.getItem() instanceof ItemSword) {

				ItemSword itemSword = (ItemSword) event.itemStack.getItem();

				if (!event.itemStack.isItemEnchanted()) {
					event.toolTip.remove(1);
				}
			
				for (int i = 0; i < event.toolTip.toArray().length; i++) {
				
					if (event.toolTip.get(i).contains("+")) {				
						event.toolTip.remove(i);
					}
				}
			
				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {

					event.toolTip.add(EnumChatFormatting.GOLD + "Sharpness: " + EnumChatFormatting.AQUA + (4.0F + ((ItemSword)event.itemStack.getItem()).func_150931_i()));
					event.toolTip.add(EnumChatFormatting.GOLD + "Durability: " + EnumChatFormatting.AQUA + (event.itemStack.getMaxDamage() - event.itemStack.getItemDamage()) + "/" + event.itemStack.getMaxDamage());

				} else {
					event.toolTip.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
				}
			}

			if (event.itemStack.getItem() instanceof ItemTool) {

				ItemTool itemTool = (ItemTool) event.itemStack.getItem();

				if (!event.itemStack.isItemEnchanted()) {
					event.toolTip.remove(1);
				}
			
				for (int i = 0; i < event.toolTip.toArray().length; i++) {
				
					if (event.toolTip.get(i).contains("+")) {				
						event.toolTip.remove(i);
					}
				}
			
				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {

					event.toolTip.add(EnumChatFormatting.GOLD + "Speed: " + EnumChatFormatting.AQUA + ((ItemTool)event.itemStack.getItem()).func_150913_i().getEfficiencyOnProperMaterial());

					if (event.itemStack.getItem() instanceof ItemFusionMatterDeconstructor) {
						
						ItemFusionMatterDeconstructor itemFusionTool = (ItemFusionMatterDeconstructor) event.itemStack.getItem();
						event.toolTip.add(EnumChatFormatting.GOLD + "Sharpness: " + EnumChatFormatting.AQUA + (4.0F + Item.ToolMaterial.valueOf(itemTool.getToolMaterialName()).getDamageVsEntity()));
						event.toolTip.add(EnumChatFormatting.GOLD + "FE: " + EnumChatFormatting.AQUA + itemFusionTool.getEnergy(event.itemStack) + "/" + itemFusionTool.getMaxEnergy());
					}
					
					else {
						event.toolTip.add(EnumChatFormatting.GOLD + "Durability: " + EnumChatFormatting.AQUA + (event.itemStack.getMaxDamage() - event.itemStack.getItemDamage()) + "/" + event.itemStack.getMaxDamage());
					}

				} else {
					event.toolTip.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
				}
			}	
		}
	}
}

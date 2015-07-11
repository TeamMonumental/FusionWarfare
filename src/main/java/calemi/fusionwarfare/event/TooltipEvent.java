package calemi.fusionwarfare.event;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.config.FWConfig;
import calemi.fusionwarfare.gui.recipe.GuiTwoInputsRecipe;
import calemi.fusionwarfare.item.tool.ItemFusionMatterDeconstructor;
import calemi.fusionwarfare.key.KeyBindings;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TooltipEvent {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void tooltipEvent(ItemTooltipEvent event) {

		if (!FWConfig.disableTooltips) {
			
			if (event.itemStack.getItem() instanceof ItemArmor) {

			ItemArmor itemArmor = (ItemArmor) event.itemStack.getItem();

				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {

					event.toolTip.add(EnumChatFormatting.GOLD + "Protection: " + EnumChatFormatting.AQUA + itemArmor.damageReduceAmount);
					event.toolTip.add(EnumChatFormatting.GOLD + "Durability: " + EnumChatFormatting.AQUA + (event.itemStack.getMaxDamage() - event.itemStack.getItemDamage()) + "/" + event.itemStack.getMaxDamage());

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

					event.toolTip.add(EnumChatFormatting.GOLD + "Sharpness: " + EnumChatFormatting.AQUA + (4.0F + Item.ToolMaterial.valueOf(itemSword.getToolMaterialName()).getDamageVsEntity()));
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

					event.toolTip.add(EnumChatFormatting.GOLD + "Speed: " + EnumChatFormatting.AQUA + Item.ToolMaterial.valueOf(itemTool.getToolMaterialName()).getEfficiencyOnProperMaterial());

					if (event.itemStack.getItem() instanceof ItemFusionMatterDeconstructor) {
						ItemFusionMatterDeconstructor itemFusionTool = (ItemFusionMatterDeconstructor) event.itemStack.getItem();
						event.toolTip.add(EnumChatFormatting.GOLD + "Sharpness: " + EnumChatFormatting.AQUA + (4.0F + Item.ToolMaterial.valueOf(itemTool.getToolMaterialName()).getDamageVsEntity()));
						event.toolTip.add(EnumChatFormatting.GOLD + "Energy: " + EnumChatFormatting.AQUA + itemFusionTool.getEnergy(event.itemStack) + "/" + itemFusionTool.getMaxEnergy());
					}
					
					else {
						event.toolTip.add(EnumChatFormatting.GOLD + "Durability: " + EnumChatFormatting.AQUA + (event.itemStack.getMaxDamage() - event.itemStack.getItemDamage()) + "/" + event.itemStack.getMaxDamage());
					}

				} else {
					event.toolTip.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
				}
			}	
		}
		
		if (Keyboard.isKeyDown(KeyBindings.recipeButton.getKeyCode())) {

			for (TwoInputRecipe currentRecipe : TwoInputRecipeRegistry.getRecipes(EnumRecipeType.INFUSION_TABLE)) {

				if (event.itemStack.getItem() == currentRecipe.output.getItem()) {

					FMLClientHandler.instance().displayGuiScreen(event.entityPlayer, new GuiTwoInputsRecipe(event.entityPlayer, currentRecipe.recipeType, TwoInputRecipeRegistry.getRecipeIndex(EnumRecipeType.INFUSION_TABLE, currentRecipe)));
				}
			}
			
			for (TwoInputRecipe currentRecipe : TwoInputRecipeRegistry.getRecipes(EnumRecipeType.MISSILE_FACTORY)) {

				if (event.itemStack.getItem() == currentRecipe.output.getItem()) {

					FMLClientHandler.instance().displayGuiScreen(event.entityPlayer, new GuiTwoInputsRecipe(event.entityPlayer, currentRecipe.recipeType, TwoInputRecipeRegistry.getRecipeIndex(EnumRecipeType.MISSILE_FACTORY, currentRecipe)));
				}
			}
		}
	}
}

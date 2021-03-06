package calemi.fusionwarfare.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import calemi.fusionwarfare.item.tool.ItemArmorBase;
import calemi.fusionwarfare.item.tool.ItemAxeBase;
import calemi.fusionwarfare.item.tool.ItemPickaxeBase;
import calemi.fusionwarfare.item.tool.ItemShovelBase;
import calemi.fusionwarfare.item.tool.ItemSwordBase;

public class ToolSet {
	
	public ItemSwordBase sword;
	public ItemPickaxeBase pickaxe;
	public ItemShovelBase shovel;	
	public ItemAxeBase axe;
	
	public ItemArmorBase helmet;
	public ItemArmorBase chestplate;
	public ItemArmorBase leggings;
	public ItemArmorBase boots;
	
	public ToolSet(String imagePath, ToolMaterial toolMaterial, ArmorMaterial armorMaterial, int armorRenderID, Item recipeItem, boolean hasCraftingRecipe, boolean hasOverlay) {
		
		sword = new ItemSwordBase(imagePath + "_sword", toolMaterial, hasOverlay);
		pickaxe = new ItemPickaxeBase(imagePath + "_pickaxe", toolMaterial, hasOverlay);
		shovel = new ItemShovelBase(imagePath + "_shovel", toolMaterial, hasOverlay);		
		axe = new ItemAxeBase(imagePath + "_axe", toolMaterial, hasOverlay);		
		
		helmet = new ItemArmorBase(imagePath + "_helmet", imagePath, "helmet", armorMaterial, armorRenderID, 0, hasOverlay);
		chestplate = new ItemArmorBase(imagePath + "_chestplate", imagePath, "chestplate", armorMaterial, armorRenderID, 1, hasOverlay);
		leggings = new ItemArmorBase(imagePath + "_leggings", imagePath, "leggings", armorMaterial, armorRenderID, 2, hasOverlay);
		boots = new ItemArmorBase(imagePath + "_boots", imagePath, "boots", armorMaterial, armorRenderID, 3, hasOverlay);
				
		if (hasCraftingRecipe) {
			
			GameRegistry.addRecipe(new ItemStack(pickaxe), new Object[] {
				"XXX", " S ", " S ", 'S', Items.stick, 'X', recipeItem
			});
			
			GameRegistry.addRecipe(new ItemStack(axe), new Object[] {
				"XX", "XS", " S", 'S', Items.stick, 'X', recipeItem
			});
			
			GameRegistry.addRecipe(new ItemStack(shovel), new Object[] {
				"X", "S", "S", 'S', Items.stick, 'X', recipeItem
			});
			
			GameRegistry.addRecipe(new ItemStack(sword), new Object[] {
				"X", "X", "S", 'S', Items.stick, 'X', recipeItem
			});
			
			GameRegistry.addRecipe(new ItemStack(helmet), new Object[] {
				"XXX", "X X", 'X', recipeItem
			});
			
			GameRegistry.addRecipe(new ItemStack(chestplate), new Object[] {
				"X X", "XXX", "XXX", 'X', recipeItem
			});
			
			GameRegistry.addRecipe(new ItemStack(leggings), new Object[] {
				"XXX", "X X", "X X", 'X', recipeItem
			});
			
			GameRegistry.addRecipe(new ItemStack(boots), new Object[] {
				"X X", "X X", 'X', recipeItem
			});			
		}		
	}
}
package calemi.fusionwarfare.init;

import java.util.Random;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.util.WeightedRandomFishable;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.DungeonHooks;
import net.minecraftforge.common.FishingHooks;

public class InitHooks {

	public static void init() {
		
		FishingHooks.addFish(new WeightedRandomFishable(new ItemStack(InitItems.fusion_fish), 100));
		
		addItemToChest(ChestGenHooks.VILLAGE_BLACKSMITH, InitItems.steel.sword, 1, 1, 10);
		addItemToChest(ChestGenHooks.VILLAGE_BLACKSMITH, InitItems.steel.pickaxe, 1, 1, 10);
		
		addItemToChest(ChestGenHooks.VILLAGE_BLACKSMITH, InitItems.steel.helmet, 1, 1, 7);
		addItemToChest(ChestGenHooks.VILLAGE_BLACKSMITH, InitItems.steel.chestplate, 1, 1, 5);
		addItemToChest(ChestGenHooks.VILLAGE_BLACKSMITH, InitItems.steel.leggings, 1, 1, 5);
		addItemToChest(ChestGenHooks.VILLAGE_BLACKSMITH, InitItems.steel.boots, 1, 1, 7);
	}
	
	private static void addItemToChest(String catagory, Item item, int minStack, int maxStack, int rarity) {
		ChestGenHooks.getInfo(catagory).addItem(new WeightedRandomChestContent(new ItemStack(item), minStack, maxStack, rarity));
	}
}

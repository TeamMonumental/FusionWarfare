package calemi.fusionwarfare.loot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import calemi.fusionwarfare.item.IEnergyItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import scala.Array;

public class CrateLootRegistry {

	public static List<CrateLoot> lootList = new ArrayList<CrateLoot>();
	
	public static void add(CrateLoot loot) {
		lootList.add(loot);
	}
	
	public static ItemStack[] genLoot(int size, EnumCrateRarity rarity) {
		
		ItemStack[] stacks = new ItemStack[size];	
		
		List<CrateLoot> buffer = new ArrayList<CrateLoot>();
		
		for (int i = 0; i < size; i++) {
			
			CrateLoot temp = genLootItem(rarity, buffer);
			
			buffer.add(temp);			
			
			stacks[i] = createItem(temp);
		}
		
		return stacks;
	}
	
	public static ItemStack createItem(CrateLoot loot) {
		
		int amount = MathHelper.getRandomIntegerInRange(new Random(), loot.countMin, loot.countMax);
		
		ItemStack tempStack = new ItemStack(loot.item, amount);	
		
		if (tempStack.getItem() instanceof IEnergyItem) {
			
			IEnergyItem item = (IEnergyItem)tempStack.getItem();
			
			item.setEnergy(tempStack, item.getMaxEnergy());			
		}
		
		return tempStack;
	}
	
	public static CrateLoot genLootItem(EnumCrateRarity rarity, List<CrateLoot> buffer) {
		
		CrateLoot temp = null;
		
		List<CrateLoot> tempList = new ArrayList<CrateLoot>();
		
		tempList.addAll(lootList);
		
		Collections.shuffle(tempList);
		
		for (CrateLoot loot : tempList) {
			
			if (loot.rarity == rarity) {
				
				temp = loot;
				break;
			}			
		}
		
		if (!isUniqueLoot(temp, buffer)) {
			
			temp = genLootItem(rarity, buffer);			
		}
		
		return temp;
	}
	
	public static boolean isUniqueLoot(CrateLoot loot, List<CrateLoot> buffer) {
		
		boolean hasCritical = false;
		boolean hasDupe = false;
		
		for (CrateLoot tempLoot : buffer) {
			
			if (tempLoot.critical) {			
				hasCritical = true;
			}
			
			if (tempLoot.item == loot.item && loot.checkDupe) {
				hasDupe = true;
			}
		}
		
		if (loot.critical && hasCritical) {
			return false;
		}
		
		if (hasDupe) {
			return false;
		}
		
		return true;
	}
}

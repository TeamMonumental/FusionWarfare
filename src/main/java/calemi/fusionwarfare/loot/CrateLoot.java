package calemi.fusionwarfare.loot;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CrateLoot {

	public EnumCrateRarity rarity;
	
	public Item item;
	
	public int countMin, countMax;
	public boolean critical;
	
	public CrateLoot(EnumCrateRarity rarity, Item item, int countMin, int countMax, boolean critical) {
		this.rarity = rarity;
		this.item = item;
		this.countMin = countMin;
		this.countMax = countMax;
		this.critical = critical;
	}	
}

package calemi.fusionwarfare.init;

import calemi.fusionwarfare.loot.CrateLoot;
import calemi.fusionwarfare.loot.CrateLootRegistry;
import calemi.fusionwarfare.loot.EnumCrateRarity;

public class InitCrateLoot {
	
	public static void init() {
		
		//BLUE_NORMAL\\
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.infused_crystal, 1, 16, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.infused_azurite, 1, 8, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.steel_ingot, 1, 16, false));
		
		//BLUE_CRITICAL\\
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.basic_chip, 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.basic_battery, 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.dudley_missile, 1, 1, true));	
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.missile_module_1, 1, 1, true));
	}
}

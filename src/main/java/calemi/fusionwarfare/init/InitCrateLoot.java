package calemi.fusionwarfare.init;

import calemi.fusionwarfare.loot.CrateLoot;
import calemi.fusionwarfare.loot.CrateLootRegistry;
import calemi.fusionwarfare.loot.EnumCrateRarity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

public class InitCrateLoot {
	
	public static void init() {
		
		//BLUE_NORMAL\\
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.fusion_auto_pistol, 1, 1, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.fusion_pistol, 1, 1, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.infused_steel_ingot, 5, 10, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.fusion_ammo, 24,32, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.steel_plate, 6, 12, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.infused_circuit, 2, 2, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.basic_chip, 1, 1, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.dudley_missile, 2, 2, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.wrench, 1, 1, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.location_linker, 1, 1, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.fusion_fish, 8, 12, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, Item.getItemFromBlock(InitBlocks.network_cable), 8, 16, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.steel.pickaxe, 1, 1, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.steel.sword, 1, 1, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, Item.getItemFromBlock(InitBlocks.fusion_torch), 32, 64, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, Item.getItemFromBlock(Blocks.iron_block), 2, 4, false));
		
		
		
		
		
		
		//BLUE_CRITICAL\\
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, Item.getItemFromBlock(InitBlocks.network_controller_2), 2, 4, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.pyroblast_missile, 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.velocity_missile_T2, 1, 1, true));	
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.breaching_missile_T2, 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.emp_missile_T2, 1, 1, true));
		
		//ORANGE_NORMAL\\
		
		//ORANGE_CRITICAL\\
		
		//RED_NORMAL\\
		
		//RED_CRITICAL\\
	}
}

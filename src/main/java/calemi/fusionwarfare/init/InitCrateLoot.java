package calemi.fusionwarfare.init;

import calemi.fusionwarfare.loot.CrateLoot;
import calemi.fusionwarfare.loot.CrateLootRegistry;
import calemi.fusionwarfare.loot.EnumCrateRarity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class InitCrateLoot {
	
	public static void init() {
		
		//BLUE_NORMAL\\
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.fusion_auto_pistol, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.fusion_pistol, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.infused_steel_ingot, 3, 5, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.fusion_ammo, 24, 32, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.steel_plate, 4, 8, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.infused_circuit, 2, 2, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.basic_chip, 1, 1, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.dudley_missile, 2, 2, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.wrench, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.location_linker, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.fusion_fish, 8, 12, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, Item.getItemFromBlock(InitBlocks.network_cable), 8, 16, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.steel.pickaxe, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.steel.sword, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, Item.getItemFromBlock(InitBlocks.fusion_torch), 32, 64, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, Item.getItemFromBlock(Blocks.iron_block), 2, 4, false, true));
		
		
		
		
		
		
		//BLUE_CRITICAL\\
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, Item.getItemFromBlock(InitBlocks.network_controller_2), 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.pyroblast_missile, 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.velocity_missile_T2, 1, 1, true));	
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.breaching_missile_T2, 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.BLUE, InitItems.emp_missile_T2, 1, 1, true));
		
		//ORANGE_NORMAL\\
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.fusion_shotgun, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.fusion_smg, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.fusion_ammo, 48, 64, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.charged_seeds, 48, 64, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.advanced_battery, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.steel_plate, 10, 16, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.infused_circuit, 3, 5, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.advanced_chip, 1, 2, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.overclocking_chip, 3, 8, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.velocity_missile_T2, 1, 2, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.emp_missile_T2, 1, 2, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, Items.emerald, 1, 2, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, Item.getItemFromBlock(Blocks.gold_block), 1, 2, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, Item.getItemFromBlock(InitBlocks.concrete), 48, 64, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, Item.getItemFromBlock(InitBlocks.steel_casing), 8, 16, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, Item.getItemFromBlock(InitBlocks.network_cable), 16, 32, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.infused_steel.pickaxe, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.infused_steel.sword, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.breaching_missile_T3, 2, 2, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, Item.getItemFromBlock(InitBlocks.missile_launcher), 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, InitItems.advanced_infused_catalyst, 2, 2, false, true));
		
		
		//ORANGE_CRITICAL\\
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, Item.getItemFromBlock(InitBlocks.player_healing_beacon), 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, Item.getItemFromBlock(InitBlocks.mining_unit), 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, Item.getItemFromBlock(InitBlocks.emp_tower), 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.ORANGE, Item.getItemFromBlock(InitBlocks.aura_turret), 1, 1, true));
		
		//RED_NORMAL\\
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.fusion_ammo, 100, 128, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.rocket, 3, 5, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.hyper_chip, 1, 2, false));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.hyper_battery, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.lightning_rod, 1, 2, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.velocity_missile_T2, 2, 2, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.emp_missile_T2, 2, 2, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.overclocking_chip, 10, 15, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, Items.golden_apple, 1, 2, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, Item.getItemFromBlock(Blocks.diamond_block), 1, 2, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, Item.getItemFromBlock(InitBlocks.concrete), 100, 128, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, Item.getItemFromBlock(InitBlocks.network_controller_2), 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.fusion_matter_deconstructor, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.infused_steel.boots, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.infused_steel.helmet, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.infused_steel.leggings, 1, 1, false, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.infused_steel.chestplate, 1, 1, false, true));
		
		//RED_CRITICAL\\
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.fusion_sniper_rifle, 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.rocket_launcher, 1, 1, true));
		CrateLootRegistry.add(new CrateLoot(EnumCrateRarity.RED, InitItems.fusion_gatling_gun, 1, 1, true));
	}
}

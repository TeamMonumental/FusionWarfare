package calemi.fusionwarfare.init;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.item.ItemBase;
import calemi.fusionwarfare.item.ItemBattery;
import calemi.fusionwarfare.item.ItemChargedSeeds;
import calemi.fusionwarfare.item.ItemCreativeBattery;
import calemi.fusionwarfare.item.ItemFoodBase;
import calemi.fusionwarfare.item.ItemFusionGatlingGun;
import calemi.fusionwarfare.item.ItemFusionGun;
import calemi.fusionwarfare.item.ItemMissile;
import calemi.fusionwarfare.item.ItemMissileModule;
import calemi.fusionwarfare.item.ItemReinforcedDoor;
import calemi.fusionwarfare.item.ItemRocketLauncher;
import calemi.fusionwarfare.item.ItemTest;
import calemi.fusionwarfare.item.tool.ItemDebugger;
import calemi.fusionwarfare.item.tool.ItemDesignator;
import calemi.fusionwarfare.item.tool.ItemFusionMatterDeconstructor;
import calemi.fusionwarfare.item.tool.ItemLocationLinker;
import calemi.fusionwarfare.item.tool.ItemScubaGear;
import calemi.fusionwarfare.item.tool.ItemSprayer;
import calemi.fusionwarfare.item.tool.ItemWrench;
import calemi.fusionwarfare.util.ToolSet;
import calemi.fusionwarfare.util.missile.MissileTypeRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class InitItems {

	//#-#-#-#-#-RESOURCES-#-#-#-#-#\\
	
	public static Item infused_crystal;	
	public static Item infused_catalyst;
	public static Item advanced_infused_catalyst;			
	public static Item infused_azurite;
	public static Item fusion_fish;	
	public static Item fusion_fish_cooked;
	public static Item charged_seeds;
	
	//#-#-#-#-#-INGOTS-#-#-#-#-#\\
	
	public static Item steel_ingot;
	public static Item infused_steel_ingot;
	public static Item infused_redstone;
	
	//#-#-#-#-#-INGREDIENTS-#-#-#-#-#\\
	
	public static Item steel_mixture;
	public static Item evaporation_cell;
	public static Item infused_circuit;
	public static Item steel_plate;	
	public static Item lightning_rod;
	public static Item sturdy_handle;
	public static Item solar_panel;	
	
	public static Item missile_module_1;
	public static Item missile_module_2;
	public static Item missile_module_3;
	
	public static Item basic_chip;
	public static Item advanced_chip;
	public static Item hyper_chip;
	
	public static Item gun_core;
	public static Item gun_barrel;
	public static Item gun_handle;
	
	//#-#-#-#-#-BATTERIES-#-#-#-#-#\\
	
	public static Item basic_battery;
	public static Item advanced_battery;
	public static Item hyper_battery;
	public static Item creative_battery;
	
	//#-#-#-#-#-GUNS-#-#-#-#-#\\
	
	public static Item fusion_ammo;
	public static Item rocket;
	
	public static Item fusion_pistol;
	public static Item fusion_auto_pistol;
	public static Item fusion_shotgun;
	public static Item fusion_smg;
	public static Item fusion_sniper_rifle;
	public static Item fusion_gatling_gun;
	
	public static Item rocket_launcher;
	
	//#-#-#-#-#-MISSILES-#-#-#-#-#\\
	
	public static Item velocity_missile_T1;
	public static Item velocity_missile_T2;
	public static Item velocity_missile_T3;
	
	public static Item breaching_missile_T1;
	public static Item breaching_missile_T2;
	public static Item breaching_missile_T3;
	
	public static Item emp_missile_T1;
	public static Item emp_missile_T2;
	public static Item emp_missile_T3;
	
	public static Item pyroblast_missile;
	public static Item dudley_missile;
	
	//#-#-#-#-#-TOOLS-#-#-#-#-#\\
	
	public static ToolSet steel, infused_steel, infused_steel_red;
	
	public static Item scuba_helmet, scuba_chestplate, scuba_leggings, scuba_boots;
	
	public static Item fusion_matter_deconstructor, fusion_matter_deconstructor_red;
	
	public static Item wrench;
	public static Item debugger;
	public static Item location_linker;
	public static Item sprayer;	
	public static Item designator;
		
	//#-#-#-#-#-MISC-#-#-#-#-#\\
	
	public static Item reinforced_door;
	public static Item overclocking_chip;	
	public static Item test;
	
	public static void init() {

		//#-#-#-#-#-RESOURCES-#-#-#-#-#\\
		
		infused_crystal = new ItemBase("infused_crystal");
		infused_catalyst = new ItemBase("infused_catalyst");
		advanced_infused_catalyst = new ItemBase("advanced_infused_catalyst", true, true).setMaxDamage(10000).setMaxStackSize(1);	
		infused_azurite = new ItemBase("infused_azurite");
		fusion_fish = new ItemBase("fusion_fish");	
		fusion_fish_cooked = new ItemFoodBase("fusion_fish_cooked", 10, 0.7F, false);
		charged_seeds = new ItemChargedSeeds();
		
		//#-#-#-#-#-INGOTS-#-#-#-#-#\\
				
		steel_ingot = new ItemBase("steel_ingot");		
		infused_steel_ingot = new ItemBase("infused_steel_ingot");
		infused_redstone = new ItemBase("infused_redstone");
		
		//#-#-#-#-#-INGREDIENTS-#-#-#-#-#\\
		
		steel_mixture = new ItemBase("steel_mixture");
		evaporation_cell = new ItemBase("evaporation_cell");
		steel_plate = new ItemBase("steel_plate");		
		lightning_rod = new ItemBase("lightning_rod");
		sturdy_handle = new ItemBase("sturdy_handle");
		solar_panel = new ItemBase("solar_panel");
		
		missile_module_1 = new ItemMissileModule(1);
		missile_module_2 = new ItemMissileModule(2);
		missile_module_3 = new ItemMissileModule(3);
		
		basic_chip = new ItemBase("basic_chip");
		advanced_chip = new ItemBase("advanced_chip");
		hyper_chip = new ItemBase("hyper_chip");
		
		gun_core = new ItemBase("gun_core");
		gun_barrel = new ItemBase("gun_barrel");
		gun_handle = new ItemBase("gun_handle");
		
		infused_circuit = new ItemBase("infused_circuit");
		
		//#-#-#-#-#-BATTERIES-#-#-#-#-#\\
			
		basic_battery = new ItemBattery("basic", 5000);
		advanced_battery = new ItemBattery("advanced", 10000);
		hyper_battery = new ItemBattery("hyper", 20000);
		
		creative_battery = new ItemCreativeBattery();	
		
		//#-#-#-#-#-GUNS-#-#-#-#-#\\
		
		fusion_ammo = new ItemBase("fusion_ammo").setCreativeTab(InitCreativeTabs.creativeTabInfantry);
		rocket = new ItemBase("rocket", false, false).setCreativeTab(InitCreativeTabs.creativeTabInfantry);
		
		fusion_pistol = new ItemFusionGun("fusion_pistol", 10, 1, 12, 1, 0.08F, false);
		fusion_auto_pistol = new ItemFusionGun("fusion_auto_pistol", 0, 1, 10, 3, 0.08F, false);
		fusion_shotgun = new ItemFusionGun("fusion_shotgun", 30, 6, 30, 8, 0.09F, false);
		fusion_smg = new ItemFusionGun("fusion_smg", 0, 1, 10, 2, 0.04F, false);
		fusion_sniper_rifle = new ItemFusionGun("fusion_sniper_rifle", 100, 1, 60, 0, 0.002F, true);	
		fusion_gatling_gun = new ItemFusionGatlingGun("fusion_gatling_gun", 1, 20, 1, 0.06F);
		
		rocket_launcher = new ItemRocketLauncher();
		
		//#-#-#-#-#-MISSILES-#-#-#-#-#\\
		
		velocity_missile_T1 = new ItemMissile(MissileTypeRegistry.velocity_1);
		velocity_missile_T2 = new ItemMissile(MissileTypeRegistry.velocity_2);
		velocity_missile_T3 = new ItemMissile(MissileTypeRegistry.velocity_3);
		
		breaching_missile_T1 = new ItemMissile(MissileTypeRegistry.breaching_1);
		breaching_missile_T2 = new ItemMissile(MissileTypeRegistry.breaching_2);
		breaching_missile_T3 = new ItemMissile(MissileTypeRegistry.breaching_3);
		
		emp_missile_T1 = new ItemMissile(MissileTypeRegistry.emp_1);
		emp_missile_T2 = new ItemMissile(MissileTypeRegistry.emp_2);
		emp_missile_T3 = new ItemMissile(MissileTypeRegistry.emp_3);
		
		pyroblast_missile = new ItemMissile(MissileTypeRegistry.pyroblast);
		dudley_missile = new ItemMissile(MissileTypeRegistry.dudley);
		
		//#-#-#-#-#-TOOLS-#-#-#-#-#\\		
		
		steel = new ToolSet("steel", InitToolMaterials.toolMaterialSteel, InitArmorMaterials.toolMaterialSteel, Reference.armorIDSteel, steel_ingot, true, false);
		infused_steel = new ToolSet("infused_steel", InitToolMaterials.toolMaterialInfusedSteel, InitArmorMaterials.toolMaterialInfusedSteel, Reference.armorIDInfusedSteel, infused_steel_ingot, true, true);
		infused_steel_red = new ToolSet("infused_steel_red", InitToolMaterials.toolMaterialInfusedSteel, InitArmorMaterials.toolMaterialInfusedSteel, Reference.armorIDInfusedSteel, infused_steel_ingot, false, true);
		
		scuba_helmet = new ItemScubaGear("scuba_helmet", "scuba_gear", InitArmorMaterials.toolMaterialScuba, Reference.armorIDScuba, 0);
		scuba_chestplate = new ItemScubaGear("scuba_chestplate", "scuba_gear", InitArmorMaterials.toolMaterialScuba, Reference.armorIDScuba, 1);
		scuba_leggings = new ItemScubaGear("scuba_leggings", "scuba_gear", InitArmorMaterials.toolMaterialScuba, Reference.armorIDScuba, 2);
		scuba_boots = new ItemScubaGear("scuba_boots", "scuba_gear", InitArmorMaterials.toolMaterialScuba, Reference.armorIDScuba, 3);
		
		fusion_matter_deconstructor = new ItemFusionMatterDeconstructor(false);
		fusion_matter_deconstructor_red = new ItemFusionMatterDeconstructor(true);
			
		wrench = new ItemWrench();
		debugger = new ItemDebugger();
		location_linker = new ItemLocationLinker();
		sprayer = new ItemSprayer();		
		designator = new ItemDesignator();
		
		//#-#-#-#-#-MISC-#-#-#-#-#\\
		
		//book = new ItemBook();
		reinforced_door = new ItemReinforcedDoor();
		overclocking_chip = new ItemBase("overclocking_chip").setMaxStackSize(15);
		test = new ItemTest();		
		
		OreDictionary.registerOre("gemInfused", infused_crystal);
		OreDictionary.registerOre("gemInfusedAzurite", infused_azurite);
	}
}

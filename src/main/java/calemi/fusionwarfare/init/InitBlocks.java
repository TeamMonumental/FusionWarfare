package calemi.fusionwarfare.init;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.block.BlockBasicMachineBase;
import calemi.fusionwarfare.block.BlockChargedPlant;
import calemi.fusionwarfare.block.BlockFusionTorch;
import calemi.fusionwarfare.block.BlockNetworkCable;
import calemi.fusionwarfare.block.BlockNetworkController;
import calemi.fusionwarfare.block.BlockNetworkGate;
import calemi.fusionwarfare.block.BlockOreBase;
import calemi.fusionwarfare.block.BlockReactorCoolingUnit;
import calemi.fusionwarfare.block.BlockReinforceable;
import calemi.fusionwarfare.block.BlockReinforceableGlass;
import calemi.fusionwarfare.block.BlockReinforcedDoor;
import calemi.fusionwarfare.block.BlockSteelCasing;
import calemi.fusionwarfare.block.BlockSupplyCrate;
import calemi.fusionwarfare.block.BlockTwoInputs;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.tileentity.TileEntityEnergyReceiver;
import calemi.fusionwarfare.tileentity.TileEntityEnergyTransmitter;
import calemi.fusionwarfare.tileentity.gen.TileEntityAquaGenerator;
import calemi.fusionwarfare.tileentity.gen.TileEntityGeothermalGenerator;
import calemi.fusionwarfare.tileentity.gen.TileEntitySolarGenerator;
import calemi.fusionwarfare.tileentity.gen.TileEntityWindTurbine;
import calemi.fusionwarfare.tileentity.machine.TileEntityAntiMobBeacon;
import calemi.fusionwarfare.tileentity.machine.TileEntityAuraTurret;
import calemi.fusionwarfare.tileentity.machine.TileEntityEMPTower;
import calemi.fusionwarfare.tileentity.machine.TileEntityEXPFabricator;
import calemi.fusionwarfare.tileentity.machine.TileEntityEnergeticFurnace;
import calemi.fusionwarfare.tileentity.machine.TileEntityFusionMatterReinforcer;
import calemi.fusionwarfare.tileentity.machine.TileEntityMiningUnit;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileSiloCore;
import calemi.fusionwarfare.tileentity.machine.TileEntityOreEnricher;
import calemi.fusionwarfare.tileentity.machine.TileEntityPlayerHealingBeacon;
import calemi.fusionwarfare.tileentity.machine.TileEntityRFConverter;
import calemi.fusionwarfare.tileentity.reactor.TileEntityAdvancedHydroReactorCore;
import calemi.fusionwarfare.tileentity.reactor.TileEntityCapsuleCore;
import calemi.fusionwarfare.tileentity.reactor.TileEntityReactorCore;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;

public class InitBlocks {
	
	//#-#-#-#-#-ORE-#-#-#-#-#\\
	
	public static Block infused_crystal_ore;
	public static Block infused_catalyst_ore;
	public static Block infused_azurite_ore;
	
	//#-#-#-#-#-BUILDING-#-#-#-#-#\\
	
	public static Block fusion_torch;
	
	public static Block concrete;
	public static Block reinforced_glass;
	
	public static Block black_brick;
	public static Block blue_brick;
	public static Block red_brick;
	
	public static Block steel_casing;
	
	public static Block reinforced_door;
	
	//#-#-#-#-#-NETWORK-#-#-#-#-#\\
	
	public static Block network_controller_1;
	public static Block network_controller_2;
	public static Block network_controller_3;
	public static Block network_cable;	
	public static Block network_gate;
	
	//#-#-#-#-#-GENERATORS-#-#-#-#-#\\
	
	public static Block aqua_generator;
	public static Block solar_generator;
	public static Block geothermal_generator;
	public static Block wind_turbine;
	
	//#-#-#-#-#-REACTORS-#-#-#-#-#\\
	
	public static Block reactor_core;
	public static Block reactor_cooling_unit;
	
	public static Block advanced_hydro_reactor_core;
	public static Block capsule_core;
	
	//#-#-#-#-#-MACHINES-#-#-#-#-#\\
	
	public static Block infusion_table;
	public static Block infusion_foundry;
	public static Block energetic_furnace;
	public static Block ore_enricher;
	public static Block mining_unit;
	public static Block missile_factory;
	public static Block missile_launcher;
	public static Block missile_silo_core;
	public static Block exp_fabricator;	
	public static Block emp_tower;
		
	public static Block aura_turret;
	
	public static Block fusion_matter_reinforcer;
	public static Block anti_mob_beacon;
	public static Block player_healing_beacon;
	
	public static Block energy_transmitter;
	public static Block energy_receiver;
	
	public static Block rf_converter;
	
	//#-#-#-#-#-MISC-#-#-#-#-#\\
		
	public static Block charged_plant;
	public static Block supply_crate;
	
	public static void init() {
		
		//#-#-#-#-#-ORE-#-#-#-#-#\\
		
		infused_crystal_ore = new BlockOreBase("infused_crystal_ore", InitItems.infused_crystal, 1, 2, 3, 7);
		infused_catalyst_ore = new BlockOreBase("infused_catalyst_ore", InitItems.infused_catalyst, 1, 1, 3, 7);
		infused_azurite_ore = new BlockOreBase("infused_azurite_ore", InitItems.infused_azurite, 1, 2, 0, 2);		
		
		//#-#-#-#-#-BUILDING-#-#-#-#-#\\
		
		fusion_torch = new BlockFusionTorch();
		
		concrete = new BlockReinforceable("concrete", 5);
		reinforced_glass = new BlockReinforceableGlass("reinforced_glass", 3);
				
		black_brick = new BlockReinforceable("black_brick", 5);
		blue_brick = new BlockReinforceable("blue_brick", 5);
		red_brick = new BlockReinforceable("red_brick", 5);
		
		steel_casing = new BlockSteelCasing();
		
		reinforced_door = new BlockReinforcedDoor();
		
		//#-#-#-#-#-NETWORK-#-#-#-#-#\\
	
		network_controller_1 = new BlockNetworkController(1);
		network_controller_2 = new BlockNetworkController(2);
		network_controller_3 = new BlockNetworkController(3);
		network_cable = new BlockNetworkCable();
		network_gate = new BlockNetworkGate();
		
		//#-#-#-#-#-GENERATORS-#-#-#-#-#\\
		
		aqua_generator = new BlockBasicMachineBase("aqua_generator", TileEntityAquaGenerator.class, Reference.guiIDAquaGenerator, false, "aqua_generator_side");
		solar_generator = new BlockBasicMachineBase("solar_generator", TileEntitySolarGenerator.class, Reference.guiIDSolarGenerator, false, true, "solar_generator_top", "steel_casing", "mech_side");		
		geothermal_generator = new BlockBasicMachineBase("geothermal_generator", TileEntityGeothermalGenerator.class, Reference.guiIDGeothermalGenerator, false, true, "mech_top_3", "steel_casing", "geothermal_generator_side");
		wind_turbine = new BlockBasicMachineBase("wind_turbine", TileEntityWindTurbine.class, Reference.guiIDWindTurbine, true, true, "mech_top_1", "steel_casing", "wind_turbine_side");		
		
		//#-#-#-#-#-REACTORS-#-#-#-#-#\\
		
		reactor_core = new BlockBasicMachineBase("reactor_core", TileEntityReactorCore.class, Reference.guiIDReactorCore, false, true, "reactor_core_side", "reactor_core_side", "reactor_core_side");
		reactor_cooling_unit = new BlockReactorCoolingUnit();
		
		advanced_hydro_reactor_core = new BlockBasicMachineBase("advanced_hydro_reactor_core", TileEntityAdvancedHydroReactorCore.class, Reference.guiIDAdvancedHydroReactorCore, false, true, "advanced_hydro_reactor_core_top", "steel_casing", "advanced_hydro_reactor_core_side");
		capsule_core = new BlockBasicMachineBase("capsule_core", TileEntityCapsuleCore.class, 0, false, true, "capsule_core_side", "capsule_core_side", "capsule_core_side");
		
		//#-#-#-#-#-MACHINES-#-#-#-#-#\\
		
		infusion_table = new BlockTwoInputs("infusion_table", EnumRecipeType.INFUSION_TABLE, Reference.guiIDTwoInputs, "table_top", "infusion_table_side");	
		infusion_foundry = new BlockTwoInputs("infusion_foundry", EnumRecipeType.INFUSION_FOUNDRY, Reference.guiIDTwoInputs, "mech_top_1", "mech_side");	
		missile_factory = new BlockTwoInputs("missile_factory", EnumRecipeType.MISSILE_FACTORY, Reference.guiIDTwoInputs, "missile_factory_top", "missile_factory_side");	
		
		energetic_furnace = new BlockBasicMachineBase("energetic_furnace", TileEntityEnergeticFurnace.class, Reference.guiIDEnergeticFurnace, true);
		ore_enricher = new BlockBasicMachineBase("ore_enricher", TileEntityOreEnricher.class, Reference.guiIDOreEnricher, true);			
		mining_unit = new BlockBasicMachineBase("mining_unit", TileEntityMiningUnit.class, Reference.guiIDMiningUnit, false, true, "mech_top_1", "mining_unit_bottom", "mining_unit_side");	
		missile_launcher = new BlockBasicMachineBase("missile_launcher", "mech_particle", TileEntityMissileLauncher.class, Reference.guiIDMissileLauncher, 1, 0, 1, 15, 4, 15);
		missile_silo_core = new BlockBasicMachineBase("missile_silo_core", TileEntityMissileSiloCore.class, Reference.guiIDMissileSiloCore, false, true, "steel_casing", "steel_casing", "missile_silo_core");
		exp_fabricator = new BlockBasicMachineBase("exp_fabricator", TileEntityEXPFabricator.class, Reference.guiIDEXPFabricator, false, true, "exp_fabricator_top", "steel_casing", "exp_fabricator_side");
		emp_tower = new BlockBasicMachineBase("emp_tower", "mech_particle", TileEntityEMPTower.class, Reference.guiIDEMPTower, 2, 0, 2, 14, 16, 14);
		
		aura_turret = new BlockBasicMachineBase("aura_turret", "mech_particle", TileEntityAuraTurret.class, Reference.guiIDAuraTurret, 3, 0, 3, 13, 12, 13).setHardness(50F);
		
		fusion_matter_reinforcer = new BlockBasicMachineBase("fusion_matter_reinforcer", TileEntityFusionMatterReinforcer.class, Reference.guiIDFusionMatterReinforcer, false, "fusion_matter_reinforcer_side");	
		anti_mob_beacon = new BlockBasicMachineBase("anti_mob_beacon", TileEntityAntiMobBeacon.class, Reference.guiIDAntiMobBeacon, false, "anti_mob_beacon_side");	
		player_healing_beacon = new BlockBasicMachineBase("player_healing_beacon", TileEntityPlayerHealingBeacon.class, Reference.guiIDPlayerHealingBeacon, false, "player_healing_beacon_side");	
		
		energy_transmitter = new BlockBasicMachineBase("energy_transmitter",TileEntityEnergyTransmitter.class, Reference.guiIDEnergyTransmitter, false, "energy_transmitter_side");	
		energy_receiver = new BlockBasicMachineBase("energy_receiver", TileEntityEnergyReceiver.class, Reference.guiIDEnergyReceiver, false, "energy_receiver_side");		
		
		rf_converter = new BlockBasicMachineBase("rf_converter", TileEntityRFConverter.class, Reference.guiIDRFConverter, false, "rf_converter_side");
		
		//#-#-#-#-#-MISC-#-#-#-#-#\\
		
		charged_plant = new BlockChargedPlant();
		supply_crate = new BlockSupplyCrate();
		
		OreDictionary.registerOre("oreInfusedCrystal", infused_crystal_ore);
		OreDictionary.registerOre("oreInfusedCatalyst", infused_catalyst_ore);
		OreDictionary.registerOre("oreInfusedAzurite", infused_azurite_ore);
		OreDictionary.registerOre("oreSulfur", infused_azurite_ore);
	}	
}

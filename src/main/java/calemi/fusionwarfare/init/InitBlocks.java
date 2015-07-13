package calemi.fusionwarfare.init;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.block.BlockBasicMachineBase;
import calemi.fusionwarfare.block.BlockChargedPlant;
import calemi.fusionwarfare.block.BlockFusionTorch;
import calemi.fusionwarfare.block.BlockNetworkCable;
import calemi.fusionwarfare.block.BlockNetworkController;
import calemi.fusionwarfare.block.BlockNetworkGate;
import calemi.fusionwarfare.block.BlockOreBase;
import calemi.fusionwarfare.block.BlockReactorCasing;
import calemi.fusionwarfare.block.BlockReactorCoolingUnit;
import calemi.fusionwarfare.block.BlockReinforceable;
import calemi.fusionwarfare.block.BlockReinforcedDoor;
import calemi.fusionwarfare.block.BlockSupplyCrate;
import calemi.fusionwarfare.block.BlockTwoInputs;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.tileentity.TileEntityEnergyReceiver;
import calemi.fusionwarfare.tileentity.TileEntityEnergyTransmitter;
import calemi.fusionwarfare.tileentity.gen.TileEntityAquaGenerator;
import calemi.fusionwarfare.tileentity.gen.TileEntityGeothermalGenerator;
import calemi.fusionwarfare.tileentity.gen.TileEntitySolarGenerator;
import calemi.fusionwarfare.tileentity.gen.TileEntityWindTurbine;
import calemi.fusionwarfare.tileentity.gen.reactor.TileEntityAdvancedHydroReactorCore;
import calemi.fusionwarfare.tileentity.gen.reactor.TileEntityCapsuleCore;
import calemi.fusionwarfare.tileentity.gen.reactor.TileEntityReactorCore;
import calemi.fusionwarfare.tileentity.machine.TileEntityAntiMobBeacon;
import calemi.fusionwarfare.tileentity.machine.TileEntityEMPTower;
import calemi.fusionwarfare.tileentity.machine.TileEntityEXPFabricator;
import calemi.fusionwarfare.tileentity.machine.TileEntityEnergeticFurnace;
import calemi.fusionwarfare.tileentity.machine.TileEntityFusionMatterReinforcer;
import calemi.fusionwarfare.tileentity.machine.TileEntityMiningUnit;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import calemi.fusionwarfare.tileentity.machine.TileEntityOreEnricher;
import calemi.fusionwarfare.tileentity.machine.TileEntityPlayerHealingBeacon;
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
	public static Block reactor_casing;
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
	public static Block exp_fabricator;	
	public static Block emp_tower;
		
	public static Block fusion_matter_reinforcer;
	public static Block anti_mob_beacon;
	public static Block player_healing_beacon;
	
	public static Block energy_transmitter;
	public static Block energy_receiver;
		
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
		concrete = new BlockReinforceable("concrete", 5, true);
		reinforced_glass = new BlockReinforceable("reinforced_glass", 3, false);
				
		black_brick = new BlockReinforceable("black_brick", 5, true);
		blue_brick = new BlockReinforceable("blue_brick", 5, true);
		red_brick = new BlockReinforceable("red_brick", 5, true);
		
		reinforced_door = new BlockReinforcedDoor();
		
		//#-#-#-#-#-NETWORK-#-#-#-#-#\\
	
		network_controller_1 = new BlockNetworkController(1, FusionWarfare.guiIDNetworkController1);
		network_controller_2 = new BlockNetworkController(2, FusionWarfare.guiIDNetworkController2);
		network_controller_3 = new BlockNetworkController(3, FusionWarfare.guiIDNetworkController3);
		network_cable = new BlockNetworkCable();
		network_gate = new BlockNetworkGate();
		
		//#-#-#-#-#-GENERATORS-#-#-#-#-#\\
		
		aqua_generator = new BlockBasicMachineBase("aqua_generator", TileEntityAquaGenerator.class, FusionWarfare.guiIDAquaGenerator, false, "aqua_generator_side", false);
		solar_generator = new BlockBasicMachineBase("solar_generator", TileEntitySolarGenerator.class, FusionWarfare.guiIDSolarGenerator, false, "solar_generator_top", "mech_blank", "mech_side", false);		
		geothermal_generator = new BlockBasicMachineBase("geothermal_generator", TileEntityGeothermalGenerator.class, FusionWarfare.guiIDGeothermalGenerator, false, "geothermal_generator_side", false);
		wind_turbine = new BlockBasicMachineBase("wind_turbine", TileEntityWindTurbine.class, FusionWarfare.guiIDWindTurbine, true, "mech_top_1", "mech_blank", "wind_turbine_side", false);		
		
		//#-#-#-#-#-REACTORS-#-#-#-#-#\\
		
		reactor_core = new BlockBasicMachineBase("reactor_core", TileEntityReactorCore.class, FusionWarfare.guiIDReactorCore, false, "reactor_core_side", false);
		reactor_casing = new BlockReactorCasing();
		reactor_cooling_unit = new BlockReactorCoolingUnit();
		
		advanced_hydro_reactor_core = new BlockBasicMachineBase("advanced_hydro_reactor_core", TileEntityAdvancedHydroReactorCore.class, FusionWarfare.guiIDAdvancedHydroReactorCore, false, "advanced_hydro_reactor_core_top", "mech_blank", "advanced_hydro_reactor_core_side", false);
		capsule_core = new BlockBasicMachineBase("capsule_core", TileEntityCapsuleCore.class, 0, false, "capsule_core_side", "capsule_core_side", "capsule_core_side", false);
		
		//#-#-#-#-#-MACHINES-#-#-#-#-#\\
		
		infusion_table = new BlockTwoInputs("infusion_table", EnumRecipeType.INFUSION_TABLE, FusionWarfare.guiIDTwoInputs, "table_top", "infusion_table_side");	
		infusion_foundry = new BlockTwoInputs("infusion_foundry", EnumRecipeType.INFUSION_FOUNDRY, FusionWarfare.guiIDTwoInputs, "mech_top_1", "mech_side");	
		missile_factory = new BlockTwoInputs("missile_factory", EnumRecipeType.MISSILE_FACTORY, FusionWarfare.guiIDTwoInputs, "missile_factory_top", "missile_factory_side_1", "missile_factory_side_2", "missile_factory_side_3", "missile_factory_side_4");	
		
		energetic_furnace = new BlockBasicMachineBase("energetic_furnace", TileEntityEnergeticFurnace.class, FusionWarfare.guiIDEnergeticFurnace, true, false);
		ore_enricher = new BlockBasicMachineBase("ore_enricher", TileEntityOreEnricher.class, FusionWarfare.guiIDOreEnricher, true, false);			
		mining_unit = new BlockBasicMachineBase("mining_unit", TileEntityMiningUnit.class, FusionWarfare.guiIDMiningUnit, false, "mech_top_1", "mining_unit_bottom", "mining_unit_side", false);	
		missile_launcher = new BlockBasicMachineBase("missile_launcher", TileEntityMissileLauncher.class, FusionWarfare.guiIDMissileLauncher, 1, 0, 1, 15, 4, 15, true);
		exp_fabricator = new BlockBasicMachineBase("exp_fabricator", TileEntityEXPFabricator.class, FusionWarfare.guiIDEXPFabricator, false, "exp_fabricator_top", "mech_blank", "exp_fabricator_side", false);
		emp_tower = new BlockBasicMachineBase("emp_tower", TileEntityEMPTower.class, FusionWarfare.guiIDEMPTower, 2, 0, 2, 14, 16, 14, false);
		
		fusion_matter_reinforcer = new BlockBasicMachineBase("fusion_matter_reinforcer", TileEntityFusionMatterReinforcer.class, FusionWarfare.guiIDFusionMatterReinforcer, false, "fusion_matter_reinforcer_side", false);	
		anti_mob_beacon = new BlockBasicMachineBase("anti_mob_beacon", TileEntityAntiMobBeacon.class, FusionWarfare.guiIDAntiMobBeacon, false, "anti_mob_beacon_side", false);	
		player_healing_beacon = new BlockBasicMachineBase("player_healing_beacon", TileEntityPlayerHealingBeacon.class, FusionWarfare.guiIDPlayerHealingBeacon, false, "player_healing_beacon_side", false);	
		
		energy_transmitter = new BlockBasicMachineBase("energy_transmitter",TileEntityEnergyTransmitter.class, FusionWarfare.guiIDEnergyTransmitter, false, "energy_transmitter_side", true);	
		energy_receiver = new BlockBasicMachineBase("energy_receiver", TileEntityEnergyReceiver.class, FusionWarfare.guiIDEnergyReceiver, false, "energy_receiver_side", true);		
		
		//#-#-#-#-#-MISC-#-#-#-#-#\\
		
		charged_plant = new BlockChargedPlant();
		supply_crate = new BlockSupplyCrate();
		
		OreDictionary.registerOre("oreInfusedCrystal", infused_crystal_ore);
		OreDictionary.registerOre("oreInfusedCatalyst", infused_catalyst_ore);
		OreDictionary.registerOre("oreSulfur", infused_azurite_ore);
	}	
}

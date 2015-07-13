package calemi.fusionwarfare.init.recipe;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.util.ToolSet;
import cpw.mods.fml.common.registry.GameRegistry;

public class InitRecipes {
	
	public static void init() {

		//#-#-#-#-#-BLOCKS-#-#-#-#-#\\
		
		//#-#-#-#-#-BUILDING-#-#-#-#-#\\
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.fusion_torch, 4), new Object[] {
			"I", "S", 'I', InitItems.infused_crystal, 'S', Items.stick
		});
		
		//#-#-#-#-#-NETWORK-#-#-#-#-#\\
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.network_controller_1), new Object[] {
			"SSS", "SCS", "SSS", 'S', InitItems.steel_ingot, 'C', InitItems.infused_crystal
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.network_controller_2), new Object[] {
			"SAS", "ICI", "SSS", 'C', InitBlocks.network_controller_1, 'S', InitItems.steel_plate, 'A', InitItems.advanced_chip, 'I', InitItems.infused_steel_ingot
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.network_controller_3), new Object[] {
			"SHS", "ICI", "SSS", 'C', InitBlocks.network_controller_2, 'S', InitItems.steel_plate, 'H', InitItems.hyper_chip, 'I', InitItems.infused_steel_ingot
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.network_cable, 8), new Object[] {
			"GSG", "GCG", "GSG", 'G', Blocks.glass, 'S', InitItems.steel_ingot, 'C', InitItems.infused_crystal
		});
				
		//#-#-#-#-#-GENERATORS-#-#-#-#-#\\
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.aqua_generator), new Object[] {
			"ISI", "WSW", "ISI", 'I', InitItems.steel_ingot, 'S', Blocks.stone, 'W', Items.water_bucket
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.geothermal_generator), new Object[] {
			"SSS", "RIR", "RRR", 'S', InitItems.solar_panel, 'R', InitItems.infused_redstone, 'I', InitItems.infused_crystal
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.solar_generator), new Object[] {
			"RRR", "SIS", "SFS", 'R', InitItems.infused_redstone, 'S', InitItems.steel_plate, 'I', InitItems.infused_crystal, 'F', InitBlocks.energetic_furnace
		});	
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.wind_turbine), new Object[] {
			"RSR", "SCS", "RSR", 'R', InitItems.infused_redstone, 'S', InitItems.steel_plate, 'C', InitItems.infused_crystal
		});
		
		//#-#-#-#-#-REACTORS-#-#-#-#-#\\
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.reactor_casing, 4), new Object[] {
			"CSC", "SCS", "CSC", 'C', InitBlocks.concrete, 'S', InitItems.steel_plate
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.reactor_core), new Object[] {
			"SCS", "CGC", "SCS", 'S', InitItems.steel_plate, 'C', InitItems.advanced_chip, 'G', InitBlocks.geothermal_generator
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.reactor_cooling_unit), new Object[] {
			"IHI", "SCS", "SSS", 'I', Blocks.ice, 'H', Blocks.hopper, 'S', InitItems.steel_plate, 'C', InitItems.advanced_chip
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.advanced_hydro_reactor_core), new Object[] {
			"SSS", "HAH", "SSS", 'S', InitItems.steel_plate, 'H', InitItems.hyper_chip, 'A', InitItems.advanced_chip
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.capsule_core), new Object[] {
			"SSS", "AGA", "SSS", 'S', InitItems.steel_plate, 'A', InitItems.advanced_chip, 'G', InitBlocks.aqua_generator
		});
		
		//#-#-#-#-#-MACHINES-#-#-#-#-#\\
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.energetic_furnace), new Object[] {
			"SSS", "SFS", "SCS", 'S', InitItems.steel_ingot, 'F', Blocks.furnace, 'C', InitItems.infused_crystal
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.ore_enricher), new Object[] {
			"SSS", "FCF", "SSS", 'S', InitItems.steel_plate, 'F', Blocks.furnace, 'C', InitItems.advanced_chip
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.infusion_table), new Object[] {
			"TTT", "SCS", "SSS", 'S', InitItems.steel_ingot, 'T', Blocks.crafting_table, 'C', InitItems.infused_crystal
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.infusion_foundry), new Object[] {
			"SBS", "RRR", "SBS", 'S', InitItems.steel_plate, 'B', InitItems.basic_chip, 'R', InitItems.infused_redstone
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.mining_unit), new Object[] {
			"SSS", "PCP", "SSS", 'S', InitItems.steel_plate, 'P', Items.diamond_pickaxe, 'C', InitItems.hyper_chip
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.missile_factory), new Object[] {
			"SIS", "BIB", "SIS", 'S', InitItems.steel_plate, 'I', InitItems.infused_steel_ingot, 'B', InitItems.basic_chip
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.missile_launcher), new Object[] {
			"SIS", "IAI", "SIS", 'S', InitItems.steel_ingot, 'I', InitItems.infused_steel_ingot, 'A', InitItems.advanced_chip
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.exp_fabricator), new Object[] {
			"ISI", "HSH", "ISI", 'S', InitItems.steel_plate, 'I', InitItems.infused_steel_ingot, 'H', InitItems.hyper_chip
		});
		
		//BEACONS		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.fusion_matter_reinforcer), new Object[] {
			"ILI", "IXI", "SCS", 'I', InitItems.infused_steel_ingot, 'L', InitItems.lightning_rod, 'S', InitItems.steel_plate, 'C', InitItems.hyper_chip, 'X', Blocks.anvil
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.player_healing_beacon), new Object[] {
			"ILI", "IXI", "SCS", 'I', InitItems.infused_steel_ingot, 'L', InitItems.lightning_rod, 'S', InitItems.steel_plate, 'C', InitItems.hyper_chip, 'X', Items.golden_apple
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.anti_mob_beacon), new Object[] {
			"ILI", "IXI", "SCS", 'I', InitItems.infused_steel_ingot, 'L', InitItems.lightning_rod, 'S', InitItems.steel_plate, 'C', InitItems.hyper_chip, 'X', InitItems.infused_steel.sword
		});
		
		//WIRELESS
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.energy_transmitter), new Object[] {
			"ICI", "IEI", "ICI", 'I', InitItems.infused_steel_ingot, 'C', InitItems.advanced_chip, 'E', Items.ender_pearl
		});
				
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.energy_receiver), new Object[] {
			"III", "CEC", "III", 'I', InitItems.infused_steel_ingot, 'C', InitItems.advanced_chip, 'E', Items.ender_pearl
		});
		
		//EMP
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.emp_tower), new Object[] {
			"SLS", "SCS", "SCS", 'S', InitItems.steel_plate, 'C', InitItems.hyper_chip, 'L', InitItems.lightning_rod
		});
		
		//#-#-#-#-#-ITEMS-#-#-#-#-#\\
		
		//#-#-#-#-#-INGREDIENTS-#-#-#-#-#\\
		
		GameRegistry.addSmelting(InitItems.fusion_fish, new ItemStack(InitItems.infused_crystal, 4), 10);
		
		//#-#-#-#-#-INGOTS-#-#-#-#-#\\
		
		GameRegistry.addSmelting(InitItems.steel_mixture, new ItemStack(InitItems.steel_ingot), 1);
		
		//#-#-#-#-#-INGREDIENTS-#-#-#-#-#\\
				
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.steel_mixture), new Object[]{Items.iron_ingot, Items.coal, Items.coal, Items.coal});
					
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.solar_panel, 3), new Object[] {
			" G ", "GIG", " G ", 'I', InitItems.infused_crystal, 'G', InitBlocks.reinforced_glass
		});
		
		//Guns Parts
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.gun_core), new Object[] {
			"SSS", "SC ", 'S', InitItems.steel_plate, 'C', InitItems.basic_chip
		});
				
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.gun_handle), new Object[] {
			"SSS", "  S", "  S", 'S', InitItems.steel_plate
		});
				
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.gun_barrel), new Object[] {
			"SSS", 'S', InitItems.steel_plate
		});
		
		//MISSILES MODULES		
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.missile_module_1, 2), new Object[] {
			" S ", "SCS", "SES", 'S', InitItems.steel_plate, 'E', InitItems.evaporation_cell, 'C', InitItems.basic_chip
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.missile_module_2, 2), new Object[] {
			" S ", "SCS", "SES", 'S', InitItems.steel_plate, 'E', InitItems.evaporation_cell, 'C', InitItems.advanced_chip
		});
		
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.missile_module_3, 2), new Object[] {
			" S ", "SCS", "SES", 'S', InitItems.steel_plate, 'E', InitItems.evaporation_cell, 'C', InitItems.hyper_chip
		});
			
		//#-#-#-#-#-BATTERIES-#-#-#-#-#\\
		
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.basic_battery), new Object[] {
			"SIS", "SIS", "SIS", 'S', InitItems.steel_ingot, 'I', InitItems.infused_steel_ingot
		});
		
		//#-#-#-#-#-GUNS-#-#-#-#-#\\		
		
		//T1			
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.fusion_pistol), new Object[]{InitItems.gun_barrel, InitItems.gun_core, InitItems.gun_handle});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.fusion_auto_pistol), new Object[]{InitItems.fusion_pistol, InitItems.basic_chip});
			
		//T2			
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.fusion_shotgun), new Object[]{InitItems.gun_barrel, InitItems.gun_barrel, InitItems.gun_barrel, InitItems.gun_core, InitItems.gun_handle, InitItems.advanced_chip});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.fusion_smg), new Object[]{InitItems.fusion_auto_pistol, InitItems.advanced_chip, InitItems.gun_barrel});
		
		//T3
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.fusion_sniper_rifle), new Object[]{InitItems.gun_barrel, InitItems.gun_barrel, InitItems.gun_barrel, InitItems.gun_barrel, InitItems.gun_core, InitItems.gun_handle, InitItems.hyper_chip});
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.fusion_gatling_gun), new Object[]{InitItems.gun_barrel, InitItems.gun_barrel, InitItems.gun_barrel, InitItems.gun_barrel, InitItems.gun_barrel, InitItems.gun_barrel, InitItems.gun_core, InitItems.gun_handle, InitItems.hyper_chip});	
	
		//#-#-#-#-#-TOOLS-#-#-#-#-#\\
		
		//Tool Coloring		
		registerColoringRecipe(InitItems.infused_steel, InitItems.infused_steel_red);
			
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.fusion_matter_deconstructor), new Object[] {
			"III", " E ", " S ", 'I', InitItems.infused_steel_ingot, 'E', InitItems.evaporation_cell, 'S', InitItems.sturdy_handle
		});		
		
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.location_linker), new Object[] {
			"I", "S", 'I', InitItems.infused_steel_ingot, 'S', InitItems.steel_plate
		});	
		
		//Teams
		
		//GameRegistry.addShapelessRecipe(new ItemStack(InitItems.team_card), new Object[]{Items.paper, Items.redstone});
		//GameRegistry.addShapelessRecipe(new ItemStack(InitItems.team_card), new Object[]{InitItems.team_card, Items.paper});
	}
	
	private static void registerColoringRecipe(ToolSet input, ToolSet output) {
		
		Item[] inputs = new Item[]{input.sword, input.pickaxe, input.shovel, input.axe, input.helmet, input.chestplate, input.leggings, input.boots};		
		Item[] outputs = new Item[]{output.sword, output.pickaxe, output.shovel, output.axe, output.helmet, output.chestplate, output.leggings, output.boots};
		
		for (int i = 0; i < inputs.length; ++i) {
			
			GameRegistry.addShapelessRecipe(new ItemStack(outputs[i]), new Object[]{inputs[i]});
			GameRegistry.addShapelessRecipe(new ItemStack(inputs[i]), new Object[]{outputs[i]});
		}
	}
}

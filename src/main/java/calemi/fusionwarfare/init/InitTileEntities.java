package calemi.fusionwarfare.init;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.tileentity.TileEntityEnergyReceiver;
import calemi.fusionwarfare.tileentity.TileEntityEnergyTransmitter;
import calemi.fusionwarfare.tileentity.TileEntityReinforcedDoor;
import calemi.fusionwarfare.tileentity.TileEntitySupplyCrate;
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
import calemi.fusionwarfare.tileentity.machine.TileEntityTwoInputs;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkCable;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkGate;
import calemi.fusionwarfare.tileentity.reactor.TileEntityAdvancedHydroReactorCore;
import calemi.fusionwarfare.tileentity.reactor.TileEntityCapsuleCore;
import calemi.fusionwarfare.tileentity.reactor.TileEntityReactorCore;
import calemi.fusionwarfare.tileentity.reactor.TileEntitySteelCasing;
import cpw.mods.fml.common.registry.GameRegistry;

public class InitTileEntities {

	public static void init() {
		
		GameRegistry.registerTileEntity(TileEntityNetworkController.class, Reference.MOD_ID + ":tileEntityNetworkController");
		GameRegistry.registerTileEntity(TileEntityNetworkCable.class, Reference.MOD_ID + ":tileEntityNetworkCable");
		GameRegistry.registerTileEntity(TileEntityNetworkGate.class, Reference.MOD_ID + ":tileEntityNetworkGate");
		
		GameRegistry.registerTileEntity(TileEntityAquaGenerator.class, Reference.MOD_ID + ":tileEntityAquaGenerator");
		GameRegistry.registerTileEntity(TileEntityGeothermalGenerator.class, Reference.MOD_ID + ":tileEntityGeothermalGenerator");
		GameRegistry.registerTileEntity(TileEntitySolarGenerator.class, Reference.MOD_ID + ":tileEntitySolarGenerator");		
		GameRegistry.registerTileEntity(TileEntityWindTurbine.class, Reference.MOD_ID + ":tileEntityWindTurbine");
		
		GameRegistry.registerTileEntity(TileEntityReactorCore.class, Reference.MOD_ID + ":tileEntityReactorCore");
		GameRegistry.registerTileEntity(TileEntitySteelCasing.class, Reference.MOD_ID + ":tileEntityReactorCasing");
		
		GameRegistry.registerTileEntity(TileEntityAdvancedHydroReactorCore.class, Reference.MOD_ID + ":tileEntityAdvancedHydroReactorCore");		
		GameRegistry.registerTileEntity(TileEntityCapsuleCore.class, Reference.MOD_ID + ":tileEntityCapsuleCore");		
		
		GameRegistry.registerTileEntity(TileEntityEnergeticFurnace.class, Reference.MOD_ID + ":tileEntityEnergeticFurnace");
		GameRegistry.registerTileEntity(TileEntityOreEnricher.class, Reference.MOD_ID + ":tileEntityOreEnricher");
		GameRegistry.registerTileEntity(TileEntityTwoInputs.class, Reference.MOD_ID + ":tileEntityInfusionTable");
		GameRegistry.registerTileEntity(TileEntityMiningUnit.class, Reference.MOD_ID + ":tileEntityMiningUnit");	
		GameRegistry.registerTileEntity(TileEntityMissileLauncher.class, Reference.MOD_ID + ":tileEntityMissileLauncher");
		GameRegistry.registerTileEntity(TileEntityMissileSiloCore.class, Reference.MOD_ID + ":tileEntityMissileSiloCore");
		GameRegistry.registerTileEntity(TileEntityEXPFabricator.class, Reference.MOD_ID + ":tileEntityEXPFabricator");
		GameRegistry.registerTileEntity(TileEntityEMPTower.class, Reference.MOD_ID + ":tileEntityEMPTower");
		
		GameRegistry.registerTileEntity(TileEntityAuraTurret.class, Reference.MOD_ID + ":tileEntityAuraTurret");
		
		GameRegistry.registerTileEntity(TileEntityFusionMatterReinforcer.class, Reference.MOD_ID + ":tileEntityFusionMatterReinforcer");		
		GameRegistry.registerTileEntity(TileEntityAntiMobBeacon.class, Reference.MOD_ID + ":tileEntityAntiMobBeacon");
		GameRegistry.registerTileEntity(TileEntityPlayerHealingBeacon.class, Reference.MOD_ID + ":tileEntityPlayerHealingBeacon");
		
		GameRegistry.registerTileEntity(TileEntityEnergyTransmitter.class, Reference.MOD_ID + ":tileEntityEnergyTransmitter");
		GameRegistry.registerTileEntity(TileEntityEnergyReceiver.class, Reference.MOD_ID + ":tileEntityEnergyReceiver");
		
		GameRegistry.registerTileEntity(TileEntitySupplyCrate.class, Reference.MOD_ID + ":tileEntitySupplyCrate");		
		GameRegistry.registerTileEntity(TileEntityReinforcedDoor.class, Reference.MOD_ID + ":tileEntityReinforcedDoor");
	}
}
package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.inventory.ContainerOverclockedEnergyTank;
import calemi.fusionwarfare.inventory.ContainerMiningUnit;
import calemi.fusionwarfare.inventory.ContainerMissileLauncher;
import calemi.fusionwarfare.inventory.ContainerMissileSiloCore;
import calemi.fusionwarfare.inventory.ContainerNetworkController;
import calemi.fusionwarfare.inventory.ContainerOneInput;
import calemi.fusionwarfare.inventory.ContainerReactorCore;
import calemi.fusionwarfare.inventory.ContainerSupplyCrate;
import calemi.fusionwarfare.inventory.ContainerTwoInputs;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.tileentity.TileEntityEnergyReceiver;
import calemi.fusionwarfare.tileentity.TileEntityEnergyTransmitter;
import calemi.fusionwarfare.tileentity.TileEntitySupplyCrate;
import calemi.fusionwarfare.tileentity.gen.TileEntityAquaGenerator;
import calemi.fusionwarfare.tileentity.gen.TileEntityGeothermalGenerator;
import calemi.fusionwarfare.tileentity.gen.TileEntitySolarGenerator;
import calemi.fusionwarfare.tileentity.gen.TileEntityWindTurbine;
import calemi.fusionwarfare.tileentity.gen.reactor.TileEntityAdvancedHydroReactorCore;
import calemi.fusionwarfare.tileentity.gen.reactor.TileEntityReactorCore;
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
import calemi.fusionwarfare.tileentity.machine.TileEntityTwoInputs;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileentity = world.getTileEntity(x, y, z);
				
		if (ID == Reference.guiIDNetworkController) {
			return new ContainerNetworkController(player, (TileEntityNetworkController) tileentity);
		}

		if (ID == Reference.guiIDEnergeticFurnace) {
			return new ContainerOneInput(player, (TileEntityEnergeticFurnace) tileentity);
		}

		if (ID == Reference.guiIDOreEnricher) {
			return new ContainerOneInput(player, (TileEntityOreEnricher) tileentity);
		}

		if (ID == Reference.guiIDTwoInputs) {
			return new ContainerTwoInputs(player, (TileEntityTwoInputs) tileentity);
		}

		if (ID == Reference.guiIDMiningUnit) {
			return new ContainerMiningUnit(player, (TileEntityMiningUnit) tileentity);
		}

		if (ID == Reference.guiIDGeothermalGenerator) {
			return new ContainerEnergyTank(player, (TileEntityGeothermalGenerator) tileentity);
		}

		if (ID == Reference.guiIDSolarGenerator) {
			return new ContainerEnergyTank(player, (TileEntitySolarGenerator) tileentity);
		}

		if (ID == Reference.guiIDAquaGenerator) {
			return new ContainerEnergyTank(player, (TileEntityAquaGenerator) tileentity);
		}

		if (ID == Reference.guiIDWindTurbine) {
			return new ContainerEnergyTank(player, (TileEntityWindTurbine) tileentity);
		}

		if (ID == Reference.guiIDReactorCore) {
			return new ContainerReactorCore(player, (TileEntityReactorCore) tileentity);
		}

		if (ID == Reference.guiIDAdvancedHydroReactorCore) {
			return new ContainerReactorCore(player, (TileEntityAdvancedHydroReactorCore) tileentity);
		}

		if (ID == Reference.guiIDMissileLauncher) {
			return new ContainerMissileLauncher(player, (TileEntityMissileLauncher) tileentity);
		}
		
		if (ID == Reference.guiIDMissileSiloCore) {
			return new ContainerMissileSiloCore(player, (TileEntityMissileSiloCore) tileentity);
		}

		if (ID == Reference.guiIDEXPFabricator) {
			return new ContainerEnergyTank(player, (TileEntityEXPFabricator) tileentity);
		}
		
		if (ID == Reference.guiIDEMPTower) {
			return new ContainerEnergyTank(player, (TileEntityEMPTower) tileentity);
		}
		
		if (ID == Reference.guiIDAuraTurret) {
			return new ContainerEnergyTank(player, (TileEntityAuraTurret) tileentity);
		}

		if (ID == Reference.guiIDFusionMatterReinforcer) {
			return new ContainerOverclockedEnergyTank(player, (TileEntityFusionMatterReinforcer) tileentity, 15);
		}

		if (ID == Reference.guiIDAntiMobBeacon) {
			return new ContainerEnergyTank(player, (TileEntityAntiMobBeacon) tileentity);
		}

		if (ID == Reference.guiIDPlayerHealingBeacon) {
			return new ContainerEnergyTank(player, (TileEntityPlayerHealingBeacon) tileentity);
		}

		if (ID == Reference.guiIDEnergyTransmitter) {
			return new ContainerOverclockedEnergyTank(player, (TileEntityEnergyTransmitter) tileentity, 15);
		}

		if (ID == Reference.guiIDEnergyReceiver) {
			return new ContainerEnergyTank(player, (TileEntityEnergyReceiver) tileentity);
		}
		
		if (ID == Reference.guiIDRFConverter) {
			return new ContainerEnergyTank(player, (TileEntityRFConverter) tileentity);
		}

		if (ID == Reference.guiIDSupplyCrate) {
			return new ContainerSupplyCrate(player, (TileEntitySupplyCrate) tileentity);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileentity = world.getTileEntity(x, y, z);

		if (ID == Reference.guiIDNetworkController) {
			return new GuiNetworkController(player, (TileEntityNetworkController) tileentity);
		}

		if (ID == Reference.guiIDEnergeticFurnace) {
			return new GuiOneInput(player, (TileEntityEnergeticFurnace) tileentity, "Energetic Furnace");
		}

		if (ID == Reference.guiIDOreEnricher) {
			return new GuiOneInput(player, (TileEntityOreEnricher) tileentity, "Ore Enricher");
		}

		if (ID == Reference.guiIDTwoInputs) {
			return new GuiTwoInputs(player, (TileEntityTwoInputs) tileentity, ((TileEntityTwoInputs)tileentity).recipeType);
		}

		if (ID == Reference.guiIDMiningUnit) {
			return new GuiMiningUnit(player, (TileEntityMiningUnit) tileentity);
		}
		
		if (ID == Reference.guiIDGeothermalGenerator) {
			return new GuiEnergyTank(player, (TileEntityGeothermalGenerator) tileentity, "Thermal Generator", false);
		}

		if (ID == Reference.guiIDSolarGenerator) {
			return new GuiEnergyTank(player, (TileEntitySolarGenerator) tileentity, "Solar Generator", false);
		}

		if (ID == Reference.guiIDAquaGenerator) {
			return new GuiEnergyTank(player, (TileEntityAquaGenerator) tileentity, "Aqua Generator", false);
		}

		if (ID == Reference.guiIDWindTurbine) {
			return new GuiEnergyTank(player, (TileEntityWindTurbine) tileentity, "Wind Turbine", false);
		}

		if (ID == Reference.guiIDReactorCore) {
			return new GuiReactorCore(player, (TileEntityReactorCore) tileentity, "Reactor");
		}

		if (ID == Reference.guiIDAdvancedHydroReactorCore) {
			return new GuiReactorCore(player, (TileEntityAdvancedHydroReactorCore) tileentity, "Advanced Hydro Reactor");
		}

		if (ID == Reference.guiIDMissileLauncher) {
			return new GuiMissileLauncher(player, (TileEntityMissileLauncher) tileentity);
		}
		
		if (ID == Reference.guiIDMissileSiloCore) {
			return new GuiMissileSiloCore(player, (TileEntityMissileSiloCore) tileentity);
		}

		if (ID == Reference.guiIDEXPFabricator) {
			return new GuiEXPFabricator(player, (TileEntityEXPFabricator) tileentity);
		}

		if (ID == Reference.guiIDEMPTower) {
			return new GuiEnergyTank(player, (TileEntityEMPTower) tileentity, "EMP Tower", true);
		}
		
		if (ID == Reference.guiIDAuraTurret) {
			return new GuiEnergyTank(player, (TileEntityAuraTurret) tileentity, "Aura Turret", true);
		}
		
		if (ID == Reference.guiIDFusionMatterReinforcer) {
			return new GuiOverclockedEnergyTank(player, (TileEntityFusionMatterReinforcer) tileentity, "Fusion Matter Reinforcer", 10, true);
		}

		if (ID == Reference.guiIDAntiMobBeacon) {
			return new GuiEnergyTank(player, (TileEntityAntiMobBeacon) tileentity, "Anti-Mob Beacon", true);
		}

		if (ID == Reference.guiIDPlayerHealingBeacon) {
			return new GuiEnergyTank(player, (TileEntityPlayerHealingBeacon) tileentity, "Player Healing Beacon", true);
		}

		if (ID == Reference.guiIDEnergyTransmitter) {
			return new GuiOverclockedEnergyTank(player, (TileEntityEnergyTransmitter) tileentity, "Energy Transmitter", 5, false);
		}

		if (ID == Reference.guiIDEnergyReceiver) {
			return new GuiEnergyTank(player, (TileEntityEnergyReceiver) tileentity, "Energy Receiver", false);
		}
		
		if (ID == Reference.guiIDRFConverter) {
			return new GuiRFConverter(player, (TileEntityRFConverter) tileentity);
		}
		
		if (ID == Reference.guiIDSupplyCrate) {
			return new GuiSupplyCrate(player, (TileEntitySupplyCrate) tileentity);
		}

		return null;
	}
}

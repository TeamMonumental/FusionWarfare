package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.gui.recipe.GuiTwoInputsRecipe;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.inventory.ContainerEnergyTransmitter;
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
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileentity = world.getTileEntity(x, y, z);

		if (ID == FusionWarfare.guiIDNetworkController1) {
			return new ContainerNetworkController(player, (TileEntityNetworkController) tileentity, 1);
		}

		if (ID == FusionWarfare.guiIDNetworkController2) {
			return new ContainerNetworkController(player, (TileEntityNetworkController) tileentity, 2);
		}

		if (ID == FusionWarfare.guiIDNetworkController3) {
			return new ContainerNetworkController(player, (TileEntityNetworkController) tileentity, 3);
		}

		if (ID == FusionWarfare.guiIDEnergeticFurnace) {
			return new ContainerOneInput(player, (TileEntityEnergeticFurnace) tileentity);
		}

		if (ID == FusionWarfare.guiIDOreEnricher) {
			return new ContainerOneInput(player, (TileEntityOreEnricher) tileentity);
		}

		if (ID == FusionWarfare.guiIDTwoInputs) {
			return new ContainerTwoInputs(player, (TileEntityTwoInputs) tileentity);
		}

		if (ID == FusionWarfare.guiIDMiningUnit) {
			return new ContainerMiningUnit(player, (TileEntityMiningUnit) tileentity);
		}

		if (ID == FusionWarfare.guiIDGeothermalGenerator) {
			return new ContainerEnergyTank(player, (TileEntityGeothermalGenerator) tileentity);
		}

		if (ID == FusionWarfare.guiIDSolarGenerator) {
			return new ContainerEnergyTank(player, (TileEntitySolarGenerator) tileentity);
		}

		if (ID == FusionWarfare.guiIDAquaGenerator) {
			return new ContainerEnergyTank(player, (TileEntityAquaGenerator) tileentity);
		}

		if (ID == FusionWarfare.guiIDWindTurbine) {
			return new ContainerEnergyTank(player, (TileEntityWindTurbine) tileentity);
		}

		if (ID == FusionWarfare.guiIDReactorCore) {
			return new ContainerReactorCore(player, (TileEntityReactorCore) tileentity);
		}

		if (ID == FusionWarfare.guiIDAdvancedHydroReactorCore) {
			return new ContainerReactorCore(player, (TileEntityAdvancedHydroReactorCore) tileentity);
		}

		if (ID == FusionWarfare.guiIDMissileLauncher) {
			return new ContainerMissileLauncher(player, (TileEntityMissileLauncher) tileentity);
		}
		
		if (ID == FusionWarfare.guiIDMissileSiloCore) {
			return new ContainerMissileSiloCore(player, (TileEntityMissileSiloCore) tileentity);
		}

		if (ID == FusionWarfare.guiIDEXPFabricator) {
			return new ContainerEnergyTank(player, (TileEntityEXPFabricator) tileentity);
		}
		
		if (ID == FusionWarfare.guiIDEMPTower) {
			return new ContainerEnergyTank(player, (TileEntityEMPTower) tileentity);
		}

		if (ID == FusionWarfare.guiIDFusionMatterReinforcer) {
			return new ContainerEnergyTank(player, (TileEntityFusionMatterReinforcer) tileentity);
		}

		if (ID == FusionWarfare.guiIDAntiMobBeacon) {
			return new ContainerEnergyTank(player, (TileEntityAntiMobBeacon) tileentity);
		}

		if (ID == FusionWarfare.guiIDPlayerHealingBeacon) {
			return new ContainerEnergyTank(player, (TileEntityPlayerHealingBeacon) tileentity);
		}

		if (ID == FusionWarfare.guiIDEnergyTransmitter) {
			return new ContainerEnergyTransmitter(player, (TileEntityEnergyTransmitter) tileentity);
		}

		if (ID == FusionWarfare.guiIDEnergyReceiver) {
			return new ContainerEnergyTank(player, (TileEntityEnergyReceiver) tileentity);
		}

		if (ID == FusionWarfare.guiIDSupplyCrate) {
			return new ContainerSupplyCrate(player, (TileEntitySupplyCrate) tileentity);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity tileentity = world.getTileEntity(x, y, z);

		if (ID == FusionWarfare.guiIDNetworkController1) {
			return new GuiNetworkController(player, (TileEntityNetworkController) tileentity, 1, "Basic");
		}

		if (ID == FusionWarfare.guiIDNetworkController2) {
			return new GuiNetworkController(player, (TileEntityNetworkController) tileentity, 2, "Advanced");
		}

		if (ID == FusionWarfare.guiIDNetworkController3) {
			return new GuiNetworkController(player, (TileEntityNetworkController) tileentity, 3, "Hyper");
		}

		if (ID == FusionWarfare.guiIDEnergeticFurnace) {
			return new GuiOneInput(player, (TileEntityEnergeticFurnace) tileentity, "Energetic Furnace");
		}

		if (ID == FusionWarfare.guiIDOreEnricher) {
			return new GuiOneInput(player, (TileEntityOreEnricher) tileentity, "Ore Enricher");
		}

		if (ID == FusionWarfare.guiIDTwoInputs) {
			return new GuiTwoInputs(player, (TileEntityTwoInputs) tileentity, ((TileEntityTwoInputs)tileentity).recipeType);
		}

		if (ID == FusionWarfare.guiIDMiningUnit) {
			return new GuiMiningUnit(player, (TileEntityMiningUnit) tileentity);
		}
		
		if (ID == FusionWarfare.guiIDGeothermalGenerator) {
			return new GuiEnergyTank(player, (TileEntityGeothermalGenerator) tileentity, "Thermal Generator", false);
		}

		if (ID == FusionWarfare.guiIDSolarGenerator) {
			return new GuiEnergyTank(player, (TileEntitySolarGenerator) tileentity, "Solar Generator", false);
		}

		if (ID == FusionWarfare.guiIDAquaGenerator) {
			return new GuiEnergyTank(player, (TileEntityAquaGenerator) tileentity, "Aqua Generator", false);
		}

		if (ID == FusionWarfare.guiIDWindTurbine) {
			return new GuiEnergyTank(player, (TileEntityWindTurbine) tileentity, "Wind Turbine", false);
		}

		if (ID == FusionWarfare.guiIDReactorCore) {
			return new GuiReactorCore(player, (TileEntityReactorCore) tileentity, "Reactor");
		}

		if (ID == FusionWarfare.guiIDAdvancedHydroReactorCore) {
			return new GuiReactorCore(player, (TileEntityAdvancedHydroReactorCore) tileentity, "Advanced Hydro Reactor");
		}

		if (ID == FusionWarfare.guiIDMissileLauncher) {
			return new GuiMissileLauncher(player, (TileEntityMissileLauncher) tileentity);
		}
		
		if (ID == FusionWarfare.guiIDMissileSiloCore) {
			return new GuiMissileSiloCore(player, (TileEntityMissileSiloCore) tileentity);
		}

		if (ID == FusionWarfare.guiIDEXPFabricator) {
			return new GuiEXPFabricator(player, (TileEntityEXPFabricator) tileentity);
		}

		if (ID == FusionWarfare.guiIDEMPTower) {
			return new GuiEnergyTank(player, (TileEntityEMPTower) tileentity, "EMP Tower", true);
		}
		
		if (ID == FusionWarfare.guiIDFusionMatterReinforcer) {
			return new GuiEnergyTank(player, (TileEntityFusionMatterReinforcer) tileentity, "Fusion Matter Reinforcer", true);
		}

		if (ID == FusionWarfare.guiIDAntiMobBeacon) {
			return new GuiEnergyTank(player, (TileEntityAntiMobBeacon) tileentity, "Anti-Mob Beacon", true);
		}

		if (ID == FusionWarfare.guiIDPlayerHealingBeacon) {
			return new GuiEnergyTank(player, (TileEntityPlayerHealingBeacon) tileentity, "Player Healing Beacon", true);
		}

		if (ID == FusionWarfare.guiIDEnergyTransmitter) {
			return new GuiEnergyTransmitter(player, (TileEntityEnergyTransmitter) tileentity);
		}

		if (ID == FusionWarfare.guiIDEnergyReceiver) {
			return new GuiEnergyTank(player, (TileEntityEnergyReceiver) tileentity, "Energy Receiver", false);
		}
		
		if (ID == FusionWarfare.guiIDSupplyCrate) {
			return new GuiSupplyCrate(player, (TileEntitySupplyCrate) tileentity);
		}

		return null;
	}
}

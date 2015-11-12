package calemi.fusionwarfare.nei;

import javax.swing.JOptionPane;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.block.BlockChargedPlant;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.minecraft.item.ItemStack;

public class NEIFusionWarfareConfig implements IConfigureNEI {

	@Override
	public void loadConfig() {
		
		API.hideItem(new ItemStack(InitBlocks.reinforced_door));
		
		for (int i = 0; i < BlockChargedPlant.icons.length; i++) {
			API.hideItem(new ItemStack(InitBlocks.charged_plant, 1, i));
		}
		
		API.registerRecipeHandler(new InfusionTableRecipeHandler());
		API.registerUsageHandler(new InfusionTableRecipeHandler());
		
		API.registerRecipeHandler(new InfusionFoundryRecipeHandler());
		API.registerUsageHandler(new InfusionFoundryRecipeHandler());
		
		API.registerRecipeHandler(new MissileFactoryRecipeHandler());
		API.registerUsageHandler(new MissileFactoryRecipeHandler());
		
		API.registerRecipeHandler(new EnergeticFurnaceRecipeHandler());
		API.registerUsageHandler(new EnergeticFurnaceRecipeHandler());
		
		API.registerRecipeHandler(new OreEnricherRecipeHandler());
		API.registerUsageHandler(new OreEnricherRecipeHandler());
	}
	
	@Override
	public String getName() {
		return Reference.MOD_NAME;
	}

	@Override
	public String getVersion() {
		return Reference.VERSION;
	}
}
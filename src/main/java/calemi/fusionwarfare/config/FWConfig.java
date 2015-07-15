package calemi.fusionwarfare.config;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class FWConfig {
	
	public static Configuration config = FusionWarfare.config;
	
	public static boolean disableFallingCrates;
	public static boolean disableRocketLauncherRecipe;
	public static boolean disableTooltips;
	
	public static boolean disableInfusedCrystalOre;
	public static boolean disableInfusedCatalystOre;
	public static boolean disableInfusedAzuriteOre;
	
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
				
		if (event.modID.equals(Reference.MOD_ID)) {
			syncConfig();
		}
	}
	
	public static void syncConfig() {	
		FMLCommonHandler.instance().bus().register(FusionWarfare.instance);
		
		final String GENERAL_OPTIONS = config.CATEGORY_GENERAL + config.CATEGORY_SPLITTER + "General Options";
		config.setCategoryLanguageKey(GENERAL_OPTIONS, "category.general_options.name");
		config.addCustomCategoryComment(GENERAL_OPTIONS, "General options for Fusion Warfare.");
		
		disableFallingCrates = config.getBoolean("Disable Falling Supply Crates", GENERAL_OPTIONS, false, "Disables Supply Crates from falling");
		disableRocketLauncherRecipe = config.getBoolean("Disable Rocket Launcher Recipe", GENERAL_OPTIONS, false, "Disables Rocket Launcher Recipe. Recommended false if Supply Crates are enabled.");
		disableTooltips = config.getBoolean("Disable Custom Tooltips", GENERAL_OPTIONS, false, "Disables custom tooltips on armor and tools.");
		
		final String ORES = config.CATEGORY_GENERAL + config.CATEGORY_SPLITTER + "Ores";
		config.setCategoryLanguageKey(ORES, "category.ores.name");
		config.addCustomCategoryComment(ORES, "Disable ores from spawning.");
		
		disableInfusedCrystalOre = config.getBoolean("Disable Infused Crystal Ore", ORES, false, "Disables this ore from spawning.");
		disableInfusedCatalystOre = config.getBoolean("Disable Infused Catalyst Ore", ORES, false, "Disables this ore from spawning.");
		disableInfusedAzuriteOre = config.getBoolean("Disable Infused Azurite Ore", ORES, false, "Disables this ore from spawning.");
		
		if (config.hasChanged()) {
			config.save();
		}
	}
}

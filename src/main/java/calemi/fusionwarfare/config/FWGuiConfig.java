package calemi.fusionwarfare.config;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class FWGuiConfig extends GuiConfig {

	public FWGuiConfig(GuiScreen screen) {
		super(screen, new ConfigElement(FusionWarfare.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(FusionWarfare.config.toString()));
	}	
}

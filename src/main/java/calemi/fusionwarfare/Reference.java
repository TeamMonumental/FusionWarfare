package calemi.fusionwarfare;

public class Reference {
	
	public static final String MOD_ID = "FusionWarfare";
	public static final String MOD_NAME = "Fusion Warfare";
	public static final String VERSION = "1.7.10-1.1.0";
	public static final String CLIENT_PROXY_CLASS = "calemi.fusionwarfare.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "calemi.fusionwarfare.proxy.ServerProxy";
	public static final String GUI_FACTORY = "calemi.fusionwarfare.gui.GuiFactory";
	
	public static final String KEY_CATAGORY = "keys.fusionwarfare.catagory";
	public static final String KEY_RECIPE_NAME = "keys.fusionwarfare.recipe";
	public static final String KEY_TEAM_GUI = "keys.fusionwarfare.teamgui";	
	
	//-------------------------------------------------------------------------\\
	
	public static int armorIDScuba;
	public static int armorIDSteel;
	public static int armorIDInfusedSteel;
	public static int armorIDInfusedSteelRed;
	
	static int nextGuiID = 1;
	
	//#-#-#-#-#-NETWORK-#-#-#-#-#\\
	
	public static int guiIDNetworkController = nextGuiID++;
	
	//#-#-#-#-#-GENERATORS-#-#-#-#-#\\
	
	public static int guiIDAquaGenerator = nextGuiID++;
	public static int guiIDGeothermalGenerator = nextGuiID++;
	public static int guiIDSolarGenerator = nextGuiID++;
	public static int guiIDWindTurbine = nextGuiID++;
	
	//#-#-#-#-#-REACTORS-#-#-#-#-#\\
	
	public static int guiIDReactorCore = nextGuiID++;
	public static int guiIDAdvancedHydroReactorCore = nextGuiID++;
	
	//#-#-#-#-#-MACHINES-#-#-#-#-#\\
	
	public static int guiIDTwoInputs = nextGuiID++;
	
	public static int guiIDEnergeticFurnace = nextGuiID++;
	public static int guiIDOreEnricher = nextGuiID++;	
	public static int guiIDMiningUnit = nextGuiID++;
	public static int guiIDMissileLauncher = nextGuiID++;
	public static int guiIDMissileSiloCore = nextGuiID++;
	public static int guiIDEXPFabricator = nextGuiID++;
	public static int guiIDEMPTower = nextGuiID++;
		
	public static int guiIDAuraTurret = nextGuiID++;
	
	public static int guiIDFusionMatterReinforcer = nextGuiID++;
	public static int guiIDAntiMobBeacon = nextGuiID++;
	public static int guiIDPlayerHealingBeacon = nextGuiID++;
	
	public static int guiIDEnergyTransmitter = nextGuiID++;
	public static int guiIDEnergyReceiver = nextGuiID++;		
	
	public static int guiIDSupplyCrate = nextGuiID++;
	
}

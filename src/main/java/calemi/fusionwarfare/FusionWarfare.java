package calemi.fusionwarfare;

import calemi.fusionwarfare.api.SecurityEvent;
import calemi.fusionwarfare.config.FWConfig;
import calemi.fusionwarfare.event.CraftingEvent;
import calemi.fusionwarfare.event.EntitySpawnEvent;
import calemi.fusionwarfare.event.OnPlayerJoinEvent;
import calemi.fusionwarfare.event.SupplyCrateEvent;
import calemi.fusionwarfare.gui.GuiHandler;
import calemi.fusionwarfare.init.InitArmorMaterials;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitCrateLoot;
import calemi.fusionwarfare.init.InitEntities;
import calemi.fusionwarfare.init.InitHooks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.init.InitTileEntities;
import calemi.fusionwarfare.init.InitToolMaterials;
import calemi.fusionwarfare.init.recipe.InitInfusionFoundryRecipes;
import calemi.fusionwarfare.init.recipe.InitInfusionTableRecipes;
import calemi.fusionwarfare.init.recipe.InitMissileFactoryRecipes;
import calemi.fusionwarfare.init.recipe.InitRecipes;
import calemi.fusionwarfare.packet.ClientPacketHandler;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.proxy.IProxy;
import calemi.fusionwarfare.world.WorldGenOre;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY, dependencies = "after:NotEnoughItems")
public class FusionWarfare {
	
	@Instance(Reference.MOD_ID)
	public static FusionWarfare instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;

	public static WorldGenOre worldGenOre = new WorldGenOre();
	
	public static SimpleNetworkWrapper network;
	
	public static Configuration config;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {	
		
		MinecraftForge.EVENT_BUS.register(new EntitySpawnEvent());
		MinecraftForge.EVENT_BUS.register(new OnPlayerJoinEvent());
		
		MinecraftForge.EVENT_BUS.register(new SecurityEvent());	
		
		config = new Configuration(event.getSuggestedConfigurationFile());
		FWConfig.syncConfig();
		
		FMLCommonHandler.instance().bus().register(new CraftingEvent());	
		FMLCommonHandler.instance().bus().register(new SupplyCrateEvent());	
		FMLCommonHandler.instance().bus().register(new FWConfig());
		FMLCommonHandler.instance().bus().register(new OnPlayerJoinEvent());	
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("fusionwarfare");
		network.registerMessage(ServerPacketHandler.Handler.class, ServerPacketHandler.class, 0, Side.SERVER);
		network.registerMessage(ClientPacketHandler.Handler.class, ClientPacketHandler.class, 1, Side.CLIENT);
		
		InitToolMaterials.init();
		InitArmorMaterials.init();
		
		InitItems.init();
		InitBlocks.init();
		InitRecipes.init();
		
		InitInfusionTableRecipes.init();
		InitInfusionFoundryRecipes.init();
		InitMissileFactoryRecipes.init();
		
		InitHooks.init();		
		InitCrateLoot.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {	
		
		proxy.registerRenders();
		InitEntities.init();		
		InitTileEntities.init();
		GameRegistry.registerWorldGenerator(worldGenOre, 1);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}
}
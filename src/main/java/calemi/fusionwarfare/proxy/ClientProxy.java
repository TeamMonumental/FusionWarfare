package calemi.fusionwarfare.proxy;

import java.util.HashMap;
import java.util.Map;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.entity.EntityBlock;
import calemi.fusionwarfare.entity.EntityDesignatorOrb;
import calemi.fusionwarfare.entity.EntityFallingSupplyCrate;
import calemi.fusionwarfare.entity.EntityFusionBullet;
import calemi.fusionwarfare.entity.EntityGrenade;
import calemi.fusionwarfare.entity.EntityMissile;
import calemi.fusionwarfare.entity.EntityRocket;
import calemi.fusionwarfare.event.HUDEvent;
import calemi.fusionwarfare.event.GunRenderEvent;
import calemi.fusionwarfare.event.TooltipEvent;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.tool.ItemScubaGear;
import calemi.fusionwarfare.key.KeyBindings;
import calemi.fusionwarfare.key.KeyInputHandler;
import calemi.fusionwarfare.model.ModelScubaGear;
import calemi.fusionwarfare.renderer.RenderBeacon;
import calemi.fusionwarfare.renderer.RenderBlock;
import calemi.fusionwarfare.renderer.RenderDesignatorOrb;
import calemi.fusionwarfare.renderer.RenderEMPTower;
import calemi.fusionwarfare.renderer.RenderFallingSupplyCrate;
import calemi.fusionwarfare.renderer.RenderFloatingItem;
import calemi.fusionwarfare.renderer.RenderFusionBullet;
import calemi.fusionwarfare.renderer.RenderGrenade;
import calemi.fusionwarfare.renderer.RenderMissile;
import calemi.fusionwarfare.renderer.RenderMissileLauncher;
import calemi.fusionwarfare.renderer.RenderMissileSilo;
import calemi.fusionwarfare.renderer.RenderNetworkBeacon;
import calemi.fusionwarfare.renderer.RenderNetworkCable;
import calemi.fusionwarfare.renderer.RenderRocket;
import calemi.fusionwarfare.renderer.RenderTurbine;
import calemi.fusionwarfare.renderer.RenderAuraBase;
import calemi.fusionwarfare.renderer.item.ItemRenderEMPTower;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionAutoPistol;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionGatlingGun;
import calemi.fusionwarfare.renderer.item.ItemRenderGrenade;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionMatterDeconstructor;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionPistol;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionSMG;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionShotgun;
import calemi.fusionwarfare.renderer.item.ItemRenderFusionSniperRifle;
import calemi.fusionwarfare.renderer.item.ItemRenderMissileLauncher;
import calemi.fusionwarfare.renderer.item.ItemRenderNetworkCable;
import calemi.fusionwarfare.renderer.item.ItemRenderRocket;
import calemi.fusionwarfare.renderer.item.ItemRenderRocketLauncher;
import calemi.fusionwarfare.renderer.item.ItemRenderNetworkBeacon;
import calemi.fusionwarfare.renderer.item.ItemRenderAuraBase;
import calemi.fusionwarfare.tileentity.gen.TileEntityWindTurbine;
import calemi.fusionwarfare.tileentity.machine.TileEntityAuraMobTurret;
import calemi.fusionwarfare.tileentity.machine.TileEntityAuraPlayerTurret;
import calemi.fusionwarfare.tileentity.machine.TileEntityEMPTower;
import calemi.fusionwarfare.tileentity.machine.TileEntityEXPFabricator;
import calemi.fusionwarfare.tileentity.machine.TileEntityAuraMatterReinforcer;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileSiloCore;
import calemi.fusionwarfare.tileentity.machine.TileEntityAuraPlayerHealer;
import calemi.fusionwarfare.tileentity.machine.TileEntityTwoInputs;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkBeacon;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkCable;
import calemi.fusionwarfare.util.EnumColorUtil;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {

	public static final Map<Item, ModelBiped> armorModels = new HashMap<Item, ModelBiped>();
	
	@Override
	public void registerRenders() {
		
		ClientRegistry.registerKeyBinding(KeyBindings.teamGuiButton);
		ClientRegistry.registerKeyBinding(KeyBindings.reloadButton);
		
		MinecraftForge.EVENT_BUS.register(new TooltipEvent());
		MinecraftForge.EVENT_BUS.register(new GunRenderEvent());
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		MinecraftForge.EVENT_BUS.register(new HUDEvent());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityFusionBullet.class, new RenderFusionBullet());
		RenderingRegistry.registerEntityRenderingHandler(EntityDesignatorOrb.class, new RenderDesignatorOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, new RenderGrenade());
		RenderingRegistry.registerEntityRenderingHandler(EntityRocket.class, new RenderRocket());
		RenderingRegistry.registerEntityRenderingHandler(EntityMissile.class, new RenderMissile());	
		RenderingRegistry.registerEntityRenderingHandler(EntityBlock.class, new RenderBlock());	
		RenderingRegistry.registerEntityRenderingHandler(EntityFallingSupplyCrate.class, new RenderFallingSupplyCrate());
		
		MinecraftForgeClient.registerItemRenderer(InitItems.rocket, new ItemRenderRocket());
		
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_pistol, new ItemRenderFusionPistol());
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_auto_pistol, new ItemRenderFusionAutoPistol());
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_shotgun, new ItemRenderFusionShotgun());
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_smg, new ItemRenderFusionSMG());
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_sniper_rifle, new ItemRenderFusionSniperRifle());		
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_gatling_gun, new ItemRenderFusionGatlingGun());
		
		MinecraftForgeClient.registerItemRenderer(InitItems.rocket_launcher, new ItemRenderRocketLauncher());
		
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_grenade, new ItemRenderGrenade("fusion"));
		MinecraftForgeClient.registerItemRenderer(InitItems.flash_grenade, new ItemRenderGrenade("flash"));
		MinecraftForgeClient.registerItemRenderer(InitItems.chemical_grenade, new ItemRenderGrenade("chemical"));
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.network_cable), new ItemRenderNetworkCable(new RenderNetworkCable(), new TileEntityNetworkCable()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.network_beacon), new ItemRenderNetworkBeacon());
		
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.missile_launcher), new ItemRenderMissileLauncher());
		MinecraftForgeClient.registerItemRenderer(InitItems.fusion_matter_deconstructor, new ItemRenderFusionMatterDeconstructor());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.emp_tower), new ItemRenderEMPTower());
		
		//Auras
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.aura_player_turret), new ItemRenderAuraBase(0, 148, 255));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.aura_matter_reinforcer), new ItemRenderAuraBase(0, 255, 0));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.aura_mob_turret), new ItemRenderAuraBase(255, 0, 0));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(InitBlocks.aura_player_healer), new ItemRenderAuraBase(255, 0, 255));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNetworkCable.class, new RenderNetworkCable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNetworkBeacon.class, new RenderNetworkBeacon());
			
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAuraPlayerTurret.class, new RenderAuraBase(0, 148, 255));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAuraMatterReinforcer.class, new RenderAuraBase(0, 255, 0));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAuraMobTurret.class, new RenderAuraBase(255, 0, 0));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAuraPlayerHealer.class, new RenderAuraBase(255, 0, 255));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMissileLauncher.class, new RenderMissileLauncher());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindTurbine.class, new RenderTurbine());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEXPFabricator.class, new RenderFloatingItem(new ItemStack(Items.experience_bottle), 0.5F, 1.1F, 0.5F, 1F));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTwoInputs.class, new RenderFloatingItem(new ItemStack(InitItems.velocity_missile_T1), 0.5F, 1.1F, 0.5F, 0.7F));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEMPTower.class, new RenderEMPTower());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMissileSiloCore.class, new RenderMissileSilo());
			
		ModelScubaGear scuba_armor = new ModelScubaGear(0.5F);
		ModelScubaGear scuba_legs = new ModelScubaGear(0.25F);
		
		armorModels.put(InitItems.scuba_helmet, scuba_armor);
		armorModels.put(InitItems.scuba_chestplate, scuba_armor);
		armorModels.put(InitItems.scuba_leggings, scuba_legs);
		armorModels.put(InitItems.scuba_boots, scuba_armor);
		
		Reference.armorIDScuba = addArmor("scuba");
		Reference.armorIDSteel = addArmor("steel");
		Reference.armorIDInfusedSteel = addArmor("infused_steel");
		Reference.armorIDInfusedSteelRed = addArmor("infused_steel_red");
	}
	
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);				
	}
}
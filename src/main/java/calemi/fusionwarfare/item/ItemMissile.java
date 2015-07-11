package calemi.fusionwarfare.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.MinecraftForgeClient;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.renderer.item.ItemRenderVelocityMissile;
import calemi.fusionwarfare.util.missile.MissileType;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMissile extends ItemBase {

	public MissileType missileType;

	public ItemMissile(MissileType missileType) {
		super(missileType.getItemName(), false, false);
		setCreativeTab(InitCreativeTabs.creativeTabMissiles);
		setMaxStackSize(4);
		this.missileType = missileType;
		
		if (FMLCommonHandler.instance().getSide() == Side.CLIENT) registerRenderer();	
	}
	
	private void registerRenderer() {
		MinecraftForgeClient.registerItemRenderer(this, missileType.itemRenderer);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		list.add(EnumChatFormatting.GOLD + "Tier " + missileType.event.tier);
	}
}

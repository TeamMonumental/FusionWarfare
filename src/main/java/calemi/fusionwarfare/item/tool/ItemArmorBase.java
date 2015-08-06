package calemi.fusionwarfare.item.tool;

import java.util.List;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemArmorBase extends ItemArmor {
	
	private IIcon overlay;
	private String imagePath;
	private String imageKey;
	public boolean hasOverlay;

	public ItemArmorBase(String imagePath, String imageKey, ArmorMaterial armorMaterial, int renderIndex, int armorType, boolean hasOverlay) {
		super(armorMaterial, renderIndex, armorType);
		this.imagePath = imagePath;
		this.imageKey = imageKey;
		this.hasOverlay = hasOverlay;
		setCreativeTab(InitCreativeTabs.creativeTabTools);
		setUnlocalizedName(imagePath);
		GameRegistry.registerItem(this, imagePath);
	}
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int overlay) {
		
		if (hasOverlay && overlay > 0) {
			
		}
		
		return 0x00FFFFFF;		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack is, int overlayNum) {
		
		if (hasOverlay && overlayNum > 0) return overlay;

		return super.getIcon(is, overlayNum);	
	}
	
	@Override
	public boolean requiresMultipleRenderPasses() {
		return hasOverlay;
	}
	
	public boolean hasColor(ItemStack is) {
		return hasOverlay;
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		return Reference.MOD_ID + ":textures/models/armor/" + imageKey + "_" + (armorType == 2 ? 2 : 1) + ".png";
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":tools/" + imagePath);
		overlay = iconRegister.registerIcon(Reference.MOD_ID + ":tools/overlays/" + imagePath + "_overlay");
	}
}

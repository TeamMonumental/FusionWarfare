package calemi.fusionwarfare.item.tool;

import java.util.List;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.util.EnumColorUtil;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemArmorBase extends ItemArmor {
	
	private IIcon overlay;
	private String imagePath;
	private String imageKey;
	private String overlayKey;
	public boolean hasOverlay;

	public ItemArmorBase(String imagePath, String imageKey, String overlayKey, ArmorMaterial armorMaterial, int renderIndex, int armorType, boolean hasOverlay) {
		super(armorMaterial, renderIndex, armorType);
		this.imagePath = imagePath;
		this.imageKey = imageKey;
		this.overlayKey = overlayKey;
		this.hasOverlay = hasOverlay;
		setCreativeTab(InitCreativeTabs.creativeTabTools);
		setUnlocalizedName(imagePath);
		GameRegistry.registerItem(this, imagePath);
	}
	
	public NBTTagCompound getNBT(ItemStack is) {
		if (is.getTagCompound() == null) {
			is.setTagCompound(new NBTTagCompound());
		}

		return is.getTagCompound();
	}
	
	@Override
	public int getColor(ItemStack is) {
		
		if (hasOverlay) {
			
			if (getNBT(is).hasKey("color")) {		
				
				return getNBT(is).getInteger("color");				
			}
			
			if (getNBT(is).hasKey("player")) {		
							
				EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(getNBT(is).getString("player"));
				
				if (player.getTeam() != null) {

					return EnumColorUtil.getColorByPrefix(((ScorePlayerTeam)player.getTeam()).getColorPrefix()).hex;					
				}
			}
			
			return EnumColorUtil.AQUA.hex;
		}
		
		return 0x00FFFFFF;
	}
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int overlay) {
				
		if (hasOverlay && overlay > 0) {
			
			if (getNBT(is).hasKey("color")) {		
				
				return getNBT(is).getInteger("color");				
			}
			
			if (getNBT(is).hasKey("player")) {		
							
				EntityPlayer player = Minecraft.getMinecraft().theWorld.getPlayerEntityByName(getNBT(is).getString("player"));
				
				if (player.getTeam() != null) {

					return EnumColorUtil.getColorByPrefix(((ScorePlayerTeam)player.getTeam()).getColorPrefix()).hex;					
				}
			}
			
			return EnumColorUtil.AQUA.hex;
		}
		
		return 0x00FFFFFF;
	}
	
	@Override
	public void onUpdate(ItemStack is, World w, Entity e, int i, boolean b) {
		
		if (!w.isRemote && e instanceof EntityPlayer) {
					
			if (getNBT(is).hasKey("color")) {
				getNBT(is).removeTag("color");
			}
			
			if (!getNBT(is).getString("player").equals(((EntityPlayer)e).getDisplayName())) {
				
				getNBT(is).setString("player", ((EntityPlayer)e).getDisplayName());
			}					
		}
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
		
		if (hasOverlay && type == "overlay") {
			return Reference.MOD_ID + ":textures/models/armor/overlays/armor_overlay_" + (armorType == 2 ? 2 : 1) + ".png";
		}
		
		return Reference.MOD_ID + ":textures/models/armor/" + imageKey + "_" + (armorType == 2 ? 2 : 1) + ".png";
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":tools/" + imagePath);
		if (hasOverlay) overlay = iconRegister.registerIcon(Reference.MOD_ID + ":tools/overlays/" + overlayKey + "_overlay");
	}
}

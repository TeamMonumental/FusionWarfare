package calemi.fusionwarfare.item.tool;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.util.EnumColorUtil;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAxeBase extends ItemAxe {

	private IIcon overlay;
	public boolean hasOverlay;
	
	private String imagePath;
	private Item.ToolMaterial toolMaterial;

	public ItemAxeBase(String imagePath, ToolMaterial toolMaterial, boolean hasOverlay) {
		super(toolMaterial);		
		this.imagePath = imagePath;
		this.hasOverlay = hasOverlay;
		this.toolMaterial = toolMaterial;
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
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack is, int overlay) {
				
		if (hasOverlay && overlay > 0) {
			
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

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":tools/" + imagePath);
		if (hasOverlay) overlay = iconRegister.registerIcon(Reference.MOD_ID + ":tools/overlays/axe_overlay");
	}
}
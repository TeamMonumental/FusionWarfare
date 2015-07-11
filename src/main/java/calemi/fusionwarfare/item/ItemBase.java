package calemi.fusionwarfare.item;

import java.util.List;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemBase extends Item {
	
	public String imagePath;
	private boolean hasTexture;
	private boolean hasEffect;
	
	public ItemBase(String imagePath) {
		hasTexture = true;
		setUnlocalizedName(imagePath);
		setCreativeTab(InitCreativeTabs.creativeTabCore);
		GameRegistry.registerItem(this, imagePath);
		this.imagePath = imagePath;
	}
	
	public ItemBase(String imagePath, boolean hasTexture, boolean hasEffect) {
		setUnlocalizedName(imagePath);
		setCreativeTab(InitCreativeTabs.creativeTabCore);
		GameRegistry.registerItem(this, imagePath);
		this.imagePath = imagePath;
		this.hasTexture = hasTexture;
		this.hasEffect = hasEffect;
	}
	
	@Override
	public boolean hasEffect(ItemStack is) {
		return hasEffect;
	}
	
	public NBTTagCompound getNBT(ItemStack is) {
		if (is.getTagCompound() == null) {
			is.setTagCompound(new NBTTagCompound());
		}

		return is.getTagCompound();
	}
	
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        if (hasTexture) itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + imagePath);
    }
}
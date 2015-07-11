package calemi.fusionwarfare.item.tool;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAxeBase extends ItemAxe {

	private String imagePath;
	private Item.ToolMaterial toolMaterial;

	public ItemAxeBase(String imagePath, ToolMaterial toolMaterial) {
		super(toolMaterial);		
		this.imagePath = imagePath;
		this.toolMaterial = toolMaterial;
		setCreativeTab(InitCreativeTabs.creativeTabTools);
		setUnlocalizedName(imagePath);
		GameRegistry.registerItem(this, imagePath);
	}

	/*@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {

		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {

			list.add(EnumChatFormatting.GOLD + "Speed: " + EnumChatFormatting.AQUA + efficiencyOnProperMaterial);
			list.add(EnumChatFormatting.GOLD + "Durability: " + EnumChatFormatting.AQUA + (is.getMaxDamage() - is.getItemDamage()) + "/" + getMaxDamage());

		} else {
			list.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
		}
	}*/

	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":tools/" + imagePath);
	}
}
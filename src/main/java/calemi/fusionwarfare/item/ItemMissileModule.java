package calemi.fusionwarfare.item;

import java.util.List;

import calemi.fusionwarfare.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemMissileModule extends ItemBase {

	private int tier;
	
	public ItemMissileModule(int tier) {
		super("missile_module_" + tier);
		this.tier = tier;
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		list.add(EnumChatFormatting.GOLD + "Tier " + tier);
	}	
	
	@SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + imagePath);
    }
}

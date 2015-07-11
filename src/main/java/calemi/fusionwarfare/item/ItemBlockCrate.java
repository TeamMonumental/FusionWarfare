package calemi.fusionwarfare.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockCrate extends ItemBlockMeta {

	public ItemBlockCrate(Block block) {
		super(block);
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean b) {
		
		if (itemStack.getItemDamage() == 0) list.add(EnumChatFormatting.AQUA + "Blue");
		if (itemStack.getItemDamage() == 1) list.add(EnumChatFormatting.GOLD + "Orange");
		if (itemStack.getItemDamage() == 2) list.add(EnumChatFormatting.RED + "Red");		
	}
}

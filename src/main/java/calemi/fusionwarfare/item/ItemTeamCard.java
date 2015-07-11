package calemi.fusionwarfare.item;

import java.util.ArrayList;
import java.util.List;

import calemi.fusionwarfare.init.InitCreativeTabs;
import scala.actors.threadpool.Arrays;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemTeamCard extends ItemBase {
	
	public ItemTeamCard() {
		super("team_card");
		setMaxStackSize(1);
		setCreativeTab(InitCreativeTabs.creativeTabCore);
	}
			
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean b) {
		
		list.add(EnumChatFormatting.GOLD + "Players:");	
		list.addAll(getNames(stack));
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
		if (!world.isRemote) {
			
			List<String> list = getNames(stack);
			
			if (!list.contains(player.getDisplayName())) {
				list.add(player.getDisplayName());
				player.worldObj.playSoundAtEntity(player, "random.orb", 1F, 1F);
			}
			
			setNames(stack, list);
		}
		
		return stack;
	}
	
	public List<String> getNames(ItemStack stack) {
		
		String label = getNBT(stack).getString("names").trim();
		
		List<String> list = new ArrayList<String>();
		
		if (!label.isEmpty()) {
			list.addAll(Arrays.asList(label.split(" ")));
		}
		
		return list;
	}
	
	public void setNames(ItemStack stack, List<String> list) {
		
		StringBuilder builder = new StringBuilder();
		
		for (String s : list) {
			
			builder.append(" " + s);	
		}
		
		builder.deleteCharAt(0);
		getNBT(stack).setString("names", builder.toString());
	}
}
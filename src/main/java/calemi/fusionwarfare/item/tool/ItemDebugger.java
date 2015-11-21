package calemi.fusionwarfare.item.tool;

import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.item.ItemBase;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemDebugger extends ItemBase {

	public ItemDebugger() {
		super("debugger");
		setCreativeTab(InitCreativeTabs.creativeTabTools);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		
		if (world.getTileEntity(x, y, z) instanceof TileEntityEnergyBase) {
			
			TileEntityEnergyBase tileEntity = (TileEntityEnergyBase)world.getTileEntity(x, y, z);
			
			world.markBlockForUpdate(x, y, z);
			tileEntity.markDirty();
						
			if (world.isRemote) {
				player.addChatMessage(new ChatComponentText(""));			
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "----------CLIENT----------"));
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Energy: " + tileEntity.energy + " " + "/" + " " + tileEntity.maxEnergy));
				if (tileEntity.maxProgress > 0) player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Progress: " + tileEntity.progress * 100 / tileEntity.maxProgress + "%"));
				player.addChatMessage(new ChatComponentText(""));	
			}
		
			if (!world.isRemote) {
								
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "----------SERVER----------"));
				player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Energy: " + tileEntity.energy + " " + "/" + " " + tileEntity.maxEnergy));
				if (tileEntity.maxProgress > 0) player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Progress: " + tileEntity.progress * 100 / tileEntity.maxProgress + "%"));
				player.addChatMessage(new ChatComponentText(""));	
			}
			
			return true;
		}		
		
		return false;
	}
}

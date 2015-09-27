package calemi.fusionwarfare.api.events;

import calemi.fusionwarfare.api.IEnergy;
import calemi.fusionwarfare.api.ISecurity;
import calemi.fusionwarfare.init.InitItems;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class WrenchEvent {

	@SubscribeEvent
	public void onWrenchBlock(PlayerInteractEvent event) {
		if (!event.world.isRemote && event.action == Action.RIGHT_CLICK_BLOCK) {

			EntityPlayer player = event.entityPlayer;
			ItemStack itemInHand = player.getCurrentEquippedItem();

			if (player.isSneaking() && itemInHand != null && itemInHand.getItem() == InitItems.wrench) {
				wrenchBlock(event.world, event.x, event.y, event.z, player);
			}
		}
	}

	private void wrenchBlock(World world, int x, int y, int z, EntityPlayer entityPlayer) {

		TileEntity tileEntity = world.getTileEntity(x, y, z);

		ItemStack drop = new ItemStack(world.getBlock(x, y, z), 1, world.getBlockMetadata(x, y, z));
		NBTTagCompound dropTag = getNBT(drop);
		
		if (tileEntity instanceof IEnergy) {
			
			IEnergy energy = (IEnergy) tileEntity;
			
			dropTag.setInteger("energy", energy.getEnergy());
		}
		
		Block block = world.getBlock(x, y, z);
		block.breakBlock(world, x, y, z, block, world.getBlockMetadata(x, y, z));
		world.setBlockToAir(x, y, z);
		
		EntityItem entityItem = new EntityItem(world, x, y, z, drop);
		world.spawnEntityInWorld(entityItem);
	}
	
	@SubscribeEvent
	public void addLore(ItemTooltipEvent event) {
		
		NBTTagCompound nbt = getNBT(event.itemStack);
		
		if (nbt.hasKey("energy")) {
			event.toolTip.add(EnumChatFormatting.GOLD + "FE: " + EnumChatFormatting.AQUA + nbt.getInteger("energy"));
		}
	}

	private NBTTagCompound getNBT(ItemStack stack) {

		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}

		return stack.getTagCompound();
	}
}
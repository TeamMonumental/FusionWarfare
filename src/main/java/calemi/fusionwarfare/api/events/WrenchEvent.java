package calemi.fusionwarfare.api.events;

import calemi.fusionwarfare.api.IEnergy;
import calemi.fusionwarfare.api.ISecurity;
import calemi.fusionwarfare.block.BlockReinforcedDoor;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.ItemFEBattery;
import calemi.fusionwarfare.tileentity.TileEntityReinforcedDoor;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.util.Location;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
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
				wrenchBlock(new Location(event.world, event.x, event.y, event.z), player);
			}
		}
	}

	private void wrenchBlock(Location loc, EntityPlayer player) {

		TileEntity tileEntity = loc.getTileEntity();

		ItemStack drop = new ItemStack(loc.getBlock(), 1, loc.getBlockMetadata());
		NBTTagCompound dropTag = getNBT(drop);

		if (loc.getBlock().hasTileEntity(loc.getBlockMetadata()) || tileEntity instanceof ISecurity) {
				
			if (loc.world.getTileEntity(loc.x, loc.y - 1, loc.z) instanceof TileEntityReinforcedDoor) {
				
				if (((ISecurity)loc.world.getTileEntity(loc.x, loc.y - 1, loc.z)).isSameTeam(player.getTeam())) {
					wrenchBlock(new Location(loc.world, loc.x, loc.y - 1, loc.z), player);	
				}
			}
			
			else if (tileEntity instanceof TileEntityReinforcedDoor) {
				
				loc.getBlock().breakBlock(loc.world, loc.x, loc.y, loc.z, loc.getBlock(), loc.getBlockMetadata());
				loc.setBlockToAir();

				EntityItem entityItem = new EntityItem(loc.world, loc.x, loc.y, loc.z, new ItemStack(InitItems.reinforced_door));
				loc.world.spawnEntityInWorld(entityItem);	
			}
			
			else {
				
				if (tileEntity instanceof IEnergy) {

					IEnergy energy = (IEnergy) tileEntity;

					dropTag.setInteger("energy", energy.getEnergy());
					dropTag.setInteger("maxEnergy", energy.getMaxEnergy());
				}

				loc.getBlock().breakBlock(loc.world, loc.x, loc.y, loc.z, loc.getBlock(), loc.getBlockMetadata());
				loc.setBlockToAir();

				EntityItem entityItem = new EntityItem(loc.world, loc.x, loc.y, loc.z, drop);
				loc.world.spawnEntityInWorld(entityItem);		
			}
		}
	}

	@SubscribeEvent
	public void addLore(ItemTooltipEvent event) {

		NBTTagCompound nbt = getNBT(event.itemStack);

		if (nbt.hasKey("energy") && !(event.itemStack.getItem() instanceof Item)) {

			event.toolTip.add(EnumChatFormatting.GOLD + "FE: " + EnumChatFormatting.AQUA + nbt.getInteger("energy") + (nbt.hasKey("maxEnergy") ? "/" + nbt.getInteger("maxEnergy") : ""));
		}
	}

	private NBTTagCompound getNBT(ItemStack stack) {

		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}

		return stack.getTagCompound();
	}
}
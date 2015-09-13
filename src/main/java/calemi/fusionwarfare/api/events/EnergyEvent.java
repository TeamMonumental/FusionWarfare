package calemi.fusionwarfare.api.events;

import calemi.fusionwarfare.api.IEnergy;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.util.Location;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;

public class EnergyEvent {

	@SubscribeEvent
	public void onBlockPlace(PlaceEvent event) {
		
		if (!event.world.isRemote) {
			
			Location loc = new Location(event.world, event.x, event.y, event.z);
			TileEntity tileEntity = loc.getTileEntity();
			
			if (tileEntity instanceof IEnergy) {
				
				IEnergy energy = (IEnergy) tileEntity;
				
				NBTTagCompound nbt = getNBT(event.itemInHand);
				
				if (nbt.hasKey("energy")) {
					energy.setEnergy(nbt.getInteger("energy"));
				}
			}
		}	
	}
	
	@SubscribeEvent
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		if (!event.world.isRemote && event.action == Action.RIGHT_CLICK_BLOCK) {
			
			EntityPlayer player = event.entityPlayer;
			Location loc = new Location(event.world, event.x, event.y, event.z);
			TileEntity tileEntity = loc.getTileEntity();
			
			if (player.isSneaking() && player.inventory.getCurrentItem().getItem() == InitItems.wrench) {
				
				if (tileEntity instanceof IEnergy) {
					
					ItemStack stack = new ItemStack(Item.getItemFromBlock(loc.getBlock()), 1, loc.getBlockMetadata());
					
					NBTTagCompound nbt = getNBT(stack);
					
					nbt.setInteger("energy", ((IEnergy)tileEntity).getEnergy());
				
					EntityItem entityItem = new EntityItem(event.world, event.x, event.y, event.z);
					
					entityItem.setEntityItemStack(stack);
					
					event.world.spawnEntityInWorld(entityItem);
					
					loc.breakBlock();
				}				
			}			
		}		
	}
	
	private NBTTagCompound getNBT(ItemStack stack) {
		
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}		
		
		return stack.getTagCompound();
	}
}
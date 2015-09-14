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
	
	private NBTTagCompound getNBT(ItemStack stack) {
		
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}		
		
		return stack.getTagCompound();
	}
}
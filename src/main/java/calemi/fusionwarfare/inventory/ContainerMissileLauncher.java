package calemi.fusionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import calemi.fusionwarfare.tileentity.TileEntityBase;

public class ContainerMissileLauncher extends ContainerBase {

	public ContainerMissileLauncher(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);	
		
		//new Slot(inv, id, x, y)
		
		//Gui's Slots

		//Input Slot
		this.addSlotToContainer(new SlotMissile(tileentity, 0, 79, 33));
		
		addPlayerInv(8, 84);		
		addHotbar(8, 142);		
	}		
}

package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerNetworkController extends ContainerBase {

	public ContainerNetworkController(EntityPlayer player, TileEntityBase tileentity, int tier) {
		super(player, tileentity);		
		
		//Overclocking
		addSlotToContainer(new SlotOverclocking(tileentity, 0, 143, 64, tier * 5));
		
		//Discharge	
		addSlotToContainer(new Slot(tileentity, 1, 17, 23));
		//Charge
		addSlotToContainer(new Slot(tileentity, 2, 17, 61));
		
		addPlayerInv(8, 99);
		addHotbar(8, 157);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
}

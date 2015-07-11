package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerReactorCore extends ContainerBase {

	public ContainerReactorCore(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);		
		
		//Charge
		addSlotToContainer(new Slot(tileentity, 0, 26, 42));
		
		addPlayerInv(8, 99);
		addHotbar(8, 157);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
}

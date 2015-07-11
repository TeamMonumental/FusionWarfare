package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerEnergyTransmitter extends ContainerBase {

	public ContainerEnergyTransmitter(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);		
		
		//Overclocking
		addSlotToContainer(new SlotOverclocking(tileentity, 0, 143, 64, 15));
	
		addPlayerInv(8, 99);
		addHotbar(8, 157);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
}

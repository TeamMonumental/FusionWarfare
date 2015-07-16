package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerMissileSiloCore extends ContainerBase {

	
	
	public ContainerMissileSiloCore(EntityPlayer player, TileEntityBase tileEntity) {
		super(player, tileEntity);		
		
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new SlotMissile(tileEntity, i, 8 + (i * 18), 73));
		}
		
		addPlayerInv(8, 99);
		addHotbar(8, 157);
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
}

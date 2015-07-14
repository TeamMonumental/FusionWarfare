package calemi.fusionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import calemi.fusionwarfare.tileentity.TileEntityBase;

public class ContainerMiningUnit extends ContainerBase {

	public ContainerMiningUnit(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 8; x++) {
				this.addSlotToContainer(new Slot(tileentity, x + (y * 8), 26 + (x * 18), 19 + (y * 18)));
			}
		}
		
		addPlayerInv(8, 107);
		addHotbar(8, 165);
	}
}
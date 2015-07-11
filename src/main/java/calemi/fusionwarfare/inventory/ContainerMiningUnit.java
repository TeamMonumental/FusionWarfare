package calemi.fusionwarfare.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import calemi.fusionwarfare.tileentity.TileEntityBase;

public class ContainerMiningUnit extends ContainerBase {

	public ContainerMiningUnit(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 7; j++) {
				this.addSlotToContainer(new Slot(tileentity, j + i * 7, 26 + (j * 18), 19 + (i * 18)));
			}
		}
		
		addPlayerInv(8, 107);
		addHotbar(8, 165);
	}
}
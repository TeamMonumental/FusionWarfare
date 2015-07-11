package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;

public class ContainerSupplyCrate extends ContainerBase {

	public ContainerSupplyCrate(EntityPlayer player, TileEntityBase tileEntity) {
		super(player, tileEntity);
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(tileEntity, j + i * 9, 8 + (j * 18), 18 + (i * 18)));
			}
		}
		
		addPlayerInv(8, 84);
		addHotbar(8, 142);
	}

}

package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import net.minecraft.entity.player.EntityPlayer;

public class ContainerInventory extends ContainerBase {

	public ContainerInventory(EntityPlayer player, TileEntityEnergyBase tileEntity, int x, int y) {
		super(player, tileEntity);
		
		addPlayerInv(x, y);
		addHotbar(x, y + 58);
	}
}

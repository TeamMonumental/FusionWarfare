package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEnergyTank extends ContainerEnergyBase {

	public ContainerEnergyTank(EntityPlayer player, TileEntityEnergyBase tileentity) {
		super(player, tileentity);		
		
		addPlayerInv(8, 99);
		addHotbar(8, 157);
	}
}

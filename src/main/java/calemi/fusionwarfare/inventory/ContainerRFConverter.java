package calemi.fusionwarfare.inventory;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.packet.ClientPacketHandler;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityRFConverter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerRFConverter extends ContainerBase {

	public TileEntityRFConverter tileEntityRF;
	
	public ContainerRFConverter(EntityPlayer player, TileEntityBase tileEntity) {
		super(player, tileEntity);
		
		tileEntityRF = (TileEntityRFConverter) tileEntity;
		
		addPlayerInv(8, 84);
		addHotbar(8, 142);
	}
	
	public void addCraftingToCrafters(ICrafting craft) {
		super.addCraftingToCrafters(craft);
		
		craft.sendProgressBarUpdate(this, 1, tileEntityRF.storage.getEnergyStored());
	}
	
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		if (player instanceof EntityPlayerMP) {
			FusionWarfare.network.sendTo(new ClientPacketHandler("sync.rf%" + tileEntityRF.storage.getEnergyStored()), (EntityPlayerMP) player);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int i2) {
		
		if (i == 2) {
			i2 = tileEntityRF.storage.getEnergyStored();
		}		
	}
}

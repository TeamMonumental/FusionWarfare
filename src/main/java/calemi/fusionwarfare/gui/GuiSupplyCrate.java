package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.inventory.ContainerSupplyCrate;
import calemi.fusionwarfare.tileentity.TileEntitySupplyCrate;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.tileentity.base.TileEntityInventoryBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class GuiSupplyCrate extends GuiContainerBase {

	public GuiSupplyCrate(EntityPlayer player, TileEntityInventoryBase tileEntity) {
		super(new ContainerSupplyCrate(player, tileEntity), player, tileEntity);
	}

	@Override
	public String getGuiTextures() {
		return "supply_crate";
	}

	@Override
	public String getGuiTitle() {
		return "Supply Crate";
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		
		if (((TileEntitySupplyCrate)tileEntity).isEmpty()) {
			player.closeScreen();
		}
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		
	}
}

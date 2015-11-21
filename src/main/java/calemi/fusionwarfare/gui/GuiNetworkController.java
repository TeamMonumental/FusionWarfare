package calemi.fusionwarfare.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerEnergyBase;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.inventory.ContainerNetworkController;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;

public class GuiNetworkController extends GuiContainerBase {

	public GuiNetworkController(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(new ContainerNetworkController(player, tileEntity), player, tileEntity);
	}

	@Override
	public int getScreenYOffset() {
		return 15;
	}
	
	@Override
	public String getGuiTextures() {
		return "network_controller";
	}

	@Override
	public String getGuiTitle() {
		
		TileEntityNetworkController tileEntityNC = (TileEntityNetworkController)tileEntityEnergy;
		
		String name = "Basic";	
		if (tileEntityNC.getTierFromEnergy() == 2) name = "Advanced";	
		if (tileEntityNC.getTierFromEnergy() == 3) name = "Hyper";	
		
		return name + " Network Controller";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawLargeFuelBar(63, 80);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawLargeFuelBarTextBox(63, 80, mouseX, mouseY);
	}
}
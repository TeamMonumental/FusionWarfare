package calemi.fusionwarfare.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerBase;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.inventory.ContainerNetworkController;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;

public class GuiNetworkController extends GuiContainerBase {

	public GuiNetworkController(EntityPlayer player, TileEntityBase tileEntity) {
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
		
		TileEntityNetworkController tileEntityNC = (TileEntityNetworkController)tileEntity;
		
		String name = "";
		
		if (tileEntityNC.tier == 1) name = "Basic";	
		if (tileEntityNC.tier == 2) name = "Advanced";	
		if (tileEntityNC.tier == 3) name = "Hyper";	
		
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
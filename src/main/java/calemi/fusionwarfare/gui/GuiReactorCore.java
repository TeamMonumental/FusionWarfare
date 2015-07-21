package calemi.fusionwarfare.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerBase;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.inventory.ContainerNetworkController;
import calemi.fusionwarfare.inventory.ContainerReactorCore;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.gen.reactor.TileEntityAdvancedHydroReactorCore;

public class GuiReactorCore extends GuiContainerBase {

	private String name;
	
	public GuiReactorCore(EntityPlayer player, TileEntityBase tileEntity, String name) {
		super(new ContainerReactorCore(player, tileEntity), player, tileEntity);
		this.name = name;
	}

	@Override
	public int getScreenYOffset() {
		return 15;
	}
	
	@Override
	public String getGuiTextures() {
		return "reactor_core";
	}

	@Override
	public String getGuiTitle() {
		return name;
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		if (tileEntity instanceof TileEntityAdvancedHydroReactorCore) {
        	drawInfoTextBar("Capsules: " + ((TileEntityAdvancedHydroReactorCore)tileEntity).capsules + "/4", 0);
        }		
		
		drawLargeFuelBar(63, 80);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawLargeFuelBarTextBox(63, 80, mouseX, mouseY);
	}
}
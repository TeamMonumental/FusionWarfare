package calemi.fusionwarfare.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.inventory.ContainerEnergyTransmitter;
import calemi.fusionwarfare.inventory.ContainerNetworkController;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntityEnergyTransmitter;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;

public class GuiEnergyTransmitter extends GuiContainerBase {

	public GuiEnergyTransmitter(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerEnergyTransmitter(player, tileEntity), player, tileEntity);
	}
	
	@Override
	public int getScreenYOffset() {
		return 15;
	}

	@Override
	public String getGuiTextures() {
		return "energy_transmitter";
	}

	@Override
	public String getGuiTitle() {
		return "Energy Transmitter";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		TileEntityEnergyTransmitter tileEntityEnergyTransmitter = (TileEntityEnergyTransmitter) tileEntity;
		
		drawRightInfoTextBar("Target X: " + tileEntityEnergyTransmitter.targetX, 0);
		drawRightInfoTextBar("Target Y: " + tileEntityEnergyTransmitter.targetY, 1);
		drawRightInfoTextBar("Target Z: " + tileEntityEnergyTransmitter.targetZ, 2);
		
		if (tileEntityEnergyTransmitter.target == null) {
			drawRightInfoTextBar(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "Receiver Not Found!", 3);
		}
		
		else {
			drawRightInfoTextBar("Receiver Found", 3);
		}
		
		drawLargeFuelBar(63, 80);
	}

	@Override	
	public void drawGuiForeground(int mouseX, int mouseY) {
			
		drawLargeFuelBarTextBox(63, 80, mouseX, mouseY);
	}	
}

	/*private static final ResourceLocation guiTextures = new ResourceLocation(Reference.MOD_ID + ":textures/gui/energy_transmitter.png");

	private EntityPlayer player;
	private TileEntityEnergyTransmitter tileentity;

	public GuiEnergyTransmitter(EntityPlayer player, TileEntityEnergyTransmitter tileentity) {
		super(new ContainerEnergyTransmitter(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		ySize += 15;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {

		updateScreen();

		this.mc.getTextureManager().bindTexture(guiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 181);

		// (teleX, teleY, originX, originY, increaseWidth, increaseHeight)

		// Fuel Bar
		int i1 = tileentity.getCurrentEnergyScaled(61);
		this.drawTexturedModalRect(k + 63, l + 81 - i1, 176, 60 - i1 + 1, 49, i1);

		this.drawTexturedModalRect(k + 63, l + 26, 176, 61, 12, 50);

		drawCreativeTabHoveringText("Energy: " + tileentity.energy + "/" + tileentity.maxEnergy, k + 79 + 90, l + 18);
		drawCreativeTabHoveringText("Target X: " + tileentity.targetX, k + 79 + 90, l + 18 + 20);
		drawCreativeTabHoveringText("Target Y: " + tileentity.targetY, k + 79 + 90, l + 18 + 40);
		drawCreativeTabHoveringText("Target Z: " + tileentity.targetZ, k + 79 + 90, l + 18 + 60);
		
		if (tileentity.target == null) {
			drawCreativeTabHoveringText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "Receiver Not Found!", k + 79 + 90, l + 18 + 80);
		}
		
		else {
			drawCreativeTabHoveringText("Receiver Found", k + 79 + 90, l + 18 + 80);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		updateScreen();

		mc.fontRenderer.drawString("Energy Transmitter", 8, 6, 4210752);
	}
}*/
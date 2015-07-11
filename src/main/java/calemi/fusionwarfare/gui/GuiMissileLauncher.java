package calemi.fusionwarfare.gui;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerMissileLauncher;
import calemi.fusionwarfare.inventory.ContainerOneInput;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;

public class GuiMissileLauncher extends GuiContainerBase {

	public GuiMissileLauncher(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerMissileLauncher(player, tileEntity), player, tileEntity);
	}

	@Override
	public String getGuiTextures() {
		return "missile_launcher";
	}

	@Override
	public String getGuiTitle() {
		return "Missile Launcher";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawRightInfoTextBar("Target X: " + ((TileEntityMissileLauncher)tileEntity).targetX, 0);
		drawRightInfoTextBar("Target Z: " + ((TileEntityMissileLauncher)tileEntity).targetZ, 1);
		drawSmallFuelBar(8, 65);
		drawLongProgBar(7, 71);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawSmallFuelBarTextBox(8, 65, mouseX, mouseY);
		drawLongProgBarTextBox(7, 71, mouseX, mouseY);
	}	
}

	/*private static final ResourceLocation guiTextures = new ResourceLocation(Reference.MOD_ID + ":textures/gui/missile_launcher.png");

	private EntityPlayer player;	
	private TileEntityBase tileentity;

	public GuiMissileLauncher(EntityPlayer player, TileEntityBase tileentity) {
		super(new ContainerMissileLauncher(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
		
	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {

		updateScreen();

		this.mc.getTextureManager().bindTexture(guiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		// (teleX, teleY, originX, originY, increaseWidth, increaseHeight)

		// Fuel Bar
		int i1 = this.tileentity.getCurrentEnergyScaled(50);
		this.drawTexturedModalRect(k + 135, l + 69 - i1, 176, 50 - i1, 14, i1 + 1);

		// Progress Bar
		int i2 = this.tileentity.getCurrentProgressScaled(50);
		this.drawTexturedModalRect(k + 153, l + 69 - i2, 190, 50 - i2, 14, i2 + 1);

		drawCreativeTabHoveringText("Energy: " + tileentity.energy + "/" + tileentity.maxEnergy, k + 79 + 90, l + 18);
		drawCreativeTabHoveringText("Progress: " + tileentity.progress * 100 / tileentity.maxProgress + "%", k + 79 + 90, l + 18 + 20);
		drawCreativeTabHoveringText("Target X: " + ((TileEntityMissileLauncher)tileentity).targetX, k + 79 + 90, l + 18 + 40);
		drawCreativeTabHoveringText("Target Z: " + ((TileEntityMissileLauncher)tileentity).targetZ, k + 79 + 90, l + 18 + 60);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		updateScreen();

		mc.fontRenderer.drawString("Missile Launcher", 8, this.ySize - 163 + 2, 4210752);		
	}
}*/
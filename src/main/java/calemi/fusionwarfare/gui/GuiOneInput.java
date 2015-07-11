package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerOneInput;
import calemi.fusionwarfare.inventory.ContainerTwoInputs;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;

public class GuiOneInput extends GuiContainerBase {

	private String name;
	
	public GuiOneInput(EntityPlayer player, TileEntityBase tileEntity, String name) {
		super(new ContainerOneInput(player, tileEntity), player, tileEntity);
		this.name = name;
	}

	@Override
	public String getGuiTextures() {
		return "one_input";
	}

	@Override
	public String getGuiTitle() {
		return name;
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawSmallFuelBar(19, 69);
		drawSmallProgBar(79, 38);
	}
	
	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawSmallFuelBarTextBox(19, 69, mouseX, mouseY);
		drawSmallProgBarTextBox(79, 38, mouseX, mouseY);
	}	
}

	
	
	/*private static final ResourceLocation guiTextures = new ResourceLocation(Reference.MOD_ID + ":textures/gui/one_input.png");

	private EntityPlayer player;
	private TileEntityBase tileentity;
	private String name;

	public GuiOneInput(EntityPlayer player, TileEntityBase tileentity, String name) {
		super(new ContainerOneInput(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		this.name = name;
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
		this.drawTexturedModalRect(k + 19, l + 69 - i1, 176, 62 - i1, 14, i1 + 1);

		// Progress Bar
		int i2 = this.tileentity.getCurrentProgressScaled(24);
		this.drawTexturedModalRect(k + 79, l + 38, 176, 0, i2 + 1, 12);

		drawCreativeTabHoveringText("Energy: " + tileentity.energy + "/" + tileentity.maxEnergy, k + 79 + 90, l + 18);
		drawCreativeTabHoveringText("Progress: " + tileentity.progress * 100 / tileentity.maxProgress + "%", k + 79 + 90, l + 18 + 20);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		updateScreen();

		mc.fontRenderer.drawString(name, 8, this.ySize - 163 + 2, 4210752);
	}
}*/
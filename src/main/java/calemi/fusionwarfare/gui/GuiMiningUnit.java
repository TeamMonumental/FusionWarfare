package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerMiningUnit;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityMiningUnit;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiMiningUnit extends GuiContainerBase {

	public GuiMiningUnit(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerMiningUnit(player, tileEntity), player, tileEntity);
	}
	
	@Override
	public int getScreenYOffset() {
		return 40;
	}

	@Override
	public String getGuiTextures() {
		return "mining_unit";
	}

	@Override
	public String getGuiTitle() {
		return "Mining Unit";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawSmallFuelBar(8, 79);
		drawLongProgBar(7, 94);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawSmallFuelBarTextBox(8, 79, mouseX, mouseY);
		drawLongProgBarTextBox(7, 94, mouseX, mouseY);
	}	
}

	/*private static final ResourceLocation guiTextures = new ResourceLocation(Reference.MOD_ID + ":textures/gui/mining_unit.png");

	private EntityPlayer player;
	private TileEntityBase tileentity;

	public GuiMiningUnit(EntityPlayer player, TileEntityBase tileentity) {
		super(new ContainerMiningUnit(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		ySize += 40;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {

		updateScreen();

		this.mc.getTextureManager().bindTexture(guiTextures);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, 189);

		// (teleX, teleY, originX, originY, increaseWidth, increaseHeight)

		// Fuel Bar
		int i1 = this.tileentity.getCurrentEnergyScaled(71);
		this.drawTexturedModalRect(k + 135, l + 89 - i1, 176, 70 - i1, 14, i1 + 1);

		// Progress Bar
		int i2 = this.tileentity.getCurrentProgressScaled(71);
		this.drawTexturedModalRect(k + 153, l + 89 - i2, 190, 70 - i2, 14, i2 + 1);

		drawCreativeTabHoveringText("Fuel: " + tileentity.energy + "/" + tileentity.maxEnergy, k + 79 + 90, l + 18);
		drawCreativeTabHoveringText("Progress: " + tileentity.progress * 100 / tileentity.maxProgress + "%", k + 79 + 90, l + 18 + 20);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		updateScreen();

		mc.fontRenderer.drawString("Mining Unit", 8, 6, 4210752);
	}
}*/
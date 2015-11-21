package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.inventory.ContainerOverclockedEnergyTank;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityAuraBase;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiAuraBase extends GuiContainerBase {

	private GuiFusionButton button;
	
	private TileEntityAuraBase tileEntityAura = (TileEntityAuraBase)tileEntityEnergy;
	
	private String name;
	
	public GuiAuraBase(EntityPlayer player, TileEntityEnergyBase tileEntity, String name) {
		super(new ContainerOverclockedEnergyTank(player, tileEntity, 15), player, tileEntity);
		this.name = name;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		button = new GuiFusionButton(0, getScreenX() + 17, getScreenY() + 65, 32, tileEntityAura.isActive ? "On" : "Off");
		buttonList.add(button);		
	}

	@Override
	protected void actionPerformed(GuiButton button) {

		if (button.id == 0) {
			tileEntityAura.toggle();
			FusionWarfare.network.sendToServer(new ServerPacketHandler("toggle.aura%" + tileEntityEnergy.xCoord + "%" + tileEntityEnergy.yCoord + "%" + tileEntityEnergy.zCoord));
		}
	}
	
	@Override
	public void updateScreen() {		
		button.displayString = tileEntityAura.isActive ? "On" : "Off";
	}
	
	@Override
	public int getScreenYOffset() {
		return 15;
	}

	@Override
	public String getGuiTextures() {
		return "overclocked_energy_tank";
	}

	@Override
	public String getGuiTitle() {
		return name;
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		drawLargeFuelBar(63, 80);	
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + getGuiTextures() + ".png"));
		drawTexturedModalRect(getScreenX() + 7, getScreenY() + 85, 0, getGuiSizeY(), 163, 9);
		drawLongProgBar(7, 86);		
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawLargeFuelBarTextBox(63, 80, mouseX, mouseY);
		drawLongProgBarTextBox(7, 86, mouseX, mouseY);
	}
}
package calemi.fusionwarfare.gui;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.inventory.ContainerEnergyBase;
import calemi.fusionwarfare.inventory.ContainerInventory;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkBeacon;
import calemi.fusionwarfare.util.EnumColorUtil;
import codechicken.core.gui.GuiCCTextField;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class GuiNetworkBeacon extends GuiContainerBase {
	
	TileEntityNetworkBeacon tileEntityBeacon = (TileEntityNetworkBeacon)tileEntityEnergy;
	
	private GuiNumberField codeField;
	private GuiFusionButton copyButton;
	private GuiFusionButton doneButton;
	
	public GuiNetworkBeacon(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(new ContainerInventory(player, tileEntity, 8, 38), player, tileEntity);
	}
	
	@Override
	public String getGuiTextures() {
		return "network_beacon";
	}
	
	@Override
	public String getGuiTitle() {
		return "Network Beacon";
	}

	@Override
	public int getScreenYOffset() {
		return -46;
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}
	
	@Override
	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
		codeField.textboxKeyTyped(c, i);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {			
			setCode();
		}
    }

	@Override
	protected void mouseClicked(int x, int y, int i) {
	    super.mouseClicked(x, y, i);
	    codeField.mouseClicked(x, y, i);
	}
	
	@Override
	public void initGui() {		
		super.initGui();
		
		Keyboard.enableRepeatEvents(true);
		
		codeField = new GuiNumberField(this.fontRendererObj, getScreenX() + 47, getScreenY() + 21, 88, 12);
		codeField.setTextColor(-1);
		codeField.setDisabledTextColour(-1);
		codeField.setEnableBackgroundDrawing(false);
		codeField.setMaxStringLength(9);
		
		copyButton = new GuiFusionButton(0, getScreenX() + 26, getScreenY() + 17, 16, "C");
		buttonList.add(copyButton);
		
		doneButton = new GuiFusionButton(1, getScreenX() + 134, getScreenY() + 17, 16, "+");
		buttonList.add(doneButton);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		if (button.id == copyButton.id) {		
			GuiScreen.setClipboardString(Integer.toString(tileEntityBeacon.code));
		}
		
		if (button.id == doneButton.id) {		
			setCode();
		}
	}
	
	public void setCode() {
		
		if (codeField.getText() != null && !codeField.getText().trim().isEmpty()) {

			tileEntityBeacon.setCode(Integer.parseInt(codeField.getText()));
			FusionWarfare.network.sendToServer(new ServerPacketHandler("code%" + tileEntityEnergy.xCoord + "%" + tileEntityEnergy.yCoord + "%" + tileEntityEnergy.zCoord + "%" + codeField.getText()));
			codeField.setText("");
		}
	}
	
	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		if (tileEntityBeacon.code != 0) drawInfoTextBar(Integer.toString(tileEntityBeacon.code), 0);		
		codeField.drawTextBox();
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		
		drawStringOverBox("Copy", 26, 17, 16, 16, mouseX, mouseY);
		drawStringOverBox("Set", 134, 17, 16, 16, mouseX, mouseY);
	}
}
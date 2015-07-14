package calemi.fusionwarfare.gui;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.inventory.ContainerMissileSiloCore;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileSiloCore;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class GuiMissileSiloCore extends GuiContainerBase {

	private GuiFusionButton[] buttons = new GuiFusionButton[2];
	
	public GuiMissileSiloCore(EntityPlayer player, TileEntityBase tileEntity) {
		super(new ContainerMissileSiloCore(player, tileEntity), player, tileEntity);
	}

	@Override
	public void initGui() {
		super.initGui();
		
		TileEntityMissileSiloCore tileEntityMissileSiloCore = (TileEntityMissileSiloCore)tileEntity;
		
		buttons[0] = new GuiFusionButton(0, getScreenX() + 32, getScreenY() + 22, 130, "Delay: " + tileEntityMissileSiloCore.currentDelay);
		buttonList.add(buttons[0]);
		
		buttons[1] = new GuiFusionButton(1, getScreenX() + 32, getScreenY() + 22 + 26, 130, "Spray Mode: " + tileEntityMissileSiloCore.sprayMode);
		buttonList.add(buttons[1]);
	}
	
	@Override
	public void updateScreen() {
		
		TileEntityMissileSiloCore tileEntityMissileSiloCore = (TileEntityMissileSiloCore)tileEntity;
		
		buttons[0].displayString = "Delay: " + tileEntityMissileSiloCore.currentDelay;
		buttons[1].displayString = "Spray Mode: " + tileEntityMissileSiloCore.sprayMode;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {		
		super.actionPerformed(button);
		
		TileEntityMissileSiloCore tileEntityMissileSiloCore = (TileEntityMissileSiloCore)tileEntity;
		
		if (button.id == buttons[0].id) {			
			FusionWarfare.network.sendToServer(new ServerPacketHandler("currentDelay%" + tileEntity.xCoord + "%" + tileEntity.yCoord + "%" + tileEntity.zCoord));
		}
		
		if (button.id == buttons[1].id) {
			FusionWarfare.network.sendToServer(new ServerPacketHandler("sprayMode%" + tileEntity.xCoord + "%" + tileEntity.yCoord + "%" + tileEntity.zCoord));
		}
	}
	
	@Override
	public int getScreenYOffset() {
		return 15;
	}
	
	@Override
	public String getGuiTextures() {
		return "missile_silo_core";
	}

	@Override
	public String getGuiTitle() {
		return "Missile Silo";
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		if (((TileEntitySecurity)tileEntity).getTeam() != null) drawLeftInfoTextBar(((TileEntitySecurity)tileEntity).teamName, 0);
		drawRightInfoTextBar("Target X: " + ((TileEntityMissileSiloCore)tileEntity).targetX, 0);
		drawRightInfoTextBar("Target Z: " + ((TileEntityMissileSiloCore)tileEntity).targetZ, 1);
		drawSmallFuelBar(8, 68);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawSmallFuelBarTextBox(8, 68, mouseX, mouseY);
	}

}

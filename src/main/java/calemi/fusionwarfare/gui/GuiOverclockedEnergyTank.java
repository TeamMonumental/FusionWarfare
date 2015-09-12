package calemi.fusionwarfare.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.inventory.ContainerOverclockedEnergyTank;
import calemi.fusionwarfare.inventory.ContainerNetworkController;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntityEnergyReceiver;
import calemi.fusionwarfare.tileentity.TileEntityEnergyTransmitter;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;

public class GuiOverclockedEnergyTank extends GuiContainerBase {

	private String name;
	boolean hasProgBar;
	
	public GuiOverclockedEnergyTank(EntityPlayer player, TileEntityBase tileEntity, String name, int overClockNumber, boolean hasProgBar) {
		super(new ContainerOverclockedEnergyTank(player, tileEntity, overClockNumber), player, tileEntity);
		this.name = name;
		this.hasProgBar = hasProgBar;
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
		
		if (hasProgBar) {
			mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/" + getGuiTextures() + ".png"));
			drawTexturedModalRect(getScreenX() + 7, getScreenY() + 85, 0, getGuiSizeY(), 163, 9);
			drawLongProgBar(7, 86);
		}
		
		if (tileEntity instanceof TileEntityEnergyTransmitter) {
			
			TileEntityEnergyTransmitter tileEntityTransmitter = (TileEntityEnergyTransmitter) tileEntity;
			TileEntityEnergyReceiver tileEntityReciever = (TileEntityEnergyReceiver) player.worldObj.getTileEntity(tileEntityTransmitter.targetX, tileEntityTransmitter.targetY, tileEntityTransmitter.targetZ);
		
			drawInfoTextBar("Target X: " + tileEntityTransmitter.targetX, 0);
			drawInfoTextBar("Target Y: " + tileEntityTransmitter.targetY, 1);
			drawInfoTextBar("Target Z: " + tileEntityTransmitter.targetZ, 2);		
			
			if (tileEntityReciever == null || tileEntityTransmitter.target == null || !tileEntityTransmitter.isSameTeam(tileEntityReciever.getTeam())) {
				drawInfoTextBar(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "Receiver Not Found!", 3);
			}
			
			else {
				drawInfoTextBar("Receiver Found", 3);
			}	
		}	
	}

	@Override	
	public void drawGuiForeground(int mouseX, int mouseY) {
			
		drawLargeFuelBarTextBox(63, 80, mouseX, mouseY);
		if (hasProgBar) drawLongProgBarTextBox(7, 86, mouseX, mouseY);
	}
}
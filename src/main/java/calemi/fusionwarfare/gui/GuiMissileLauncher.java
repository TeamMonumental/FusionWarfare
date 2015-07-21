package calemi.fusionwarfare.gui;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.inventory.ContainerMissileLauncher;
import calemi.fusionwarfare.inventory.ContainerOneInput;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
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
		drawInfoTextBar("Target X: " + ((TileEntityMissileLauncher)tileEntity).targetX, 0);
		drawInfoTextBar("Target Z: " + ((TileEntityMissileLauncher)tileEntity).targetZ, 1);
		drawSmallFuelBar(8, 65);
		drawLongProgBar(7, 71);
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
		drawSmallFuelBarTextBox(8, 65, mouseX, mouseY);
		drawLongProgBarTextBox(7, 71, mouseX, mouseY);
	}
}
package calemi.fusionwarfare.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public interface ITileEntityGuiHandler {

	public Container getTileContainer(EntityPlayer player);
	
	public GuiContainer getTileGuiContainer(EntityPlayer player);
}

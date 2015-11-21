package calemi.fusionwarfare.tileentity.network;

import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.api.ISecurity;
import calemi.fusionwarfare.gui.GuiNetworkBeacon;
import calemi.fusionwarfare.inventory.ContainerEnergyBase;
import calemi.fusionwarfare.inventory.ContainerInventory;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.scoreboard.Team;

public class TileEntityNetworkBeacon extends TileEntitySecurity implements ITileEntityGuiHandler {

	public int code;
		
	public void setCode(int code) {
		this.code = code;
	}
	
	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.NONE;
	}

	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}

	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		code = nbt.getInteger("code");
	}

	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		nbt.setInteger("code", code);
	}
	
	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerInventory(player, this, 8, 38);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiNetworkBeacon(player, this);
	}	
}

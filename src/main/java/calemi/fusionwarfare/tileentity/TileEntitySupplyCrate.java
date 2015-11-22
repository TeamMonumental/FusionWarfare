package calemi.fusionwarfare.tileentity;

import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.gui.GuiSupplyCrate;
import calemi.fusionwarfare.inventory.ContainerSupplyCrate;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.tileentity.base.TileEntityInventoryBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntitySupplyCrate extends TileEntityInventoryBase implements ITileEntityGuiHandler {

	private int time;
	private int maxTime = 36000; //30min
	
	@Override
	public void updateEntity() {

		time++;
		
		if (time >= maxTime) {
			
			for (ItemStack slot : slots) {				
				
				if (slot != null) {
					slot.stackSize = 0;
				}					
			}
			
			worldObj.setBlockToAir(xCoord, yCoord, zCoord);
		}
		
		if (isEmpty()) {
			
			if (!worldObj.isRemote) {
				worldObj.setBlockToAir(xCoord, yCoord, zCoord);	
			}
		}
	}
	
	public boolean isEmpty() {
		
		for (ItemStack slot : slots) {
			
			if (slot != null) {			
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);	
		nbt.setInteger("time", time);	
	}

	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);	
		time = nbt.getInteger("time");
	}
	
	@Override
	public int getSizeInventory() {
		return 27;
	}
	
	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerSupplyCrate(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiSupplyCrate(player, this);
	}
}

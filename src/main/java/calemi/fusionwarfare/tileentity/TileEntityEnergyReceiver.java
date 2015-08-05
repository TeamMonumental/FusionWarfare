package calemi.fusionwarfare.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityEnergyReceiver extends TileEntitySecurity {

	public TileEntityEnergyReceiver() {
		maxEnergy = 5000;
	}
		
	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
	
	@Override
	public int getSizeInventory() {
		return 0;
	}
}

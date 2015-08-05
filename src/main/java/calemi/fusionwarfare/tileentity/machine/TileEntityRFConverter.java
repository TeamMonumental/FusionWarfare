package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityRFConverter extends TileEntityBase {
	
	public TileEntityRFConverter() {
		maxEnergy = 10000;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();		
	}
		
	//--------------------------------------------------------------------
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
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
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
	}
	
	//-----------------------------------------------------------
	
	@Override
	public Packet getDescriptionPacket() {
		
		super.getDescriptionPacket();
		
		NBTTagCompound syncData = new NBTTagCompound();
	
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		
		super.onDataPacket(net, pkt);			
	}
}

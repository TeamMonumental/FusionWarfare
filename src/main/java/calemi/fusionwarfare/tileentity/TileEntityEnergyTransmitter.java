package calemi.fusionwarfare.tileentity;

import calemi.fusionwarfare.util.EnergyUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityEnergyTransmitter extends TileEntitySecurity {

	public int targetX;
	public int targetY;
	public int targetZ;
	public TileEntityEnergyReceiver target;

	public TileEntityEnergyTransmitter() {
		maxEnergy = 5000;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		int transferRate = 5 + (slots[0] == null ? 0 : slots[0].stackSize * 5);

		if (worldObj.getTileEntity(targetX, targetY, targetZ) instanceof TileEntityEnergyReceiver) {

			TileEntityEnergyReceiver tileEntity = (TileEntityEnergyReceiver) worldObj.getTileEntity(targetX, targetY, targetZ);

			target = tileEntity;
					
			if (!worldObj.isRemote) {
				
				if (compare(tileEntity)) {
					EnergyUtil.transferEnergy(this, tileEntity, transferRate);
				}
			}
		}

		else {
			target = null;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setInteger("targetX", targetX);
		nbt.setInteger("targetY", targetY);
		nbt.setInteger("targetZ", targetZ);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		targetX = nbt.getInteger("targetX");
		targetY = nbt.getInteger("targetY");
		targetZ = nbt.getInteger("targetZ");
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.INPUT;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}
}

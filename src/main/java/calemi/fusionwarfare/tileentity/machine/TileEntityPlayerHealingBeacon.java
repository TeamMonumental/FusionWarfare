package calemi.fusionwarfare.tileentity.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.util.EnergyUtil;

public class TileEntityPlayerHealingBeacon extends TileEntitySecurity {
	
	private int energyCost = 500;
	
	public TileEntityPlayerHealingBeacon() {
		maxEnergy = 5000;
		maxProgress = 20;
	}
	
	@Override
	public void updateEntity() {
				
		if (!isDone() && worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
			progress++;
		}

		else {
			resetProgress();
		}
		
		if (!worldObj.isRemote) {
			
			if (isDone()) {
				
				for (Object o : worldObj.loadedEntityList) {
					
					if (o instanceof EntityPlayer) {
						
						EntityPlayer player = (EntityPlayer)o;
												
						if (!player.capabilities.isCreativeMode && isSameTeam(player) && player.getDistance(xCoord, yCoord, zCoord) <= 32 && player.getHealth() < player.getMaxHealth()) {
							
							if (EnergyUtil.canSubtractEnergy(this, energyCost)) {
								EnergyUtil.subtractEnergy(this, energyCost);
								player.heal(2);
								break;
							}						
						}
					}
				}
			}			
		}	
	}
	
	//-------------------------------------------------------------------------------------------
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
	
		writeToNBT(syncData);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {

		readFromNBT(pkt.func_148857_g());
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[]{};
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
		return EnumIO.INPUT;
	}
}

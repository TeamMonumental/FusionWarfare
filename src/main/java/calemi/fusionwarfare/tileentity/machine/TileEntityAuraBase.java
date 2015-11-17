package calemi.fusionwarfare.tileentity.machine;

import java.util.ArrayList;
import java.util.List;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.entity.DamageSourceTurret;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;

public abstract class TileEntityAuraBase extends TileEntitySecurity {
	
	public abstract int getEnergyCost();
	public abstract int getMaxEnergy();
	public abstract int getProgressTime();
	
	public abstract boolean onAction();
	
	public static List<Entity> entitiesInAura = new ArrayList<Entity>();
	public static List<Location> blocksInAura = new ArrayList<Location>();
	
	public boolean isActive = false;
	
	public TileEntityAuraBase() {
		maxEnergy = getMaxEnergy();
		maxProgress = getProgressTime();
	}
	
	@Override
	public void updateEntity() {
		
		int range = 5 + (slots[0] != null ? slots[0].stackSize : 0);
		
		if ((isActive || worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) && !isDone() && EnergyUtil.canSubtractEnergy(this, getEnergyCost())) {			
			progress++;
		}
			
		if (isDone()) {
			
			entitiesInAura.clear();		
			
			for (Object o : worldObj.loadedEntityList) {
				
				if (o != null && o instanceof Entity && ((Entity)o).getDistance(xCoord, yCoord, zCoord) < range) {				
									
					entitiesInAura.add((Entity) o);
				}
			}
			
			blocksInAura = ShapeUtil.getSphere(worldObj, xCoord, yCoord, zCoord, range);
			
			if (onAction()) {
				resetProgress();			
				EnergyUtil.subtractEnergy(this, getEnergyCost());
			}		
		}
	}
	
	public void toggle() {
		isActive = !isActive;				
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[]{};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return false;
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
	public void readFromNBT(NBTTagCompound nbt) {			
		super.readFromNBT(nbt);	
		isActive = nbt.getBoolean("isActive");	
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {		
		super.writeToNBT(nbt);	
		nbt.setBoolean("isActive", isActive);	
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();
		
		syncData.setInteger("FE", energy);
		syncData.setInteger("progress", progress);
		syncData.setBoolean("isActive", isActive);
		
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
			
		energy = pkt.func_148857_g().getInteger("FE");
		progress = pkt.func_148857_g().getInteger("progress");
		isActive = pkt.func_148857_g().getBoolean("isActive");
	}
}

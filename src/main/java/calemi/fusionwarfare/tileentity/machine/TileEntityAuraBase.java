package calemi.fusionwarfare.tileentity.machine;

import java.util.ArrayList;
import java.util.List;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.entity.DamageSourceTurret;
import calemi.fusionwarfare.gui.GuiAuraBase;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;

public abstract class TileEntityAuraBase extends TileEntitySecurity implements ITileEntityGuiHandler {
	
	public abstract String getName();
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
		
		int range = 5 + overclockedDifference();
		
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
	
	@Override
	public int overclockedDifference() {
		return (getOverclockingSlot() != null ? getOverclockingSlot().stackSize : 0);
	}
	
	public void toggle() {
		isActive = !isActive;				
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.INPUT;
	}

	@Override
	public ItemStack getOverclockingSlot() {
		return slots[0];
	}
	
	@Override
	public int getSizeInventory() {
		return 1;
	}
	
	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		isActive = nbt.getBoolean("isActive");
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		nbt.setBoolean("isActive", isActive);
	}
	
	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerEnergyTank(player, this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiAuraBase(player, this, getName());
	}
}

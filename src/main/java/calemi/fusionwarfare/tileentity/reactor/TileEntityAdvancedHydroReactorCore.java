package calemi.fusionwarfare.tileentity.reactor;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraftforge.common.util.ForgeDirection;
import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.gui.GuiReactorCore;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.inventory.ContainerReactorCore;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;

public class TileEntityAdvancedHydroReactorCore extends TileEntityEnergyBase implements ITileEntityGuiHandler {

	public int capsules;

	public TileEntityAdvancedHydroReactorCore() {
		maxEnergy = 200000;
	}

	@Override
	public void updateEntity() {

		Random rand = new Random();

		if (!worldObj.isRemote) {

			if (worldObj.getTotalWorldTime() % 20L == 0L) {
				checkForCapsules();
				worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				markDirty();
			}

			int energyAmount = capsules * 5;

			if (energyAmount > 0) {
				
				for (int i = 0; i < slots.length; i++) {
				
					if (slots[i] != null && slots[i].getItem() == InitItems.advanced_infused_catalyst) {

						if (slots[i].getItemDamage() >= slots[i].getMaxDamage()) {
							decrStackSize(i, 1);
							break;
						}
					
						else if (EnergyUtil.canAddEnergy(this, energyAmount)) {

							slots[i].setItemDamage(slots[i].getItemDamage() + 1);
							EnergyUtil.addEnergy(this, energyAmount);
						}
						
						break;
					}
				}
			}			
		}
	}

	private Location getCurrentLocation() {
		return new Location(worldObj, xCoord, yCoord, zCoord);
	}

	private void checkForCapsules() {
	
		capsules = 0;

		int dis = 4;
		
		ForgeDirection[] dirs = {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST};
		
		for (ForgeDirection dir : dirs) {
			
			for (int i = 0; i < dis; i++) {			
				
				Location temp = getCurrentLocation().add(dir, i + 1);
				
				if (i < (dis - 1)) {
					
					if (!temp.compareBlocks(InitBlocks.steel_casing)) {
						break;
					}
				}	
				
				else {
					
					if (temp.getTileEntity() instanceof TileEntityCapsuleCore) {

						TileEntityCapsuleCore tileEntity = (TileEntityCapsuleCore) temp.getTileEntity();

						if (tileEntity.isAssembled) {

							capsules++;
						}
					}
				}
			}
		}
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
		return 6;
	}

	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);

		nbt.setInteger("capsules", capsules);
	}

	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);

		capsules = nbt.getInteger("capsules");
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerReactorCore(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiReactorCore(player, this, "Advanced Hydro Reactor");
	}
}

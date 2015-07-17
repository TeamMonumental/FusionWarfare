package calemi.fusionwarfare.tileentity.gen.reactor;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraftforge.common.util.ForgeDirection;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;

public class TileEntityAdvancedHydroReactorCore extends TileEntityBase {

	public int capsules;

	public TileEntityAdvancedHydroReactorCore() {
		maxEnergy = 100000;
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
				
				if (slots[0] != null && slots[0].getItem() == InitItems.advanced_infused_catalyst) {

					if (slots[0].getItemDamage() >= slots[0].getMaxDamage()) {
						decrStackSize(0, 1);
					}

					else if (EnergyUtil.canAddEnergy(this, energyAmount)) {

						slots[0].setItemDamage(slots[0].getItemDamage() + 1);
						EnergyUtil.addEnergy(this, energyAmount);
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
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setInteger("capsules", capsules);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		capsules = nbt.getInteger("capsules");
	}

	// -------------------------------------------------Packets

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound syncData = new NBTTagCompound();

		syncData.setInteger("capsules", capsules);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {

		capsules = pkt.func_148857_g().getInteger("capsules");
	}

	// --------------------------------------------------------------------------------------\\

	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack is, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack is, int side) {
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return false;
	}
}

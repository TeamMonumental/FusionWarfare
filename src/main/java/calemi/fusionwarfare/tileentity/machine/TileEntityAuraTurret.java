package calemi.fusionwarfare.tileentity.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import calemi.fusionwarfare.block.BlockContainerBase;
import calemi.fusionwarfare.entity.DamageSourceTurret;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.IEnergy;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.util.EnergyUtil;

public class TileEntityAuraTurret extends TileEntitySecurity {

	public TileEntityAuraTurret() {
		maxEnergy = 10000;
		maxProgress = 20;
	}
	
	@Override
	public void updateEntity() {

		int energyCost = 500;		
		int range = 5;
		
		if (!isDone() && EnergyUtil.canSubtractEnergy(this, energyCost)) {
			
			progress++;
		}
			
		if (isDone()) {
					
			for (Object o : worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(range, range, range))) {
				
				EntityPlayer player = (EntityPlayer)o;
				
				if (!player.capabilities.isCreativeMode && !isSameTeam(player) && player.hurtResistantTime == 0) {
						
					resetProgress();	
						
					player.attackEntityFrom(new DamageSourceTurret(player, teamName), 12.0F);	
					player.hurtResistantTime = 5;
					EnergyUtil.subtractEnergy(this, energyCost);
				}
			}
			
		}
	}
	
	//----------------------------------------------------------------------------
	
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
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return null;
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		return false;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
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
}

package calemi.fusionwarfare.tileentity.machine;

import java.util.Random;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.entity.EntityMissile;
import calemi.fusionwarfare.item.ItemMissile;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.util.EnergyUtil;
import calemi.fusionwarfare.util.explosion.VelocityEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

public class TileEntityMissileLauncher extends TileEntitySecurity {

	public int targetX;
	public int targetZ;
	public boolean forceLaunch = false;
	
	private int energyCost = 1000;
	private Random rand = new Random();
	
	public TileEntityMissileLauncher() {
		maxEnergy = 5000;
		maxProgress = 100;		
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		
		if (canLaunch()) {

			progress++;
			
			if (worldObj.getTotalWorldTime() % 5L == 0L) {
				worldObj.playSound(xCoord, yCoord, zCoord, "random.fizz", 0.2F, 0.1F, false);
			}		
			
			for (int i = 0; i < progress; i++) {

				double randX = MathHelper.getRandomDoubleInRange(rand, -0.5D, 0.5D);
				double randZ = MathHelper.getRandomDoubleInRange(rand, -0.5D, 0.5D);

				worldObj.spawnParticle("smoke", xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, randX, -0.1D, randZ);	
				
				if (i % 8 == 0) {
					worldObj.spawnParticle("flame", xCoord + 0.5F, yCoord + 0.5F, zCoord + 0.5F, randX, -0.1D, randZ);
				}
			}
		}

		else {
			resetProgress();
		}

		if (isDone()) {

			resetProgress();
			EnergyUtil.subtractEnergy(this, energyCost);

			if (!worldObj.isRemote) {

				EntityMissile entity = new EntityMissile(worldObj, xCoord, yCoord, zCoord, targetX, targetZ, ((ItemMissile) slots[0].getItem()));
				worldObj.spawnEntityInWorld(entity);				
			}
					
			decrStackSize(0, 1);
			
			forceLaunch = false;
		}

	}

	private boolean canLaunch() {
		
		boolean b1 = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
		boolean b2 = worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord);
		boolean b3 = slots[0] != null && EnergyUtil.canSubtractEnergy(this, energyCost);
		
		return (b1 && b2 && b3) || (forceLaunch && b2 && b3);
	}

	public void update() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		worldObj.func_147479_m(xCoord, yCoord, zCoord);
	}

	// ------------------------------------------------------------------------

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return new int[]{0};
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
		return true;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack stack) {
		return stack.getItem() instanceof ItemMissile;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.INPUT;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		nbt.setInteger("targetX", targetX);
		nbt.setInteger("targetZ", targetZ);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		targetX = nbt.getInteger("targetX");
		targetZ = nbt.getInteger("targetZ");
	}

	// -------------------------------------------------Packets

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
}

package calemi.fusionwarfare.tileentity.machine;

import java.util.Random;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.entity.EntityMissile;
import calemi.fusionwarfare.gui.GuiMissileLauncher;
import calemi.fusionwarfare.inventory.ContainerMissileLauncher;
import calemi.fusionwarfare.item.ItemMissile;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.util.explosion.VelocityEvent;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;

public class TileEntityMissileLauncher extends TileEntitySecurity implements ITileEntityGuiHandler {

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
		
		System.out.println(slots[0]);
				
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

		else resetProgress();

		if (isDone()) {

			resetProgress();
			EnergyUtil.subtractEnergy(this, energyCost);

			if (!worldObj.isRemote) {

				EntityMissile entity = new EntityMissile(worldObj, xCoord, yCoord, zCoord, targetX, targetZ, ((ItemMissile) slots[0].getItem()));
				worldObj.spawnEntityInWorld(entity);				
			}
					
			decrStackSize(0, 1);
			
			forceLaunch = false;
			
			update();
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
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(0, 5, 0);
	}

	@Override
	public boolean canInsertItem(int p_102007_1_, ItemStack stack, int p_102007_3_) {
		return true;
	}

	@Override
	public boolean canExtractItem(int p_102008_1_, ItemStack stack, int p_102008_3_) {
		return true;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return stack.getItem() instanceof ItemMissile;
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
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		forceLaunch = nbt.getBoolean("forceLaunch");
		targetX = nbt.getInteger("targetX");
		targetZ = nbt.getInteger("targetZ");
	}

	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		nbt.setBoolean("forceLaunch", forceLaunch);
		nbt.setInteger("targetX", targetX);
		nbt.setInteger("targetZ", targetZ);
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerMissileLauncher(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiMissileLauncher(player, this);
	}
}

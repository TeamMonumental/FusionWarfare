package calemi.fusionwarfare.tileentity.machine;

import java.util.List;
import java.util.Random;

import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.entity.EntityMissile;
import calemi.fusionwarfare.gui.GuiMissileSiloCore;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.inventory.ContainerMissileSiloCore;
import calemi.fusionwarfare.item.ItemMissile;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.tileentity.reactor.TileEntitySteelCasing;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMissileSiloCore extends TileEntitySecurity implements ITileEntityGuiHandler {

	private final ForgeDirection[] dirs = {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST};
	
	Random rand = new Random();
	
	public ForgeDirection direction;
	
	public int currentDelay;
	public int targetX;
	public int targetZ;
	public int energyCost = 5000;
	
	public boolean sprayMode;
	public boolean isAssembled;
	public boolean isBlockPowered;	
	public boolean forceLaunch = false;
	
	public TileEntityMissileSiloCore() {
		maxEnergy = 50000;
		maxProgress = 20;
	}

	@Override
	public void updateEntity() {
					
		maxProgress = 20 + (currentDelay * 20);
		
		if (direction != null) {
			checkStructure(direction);
		}
		
		else {
			
			for (ForgeDirection dir : dirs) {			
			
				Location loc = new Location(worldObj, xCoord, yCoord, zCoord).add(dir, 2);
				
				if (loc.compareBlocks(InitBlocks.steel_casing)) {
					direction = dir;
				}
			}
		}	
		
		if (isAssembled && canLaunch()) {
			progress++;
		}
		
		if (isDone()) {
			
			int missileSlot = findMissile();
			
			resetProgress();
			
			if (missileSlot != -1) {
				
				if (!worldObj.isRemote) {
					
					int randX = 0;
					int randZ = 0;
					
					int range = 25;
					
					if (sprayMode) {
						
						randX = rand.nextInt(range * 2) - range;
						randZ = rand.nextInt(range * 2) - range;
					}
										
					EntityMissile entity = new EntityMissile(worldObj, xCoord + direction.offsetX, yCoord, zCoord + direction.offsetZ, targetX + randX, targetZ + randZ, ((ItemMissile) slots[missileSlot].getItem()));
					entity.motionY = 2;
					worldObj.spawnEntityInWorld(entity);					
				}
				
				EnergyUtil.subtractEnergy(this, energyCost);
				decrStackSize(missileSlot, 1);
				forceLaunch = false;
				update();
			}													
		}
	}
	
	private int findMissile() {
		
		for (int i = 0; i < slots.length; i++) {
			
			if (slots[i] != null) {			
				return i;
			}
		}
	
		return -1;		
	}
	
	private boolean canLaunch() {
		
		boolean b1 = isBlockPowered;
		boolean b2 = worldObj.canBlockSeeTheSky(xCoord + direction.offsetX, yCoord, zCoord + direction.offsetZ);
		boolean b3 = EnergyUtil.canSubtractEnergy(this, energyCost);		
		boolean b4 = false;
		
		for (ItemStack stack : slots) {				
			if (stack != null) {			
				b4 = true;
			}
		}
		
		return (b1 && b2 && b3 && b4) || (forceLaunch && b2 && b3 && b4);
	}
		
	private void checkStructure(ForgeDirection dir) {
		
		Location loc = new Location(worldObj, xCoord + dir.offsetX, yCoord, zCoord + dir.offsetZ);
		
		List<Location> list = ShapeUtil.getCube(worldObj, loc.x, loc.y, loc.z, 1, 2, 1);
			
		int bottomY = loc.y - 2;
			
		boolean assembled = true;
			
		for (Location loc2 : list) {
				
			if (loc2.y != bottomY && loc.x == loc2.x && loc.z == loc2.z) {
				
				if (!loc2.compareBlocks(Blocks.air)) {
						
					assembled = false;
				}
			}
				
			else {
					
				if (!loc2.compareBlocks(InitBlocks.steel_casing, InitBlocks.missile_silo_core)) {
						
					assembled = false;			
				}
			}
		}
		
		isAssembled = assembled;
		direction = dir;
				
		boolean isPowered = false;
		
		for (Location loc2 : list) {
			
			if (loc2.getTileEntity() instanceof TileEntitySteelCasing) {				
					
				TileEntitySteelCasing tileEntity = (TileEntitySteelCasing)loc2.getTileEntity();
				
				if (worldObj.isBlockIndirectlyGettingPowered(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord) || worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
					
					isPowered = true;
				}
				
				if (isAssembled) {
					
					tileEntity.x = xCoord;
					tileEntity.y = yCoord;
					tileEntity.z = zCoord;
				}
					
				else {
					tileEntity.x = 0;
					tileEntity.y = 0;
					tileEntity.z = 0;
				}
			}
		}
		
		isBlockPowered = isPowered;
	}
		
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(2, 2, 2);
	}
	
	@SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0D;
    }
	
	public void update() {
		markDirty();
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return side > 1;
	}

	@Override
	public int getSizeInventory() {
		return 9;
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
		currentDelay = nbt.getInteger("currentDelay");
		targetX = nbt.getInteger("targetX");
		targetZ = nbt.getInteger("targetZ");		
		sprayMode = nbt.getBoolean("sprayMode");
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);	
		nbt.setInteger("currentDelay", currentDelay);
		nbt.setInteger("targetX", targetX);
		nbt.setInteger("targetZ", targetZ);		
		nbt.setBoolean("sprayMode", sprayMode);
	}

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerMissileSiloCore(player, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiMissileSiloCore(player, this);
	}
}
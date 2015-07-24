package calemi.fusionwarfare.tileentity.machine;

import java.util.List;

import net.minecraft.item.ItemStack;
import calemi.fusionwarfare.block.BlockReinforceable;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;

public class TileEntityFusionMatterReinforcer extends TileEntityBase {

	private int energyCost = 50;

	public TileEntityFusionMatterReinforcer() {
		maxEnergy = 5000;
		maxProgress = 50;
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

				resetProgress();
				Location l = findWeakestBlock();

				if (l != null) {

					if (EnergyUtil.canSubtractEnergy(this, energyCost) && reinforceBlock(l)) {
						EnergyUtil.subtractEnergy(this, energyCost);						
						worldObj.playSoundEffect(l.x, l.y, l.z, "mob.zombie.unfect", 1, 1);
					}
				}
			}
		}
	}
	
	private boolean reinforceBlock(Location l) {
		
		BlockReinforceable block = (BlockReinforceable) l.getBlock();
		
		if (l.getBlockMetadata() < (block.maxMeta - 1)) {
			
			l.setBlockMetadata(l.getBlockMetadata() + 1);
			return true;
		}
		
		return false;
	}

	private Location findWeakestBlock() {

		Location tempLoc = null;

		for (Location l : getArea()) {

			if (l.getBlock() instanceof BlockReinforceable) {

				if (tempLoc == null || getMetaSpace(l) > getMetaSpace(tempLoc)) {
					tempLoc = l;
				}
			}
		}

		return tempLoc;
	}

	private int getMetaSpace(Location l) {
		
		return ((BlockReinforceable)l.getBlock()).maxMeta - l.getBlockMetadata();
	}
	
	private List<Location> getArea() {

		int r = 10;
		return ShapeUtil.getCube(worldObj, xCoord, (yCoord + r) - 1, zCoord, r, r, r);
	}

	// -------------------------------------------------------------------------------------------

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {};
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
		return slots[0];
	}
}

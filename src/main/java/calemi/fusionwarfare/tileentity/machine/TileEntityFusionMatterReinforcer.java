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
					
					BlockReinforceable block = (BlockReinforceable) l.getBlock();

					if (l.getBlockMetadata() < (block.maxMeta - 1) && EnergyUtil.canSubtractEnergy(this, energyCost)) {
						EnergyUtil.subtractEnergy(this, energyCost);
						l.setBlockMetadata(l.getBlockMetadata() + 1);
						worldObj.playSoundEffect(l.x, l.y, l.z, "random.anvil_use", 1, 0.5F);
					}
				}
			}
		}
	}

	private Location findWeakestBlock() {

		Location tempLoc = null;

		for (Location l : getArea()) {

			if (l.getBlock() instanceof BlockReinforceable) {

				if (tempLoc == null || l.getBlockMetadata() < tempLoc.getBlockMetadata()) {
					tempLoc = l;
				}
			}
		}

		return tempLoc;
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

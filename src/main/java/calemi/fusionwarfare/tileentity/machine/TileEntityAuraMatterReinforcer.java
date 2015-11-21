package calemi.fusionwarfare.tileentity.machine;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.block.BlockReinforceable;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;

public class TileEntityAuraMatterReinforcer extends TileEntityAuraBase {

	@Override
	public String getName() {
		return "Aura Matter Reinforcer";
	}
	
	@Override
	public int getEnergyCost() {
		return 50;
	}

	@Override
	public int getMaxEnergy() {
		return 10000;
	}

	@Override
	public int getProgressTime() {
		return 50;
	}

	@Override
	public boolean onAction() {
		
		Location l = findWeakestBlock();

		if (l != null) {

			if (reinforceBlock(l)) {
				worldObj.playSoundEffect(l.x, l.y, l.z, "mob.zombie.unfect", 1, 1);
				return true;
			}
		}
		
		return false;
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

		for (Location l : blocksInAura) {

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
}
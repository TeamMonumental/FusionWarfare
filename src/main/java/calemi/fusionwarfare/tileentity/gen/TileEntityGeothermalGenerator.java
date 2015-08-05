package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntityBaseWithNoInv;
import calemi.fusionwarfare.util.EnergyUtil;
import calemi.fusionwarfare.util.Location;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class TileEntityGeothermalGenerator extends TileEntityBaseWithNoInv {

	public TileEntityGeothermalGenerator() {
		maxEnergy = 750;
	}

	@Override
	public void updateEntity() {

		if (!worldObj.isRemote) {
			
			if (worldObj.getTotalWorldTime() % 40L == 0L) {			
				findLava();
			}		
		}
	}

	private void findLava() {

		for (int x = xCoord - 1; x <= xCoord + 1; x++) {
			for (int z = zCoord - 1; z <= zCoord + 1; z++) {
				
				Location location = new Location(worldObj, x, yCoord, z);

				if (location.getBlock() == Blocks.lava && location.getBlockMetadata() == 0 ) {
					EnergyUtil.addEnergy(this, 5);
				}
			}
		}
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}
}
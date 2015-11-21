package calemi.fusionwarfare.tileentity.reactor;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityCapsuleCore extends TileEntityEnergyBase {

	public boolean isAssembled;

	@Override
	public void updateEntity() {

		if (!worldObj.isRemote && worldObj.getTotalWorldTime() % 10L == 0L) {

			isAssembled = isCorrectStructure();

			if (isAssembled) {

				Location bottom = getCurrentLocation().add(0, -3, 0);
				
				for (int i = 0; i < 5; i++) {
					
					Location temp = bottom.add(0, i + 1, 0);
					
					if (i != 2) {
						
						if (temp.compareBlocks(Blocks.air)) {				
							temp.setBlock(Blocks.water, 0);
						}
					}
				}
			}
		}
	}

	private Location getCurrentLocation() {		
		return new Location(worldObj, xCoord, yCoord, zCoord);		
	}
	
	private boolean isCorrectStructure() {

		Location bottom = getCurrentLocation().add(0, -3, 0);
		
		if (!(worldObj.getBiomeGenForCoords(xCoord, zCoord) instanceof BiomeGenOcean) || !hasEnoughWater()) {
			return false;
		}
		
		if (!bottom.compareBlocks(InitBlocks.steel_casing)) {
			return false;
		}
		
		if (!getCurrentLocation().add(0, 3, 0).compareBlocks(InitBlocks.reactor_cooling_unit)) {
			return false;
		}
		
		ForgeDirection[] dirs = {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST};
		
		for (ForgeDirection dir : dirs) {
			
			for (int i = 0; i < 5; i++) {
				
				Location side = bottom.add(ForgeDirection.UP).add(dir).add(0, i, 0);
				
				if (i == 0 || i == 4) {
					
					if (!side.compareBlocks(InitBlocks.steel_casing)) {					
						return false;
					}
				}
				
				if (i == 1 || i == 3) {
					
					if (!side.compareBlocks(InitBlocks.reinforced_glass)) {					
						return false;
					}
				}
				
				if (i == 2) {
					
					if (!side.compareBlocks(InitBlocks.steel_casing, InitBlocks.reinforced_glass)) {					
						return false;
					}					
				}
			}		
		}
		
		for (int i = 0; i < 5; i++) {
			
			Location temp = bottom.add(0, i + 1, 0);
			
			if (i != 2) {
				
				if (!temp.compareBlocks(Blocks.water, Blocks.air)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean hasEnoughWater() {

		for (int y = yCoord + 4; y < (yCoord + 4) + 15; y++) {

			Location loc = new Location(worldObj, xCoord, y, zCoord);

			if (loc.getBlock() != Blocks.water || loc.getBlockMetadata() > 0) {
				return false;
			}
		}

		return true;
	}
	
	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
}
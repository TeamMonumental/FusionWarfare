package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenMesa;

public class TileEntitySolarGenerator extends TileEntityBase {

	public TileEntitySolarGenerator() {
		maxEnergy = 500;
	}

	@Override
	public void updateEntity() {
		
		if (!worldObj.isRemote) {

			if (worldObj.getTotalWorldTime() % 40L == 0L) {

				if (worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord) && !worldObj.isRaining()) {

					int lightValue = worldObj.getSavedLightValue(EnumSkyBlock.Sky, xCoord, yCoord + 1, zCoord) - (worldObj.skylightSubtracted);

					if (lightValue > 12) {
						
						if (worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature > 1.5F) {
							EnergyUtil.addEnergy(this, 10);
						}
						
						EnergyUtil.addEnergy(this, 15);
					}
				}
			}
		}
	}

	// --------------------------------------------------------------------------------

	@Override
	public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
		return new int[]{};
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
		return EnumIO.OUTPUT;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
}

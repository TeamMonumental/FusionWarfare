package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntityBaseWithNoInv;
import calemi.fusionwarfare.util.EnergyUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenMesa;

public class TileEntitySolarGenerator extends TileEntityBaseWithNoInv {

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
							EnergyUtil.addEnergy(this, 5);
						}
						
						EnergyUtil.addEnergy(this, 15);
					}
				}
			}
		}
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}
}
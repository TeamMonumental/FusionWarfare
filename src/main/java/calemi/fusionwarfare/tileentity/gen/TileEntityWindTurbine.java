package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntityBaseWithNoInv;
import calemi.fusionwarfare.util.EnergyUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.biome.BiomeGenOcean;

public class TileEntityWindTurbine extends TileEntityBaseWithNoInv {

	public float speed, rotation;

	public TileEntityWindTurbine() {
		maxEnergy = 750;
	}

	@Override
	public void updateEntity() {

		rotation += speed;

		if (yCoord >= 75 && worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord)) {

			speed = 5F;

			if (worldObj.getTotalWorldTime() % 40L == 0L) {

				if (!worldObj.isRemote) EnergyUtil.addEnergy(this, 30);

				if (worldObj.getBiomeGenForCoords(xCoord, zCoord) instanceof BiomeGenOcean) {
					speed = 8F;
					if (!worldObj.isRemote) EnergyUtil.addEnergy(this, 10);
				}
			}
		}

		else {
			speed = 0;
		}
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}
}
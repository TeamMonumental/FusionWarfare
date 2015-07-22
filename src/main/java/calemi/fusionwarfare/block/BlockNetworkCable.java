package calemi.fusionwarfare.block;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkCable;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockNetworkCable extends BlockBasicMachineBase {

	float pixel = 1F/16F;
	
	public BlockNetworkCable() {
		super("network_cable", TileEntityNetworkCable.class, 0, false);
		setBlockBounds(11 * pixel / 2, 11 * pixel / 2, 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2, 1 - 11 * pixel / 2);
		hasCustomModel = true;
		this.particleImage = "mech_particle";
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float f, float f2, float f3) {		
		return false;			
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

		TileEntityNetworkCable cable = (TileEntityNetworkCable) world.getTileEntity(x, y, z);

		if (cable != null) {

			float minX = 11 * pixel / 2 - (cable.connections[5] != null ? (11 * pixel / 2) : 0);
			float maxX = 1 - 11 * pixel / 2 + (cable.connections[3] != null ? (11 * pixel / 2) : 0);

			float minY = 11 * pixel / 2 - (cable.connections[1] != null ? (11 * pixel / 2) : 0);
			float maxY = 1 - 11 * pixel / 2 + (cable.connections[0] != null ? (11 * pixel / 2) : 0);

			float minZ = 11 * pixel / 2 - (cable.connections[2] != null ? (11 * pixel / 2) : 0);
			float maxZ = 1 - 11 * pixel / 2 + (cable.connections[4] != null ? (11 * pixel / 2) : 0);

			setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}

		return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {

		TileEntityNetworkCable cable = (TileEntityNetworkCable) world.getTileEntity(x, y, z);

		if (cable != null) {

			float minX = 11 * pixel / 2 - (cable.connections[5] != null ? (11 * pixel / 2) : 0);
			float maxX = 1 - 11 * pixel / 2 + (cable.connections[3] != null ? (11 * pixel / 2) : 0);

			float minY = 11 * pixel / 2 - (cable.connections[1] != null ? (11 * pixel / 2) : 0);
			float maxY = 1 - 11 * pixel / 2 + (cable.connections[0] != null ? (11 * pixel / 2) : 0);

			float minZ = 11 * pixel / 2 - (cable.connections[2] != null ? (11 * pixel / 2) : 0);
			float maxZ = 1 - 11 * pixel / 2 + (cable.connections[4] != null ? (11 * pixel / 2) : 0);

			setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}

		return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
	}
}

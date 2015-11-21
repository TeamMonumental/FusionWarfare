package calemi.fusionwarfare.block;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.gui.GuiNetworkBeacon;
import calemi.fusionwarfare.gui.GuiTeamSystem;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkBeacon;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkCable;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class BlockNetworkBeacon extends BlockBasicMachineBase {

	public BlockNetworkBeacon() {
		super("network_beacon", TileEntityNetworkBeacon.class);
		setHasCustomModel();
		setBounds(4, 0, 4, 12, 12, 12);
	}
	
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) || BlockFence.func_149825_a(world.getBlock(x, y - 1, z));
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock) {
		
		boolean flag = false;

		if (!World.doesBlockHaveSolidTopSurface(world, x, y - 1, z)	&& !BlockFence.func_149825_a(world.getBlock(x, y - 1, z))) {
			flag = true;
		}

		if (flag) {
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}
}

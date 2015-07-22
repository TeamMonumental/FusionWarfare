package calemi.fusionwarfare.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockReinforceableGlass extends BlockReinforceable {

	public BlockReinforceableGlass(String imagePath, int maxMeta) {
		super(imagePath, maxMeta);
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int x, int y, int z, int side) {		
		Block block = iBlockAccess.getBlock(x, y, z);
		return block == this ? false : super.shouldSideBeRendered(iBlockAccess, x, y, z, side);
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}	
	
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
		return getBlockColor() - (blockAccess.getBlockMetadata(x, y, z) * 0x303030);
	}
}

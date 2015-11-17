package calemi.fusionwarfare.util;

import net.minecraft.block.BlockLiquid;
import net.minecraft.init.Blocks;

import java.util.Random;

import calemi.fusionwarfare.block.BlockReinforceable;
import calemi.fusionwarfare.entity.EntityBlock;

public class BlockUtil {
	
	private static Random rand = new Random();
	
	public static void spawnGhostBlock(Location loc) {
			
		if (loc.getBlock() != Blocks.air) {	
			
			if (rand.nextInt(1) == 0) {					
				EntityBlock ghostBlock = new EntityBlock(loc.world, loc.x, loc.y, loc.z, loc.getBlock(), loc.getBlockMetadata());
				loc.world.spawnEntityInWorld(ghostBlock);				
			}
			
			loc.setBlockToAir();
		}			
	}
	
	public static void degradeBlock(Location loc, int degrade, boolean breakNormalBlocks, boolean spawnGhostBlocks) {
		
		if (loc.getBlock() instanceof BlockReinforceable) {
			
			if ((loc.getBlockMetadata() - degrade) < 0) {			
				
				if (spawnGhostBlocks) {
					BlockUtil.spawnGhostBlock(loc);		
				}
				
				else loc.world.setBlockToAir(loc.x, loc.y, loc.z);
			}
			
			else {
				loc.setBlockMetadata(loc.getBlockMetadata() - degrade);
			}
		}
		
		else if (breakNormalBlocks && loc.getTileEntity() == null && !loc.isBlockUnbreakable() && !(loc.getBlock() instanceof BlockLiquid)) BlockUtil.spawnGhostBlock(loc);	
	}
}

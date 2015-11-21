package calemi.fusionwarfare.multiblock;

import calemi.fusionwarfare.util.Location;
import net.minecraft.block.Block;

public class CheckPoint {

	public int xOffset, yOffset, zOffset;
	
	public Block[] blocks;
	
	public Location master;
	
	public CheckPoint(Location master, Block... blocks) {		
		this.master = master;
		this.blocks = blocks;
	}
	
	public Location getRealLocation() {		
		return master.add(xOffset, yOffset, zOffset);		
	}
	
	public boolean checkBlock() {		
		return getRealLocation().compareBlocks(blocks);		
	}
}

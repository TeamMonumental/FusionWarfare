package calemi.fusionwarfare.util;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Location {

	public int x, y, z;
			
	public World world;
	
	public Location(World world, int x, int y, int z) {	
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;		
	}
	
	public boolean compareBlocks(Block... blocks) {
		
		for (Block block : blocks) {
			
			if (block == getBlock()) {			
				
				return true;				
			}
		}
		
		return false;
	}
	
	public Location(World world) {		
		this(world, 0, 0, 0);
	}
	
	public Block getBlock() {
		return world.getBlock(x, y, z);
	}
	public void setBlock(Block block, int metadata) {
		world.setBlock(x, y, z, block, metadata, 2);
	}
	
	public boolean isBlockUnbreakable() {
		return world.getBlock(x, y, z).getBlockHardness(world, x, y, z) < 0;
	}
	
	public int getBlockMetadata() {
		return world.getBlockMetadata(x, y, z);
	}
	
	public TileEntity getTileEntity() {
		return world.getTileEntity(x, y, z);
	}
	
	public List<ItemStack> getDrops() {
		
		return getBlock().getDrops(world, x, y, z, getBlockMetadata(), 0);		
	}
	
	public void breakBlock() {
		world.setBlockToAir(x, y, z);
	}

	public void setBlockMetadata(int meta) {
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}
	
	public Location add(int x, int y, int z) {		
		return new Location(world, this.x + x, this.y + y, this.z + z);	
	}
	
	public Location add(ForgeDirection dir) {		
		return new Location(world, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);	
	}
	
	public Location add(ForgeDirection dir, int distance) {	
		
		Location loc = new Location(world, x, y, z);

		loc.x += dir.offsetX * distance;
		loc.y += dir.offsetY * distance;
		loc.z += dir.offsetZ * distance;
		
		return loc;	
	}
}
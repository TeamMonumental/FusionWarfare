package calemi.fusionwarfare.util;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
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
	
	public Location(TileEntity entity) {
		world = entity.getWorldObj();
		x = entity.xCoord;
		y = entity.yCoord;
		z = entity.zCoord; 
	}
	
	public Location(World world) {		
		this(world, 0, 0, 0);
	}
	
	public boolean compareBlocks(Block... blocks) {
		
		for (Block block : blocks) {
			
			if (block == getBlock()) {			
				
				return true;				
			}
		}
		
		return false;
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
	
	public void setBlockToAir() {
		world.setBlockToAir(x, y, z);
	}

	public void setBlockMetadata(int meta) {
		world.setBlockMetadataWithNotify(x, y, z, meta, 2);
	}
	
	public Location add(int xa, int ya, int za) {		
		return new Location(world, x + xa, y + ya, z + za);	
	}
	
	public Location add(ForgeDirection dir) {		
		return add(dir.offsetX, dir.offsetY, dir.offsetZ);	
	}
	
	public Location add(ForgeDirection dir, int distance) {	
		return add(dir.offsetX * distance, dir.offsetY * distance, dir.offsetZ * distance);	
	}
	
	public EntityItem dropItem(ItemStack stack) {
		EntityItem entityItem = new EntityItem(world, x, y, z, stack);
		world.spawnEntityInWorld(entityItem);
		return entityItem;
	}
	
	public void breakBlock() {
		
		List<ItemStack> drops = getDrops();
		
		for (ItemStack is : drops) {
			dropItem(is);
		}
		
		TileEntity entity = getTileEntity();
		
		if (entity != null && entity instanceof IInventory) {
			
			IInventory inv = (IInventory) entity;
			
			for (int i = 0 ; i < inv.getSizeInventory(); i++) {
				
				dropItem(inv.getStackInSlot(i));
			}
		}
		
		setBlockToAir();
	}
}
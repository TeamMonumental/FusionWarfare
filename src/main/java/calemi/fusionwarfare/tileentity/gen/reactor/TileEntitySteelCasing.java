package calemi.fusionwarfare.tileentity.gen.reactor;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import calemi.fusionwarfare.block.BlockContainerBase;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.IEnergy;
import calemi.fusionwarfare.tileentity.TileEntityBase;

public class TileEntitySteelCasing extends TileEntity implements IEnergy {

	public int x, y, z;
	
	public TileEntityBase getMaster() {
		
		TileEntity entity = worldObj.getTileEntity(x, y, z);
		
		if (entity instanceof TileEntityBase) {
			return (TileEntityBase) entity;	
		}
		
		return null;
	}
	
	@Override
	public void updateEntity() {
		
		if (!(worldObj.getBlock(x, y, z) instanceof BlockContainerBase)) {
			
			x = 0;
			y = 0;
			z = 0;
		}
	}
	
	@Override
	public int getEnergy() {
		return getMaster() == null ? 0 : getMaster().getEnergy();
	}

	@Override
	public void setEnergy(int energy) {
		if (getMaster() != null) getMaster().setEnergy(energy);
	}

	@Override
	public int getMaxEnergy() {
		return getMaster() == null ? 0 : getMaster().getMaxEnergy();
	}

	@Override
	public EnumIO getIOType() {
		return getMaster() == null ? EnumIO.NONE : getMaster().getIOType();
	}
}

package calemi.fusionwarfare.tileentity.reactor;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.api.IEnergy;
import calemi.fusionwarfare.tileentity.TileEntityBase;

public class TileEntityReactorCasing extends TileEntity implements IEnergy {

	public int x, y, z;
	
	public TileEntityBase getMaster() {
		
		TileEntity entity = worldObj.getTileEntity(x, y, z);
		
		if (entity instanceof TileEntityBase) {
			return (TileEntityBase) entity;	
		}
		
		return null;
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
		return EnumIO.OUTPUT;
	}
}

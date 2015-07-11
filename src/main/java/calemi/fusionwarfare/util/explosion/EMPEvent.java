package calemi.fusionwarfare.util.explosion;

import java.util.Random;

import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkCable;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EMPEvent extends BlastEvent {

	public EMPEvent(int tier) {
		super(tier);
	}

	@Override
	public void detonate(World world, int x, int y, int z) {

		Random rand = new Random();

		int r = getRange();

		for (Location loc : ShapeUtil.getSphere(world, x, y, z, r)) {
			
			if (loc.getTileEntity() instanceof TileEntityBase) {
				
				((TileEntityBase)loc.getTileEntity()).energy = 0;
			}			
		}	
	}

	public int getRange() {
		return 4 + ((tier - 1) * 2);
	}
}

package calemi.fusionwarfare.util.explosion;

import net.minecraft.world.World;
import calemi.fusionwarfare.util.BlockUtil;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;

public class BreachingEvent extends BlastEvent {

	public BreachingEvent(int tier) {
		super(tier);
	}

	@Override
	public void detonate(World world, int x, int y, int z) {

		int r = 5;
		int d = 1 + tier;
		
		for (Location loc : ShapeUtil.getSphere(world, x, y, z, r)) {		
			
			BlockUtil.degradeBlock(loc, d, true, true);			
		}
	}
}

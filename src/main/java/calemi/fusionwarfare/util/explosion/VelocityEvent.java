package calemi.fusionwarfare.util.explosion;

import java.util.Random;

import net.minecraft.world.World;
import calemi.fusionwarfare.util.BlockUtil;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;

public class VelocityEvent extends BlastEvent {

	Random rand = new Random();

	public VelocityEvent(int tier) {
		super(tier);
	}

	@Override
	public void detonate(World world, int x, int y, int z) {

		int r = getRange();
		int h = 5;

		for (Location loc : ShapeUtil.getSphere(world, x, y, z, r)) {

			BlockUtil.degradeBlock(loc, 1, true, true);		
		}
	}
	
	public int getRange() {
		return 4 + ((tier - 1) * 2);
	}
}

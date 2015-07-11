package calemi.fusionwarfare.util.explosion;

import java.util.Random;

import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;

public class PyroBlastEvent extends BlastEvent {

	Random rand = new Random();
	
	public PyroBlastEvent() {
		super(2);
	}

	@Override
	public void detonate(World world, int x, int y, int z) {

		//for (Location loc : ShapeUtil.getSphere(world, x, y, z, 20)) {
		for (Location loc : ShapeUtil.getCube(world, x, y - 20, z, 10, 30, 10)) {	
		
			if (hasSpace(loc) && rand.nextInt(150) == 0) {
				
				EntityBlaze entity = new EntityBlaze(world);
				entity.setPositionAndUpdate(loc.x, loc.y, loc.z);
				world.spawnEntityInWorld(entity);
			}
		}
	}
	
	private boolean hasSpace(Location loc) {
		
		return loc.getBlock() == Blocks.air && loc.add(0, 1, 0).getBlock() == Blocks.air;
	}
}

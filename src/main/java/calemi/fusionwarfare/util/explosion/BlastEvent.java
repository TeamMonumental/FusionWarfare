package calemi.fusionwarfare.util.explosion;

import net.minecraft.world.World;

public abstract class BlastEvent {

	public int tier;
	
	public BlastEvent(int tier) {
		this.tier = tier;
	}
	
	public abstract void detonate(World world, int x, int y, int z);
}

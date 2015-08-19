package calemi.fusionwarfare.util.explosion;

import net.minecraft.world.World;

public abstract class GrenadeBlastEvent {

	public abstract void explode(World world, int x, int y, int z);
}

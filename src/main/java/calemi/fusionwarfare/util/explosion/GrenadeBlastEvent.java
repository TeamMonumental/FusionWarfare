package calemi.fusionwarfare.util.explosion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class GrenadeBlastEvent {

	public abstract String getTextureName();
	
	public abstract void explode(World world, EntityPlayer shooter, int x, int y, int z);
}

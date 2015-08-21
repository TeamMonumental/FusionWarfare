package calemi.fusionwarfare.util.explosion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ChemicalGrenadeBlastEvent extends GrenadeBlastEvent {
	
	@Override
	public String getTextureName() {
		return "chemical";
	}
	
	@Override
	public void explode(World world, EntityPlayer shooter, int x, int y, int z) {

	}
}

package calemi.fusionwarfare.util.explosion;

import java.util.Random;

import net.minecraft.world.World;
import calemi.fusionwarfare.Reference;

public class DudleyEvent extends BlastEvent {

	Random rand = new Random();
	
	public DudleyEvent() {
		super(1);
	}

	@Override
	public void detonate(World world, int x, int y, int z) {

		world.playSoundEffect(x, y, z, Reference.MOD_ID + ":fart", 1F, rand.nextFloat() * 0.4F + 0.8F);
	}
}

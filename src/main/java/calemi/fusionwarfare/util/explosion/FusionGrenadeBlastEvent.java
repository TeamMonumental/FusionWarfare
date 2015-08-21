package calemi.fusionwarfare.util.explosion;

import java.util.Random;

import calemi.fusionwarfare.entity.DamageSourceCustom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class FusionGrenadeBlastEvent extends GrenadeBlastEvent {

	private final float range = 4;

	@Override
	public String getTextureName() {
		return "fusion";
	}

	@Override
	public void explode(World world, EntityPlayer shooter, int x, int y, int z) {

		if (!world.isRemote) {

			AxisAlignedBB box = AxisAlignedBB.getBoundingBox(x - range, y - range, z - range, x + range, y + range, z + range);

			for (Object o : world.getEntitiesWithinAABB(EntityLivingBase.class, box)) {

				EntityLivingBase entity = (EntityLivingBase) o;

				if (entity.getDistance(x, y, z) <= range) {

					if (entity instanceof EntityPlayer) {
						entity.attackEntityFrom(new DamageSourceCustom(((EntityPlayer) entity).getDisplayName() + " was disintegrated by " + shooter.getDisplayName() + "'s Fusion Grenade"), (float) ((range - entity.getDistance(x, y, z)) * 3));
					}

					else {
						entity.attackEntityFrom(DamageSource.generic, (float) ((range - entity.getDistance(x, y, z)) * 3));
					}
				}
			}
		}

		world.playSound(x, y, z, "mob.zombie.remedy", 1, 1.2F, true);
		world.spawnParticle("hugeexplosion", x, y, z, 1.0D, 0.0D, 0.0D);
	}
}

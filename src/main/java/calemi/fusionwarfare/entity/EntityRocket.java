package calemi.fusionwarfare.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntityRocket extends EntityThrowable {

	public EntityRocket(World world) {
		super(world);
	}

	public EntityRocket(World world, EntityLivingBase entity) {
		super(world);

		setSize(0.5F, 0.5F);

		posX = entity.posX;
		posY = entity.posY + 1.5;
		posZ = entity.posZ;

		rotationPitch = entity.rotationPitch;
		rotationYaw = entity.rotationYaw;

		motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);
		motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);
		motionY = (double) (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI) * 2);
	
		motionX *= 2;
		motionY *= 2;
		motionZ *= 2;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (ticksExisted > 20 * 60) {			
			setDead();			
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0;
	}
	
	@Override
	protected void onImpact(MovingObjectPosition mop) {

		if (mop.typeOfHit == MovingObjectType.BLOCK) {
			
			worldObj.createExplosion(getThrower(), posX, posY, posZ, 5, false);		
		}
	}
}

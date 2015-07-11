package calemi.fusionwarfare.entity;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityFusionBullet extends EntityThrowable {

	private int damage;
	private float gravityVelocity;
	private Entity shooter;

	public EntityFusionBullet(World world) {
		super(world);
	}

	public EntityFusionBullet(World world, EntityLivingBase entity, int damage, int accuracy, float gravityVelocity) {
		super(world);

		this.damage = damage;
		this.gravityVelocity = gravityVelocity;
		
		setSize(0.5F, 0.5F);

		posX = entity.posX;
		posY = entity.posY + 1.5;
		posZ = entity.posZ;

		float randomPitch = accuracy == 0 ? 0 : rand.nextInt(accuracy * 2) - accuracy;
		float randomYaw = accuracy == 0 ? 0 : rand.nextInt(accuracy * 2) - accuracy;
		
		rotationPitch = entity.rotationPitch + randomPitch;
		rotationYaw = entity.rotationYaw + randomYaw;

		motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);
		motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);
		motionY = (double) (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI) * 2);
	
		motionX *= 2;
		motionY *= 2;
		motionZ *= 2;
	}

	protected void onImpact(MovingObjectPosition mop) {
		
		if (mop.entityHit != null && mop.entityHit != shooter) {
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);			
			setDead();
		}
		
		int x = mop.blockX;
		int y = mop.blockY;
		int z = mop.blockZ;
		
		Block block = worldObj.getBlock(x, y, z);
			
		if (!worldObj.isRemote) {
						
			if (block.getMaterial() == Material.glass) {
				worldObj.setBlockToAir(x, y, z);
			}
			
			setDead();
		}
	}
	
	protected float getGravityVelocity() {
		return gravityVelocity;
	}
}
package calemi.fusionwarfare.entity;

import java.util.List;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
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

public class EntityFusionBullet extends EntityThrowable implements IEntityAdditionalSpawnData {

	private int damage;
	private float gravityVelocity;
	private int hurtTime;
	private EntityPlayer shooter;

	public EntityFusionBullet(World world) {
		super(world);
	}

	public EntityFusionBullet(World world, EntityPlayer player, int damage, int accuracy, float gravityVelocity, int hurtTime) {
		super(world, player);

		this.damage = damage;
		this.gravityVelocity = gravityVelocity;
		this.hurtTime = hurtTime;
		
		setSize(0.5F, 0.5F);

		this.shooter = player;
		
		float randomPitch = accuracy == 0 ? 0 : rand.nextInt(accuracy * 2) - accuracy;
		float randomYaw = accuracy == 0 ? 0 : rand.nextInt(accuracy * 2) - accuracy;
		
		rotationPitch = player.rotationPitch + randomPitch;
		rotationYaw = player.rotationYaw + randomYaw;

		motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);
		motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * 2);
		motionY = (double) (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI) * 2);
	
		motionX *= 2;
		motionY *= 2;
		motionZ *= 2;
	}

	protected void onImpact(MovingObjectPosition mop) {
		
		if (mop.entityHit != null && mop.entityHit != shooter) {
			
			if (mop.entityHit instanceof EntityPlayer) {
				mop.entityHit.attackEntityFrom(new DamageSourceCustom(((EntityPlayer)mop.entityHit).getDisplayName() + " was pummeled by Fusion Bullets from " + shooter.getDisplayName()), damage);
			}
			
			else {
				mop.entityHit.attackEntityFrom(DamageSource.magic, damage);
			}	
			
			if (hurtTime > 0) mop.entityHit.hurtResistantTime = hurtTime;
						
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
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		
		shooter = worldObj.getPlayerEntityByName(nbt.getString("shooter"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		
		if (shooter != null) nbt.setString("shooter", shooter.getDisplayName());
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		readEntityFromNBT(ByteBufUtils.readTag(additionalData));
	}
	
	@Override
	public void writeSpawnData(ByteBuf buffer) {
		
		NBTTagCompound nbt = new NBTTagCompound();
		
		writeEntityToNBT(nbt);
		
		ByteBufUtils.writeTag(buffer, nbt);
	}
}
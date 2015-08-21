package calemi.fusionwarfare.entity;

import calemi.fusionwarfare.item.ItemGrenade;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGrenade extends Entity implements IEntityAdditionalSpawnData {

	private EntityPlayer shooter;
	public Item grenade;

	private float bounce = 0.5F;

	public EntityGrenade(World world) {
		super(world);
	}

	public EntityGrenade(World world, EntityPlayer shooter, Item grenade) {
		super(world);

		this.shooter = shooter;
		this.grenade = grenade;

		setPosition(shooter.posX, shooter.posY + 1.5F, shooter.posZ);

		rotationPitch = shooter.rotationPitch;
		rotationYaw = shooter.rotationYaw;

		float speed = 1.5F;

		motionX = (double) (-MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * speed);
		motionZ = (double) (MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI) * speed);
		motionY = (double) (-MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI) * speed);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		worldObj.spawnParticle("smoke", posX, posY + 0.5F, posZ, 0, 0, 0);

		if (ticksExisted % 5 == 0) {
			worldObj.playSound(posX, posY, posZ, "random.fizz", 1, 1, true);
		}

		if (ticksExisted > 60) {

			((ItemGrenade) grenade).event.explode(worldObj, shooter, (int) posX, (int) posY, (int) posZ);

			setDead();
		}

		if (onGround) {

			motionX /= 1.5F;
			motionZ /= 1.5F;

			motionY = 0;

			motionY += bounce;

			bounce /= 2;

			onGround = false;
			isAirBorne = true;
		}

		else {
			motionY -= 0.1F;
		}

		moveEntity(motionX, motionY, motionZ);
	}

	@Override
	protected void entityInit() {
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {

		shooter = (EntityPlayer) worldObj.getEntityByID(nbt.getInteger("shooter"));
		grenade = Item.getItemById(nbt.getInteger("grenade"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {

		if (shooter != null)
			nbt.setInteger("shooter", shooter.getEntityId());
		if (grenade != null)
			nbt.setInteger("grenade", Item.getIdFromItem(grenade));
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		NBTTagCompound nbt = new NBTTagCompound();
		writeEntityToNBT(nbt);
		ByteBufUtils.writeTag(buffer, nbt);
	}

	@Override
	public void readSpawnData(ByteBuf buffer) {

		readEntityFromNBT(ByteBufUtils.readTag(buffer));
	}
}

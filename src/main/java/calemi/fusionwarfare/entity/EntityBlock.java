package calemi.fusionwarfare.entity;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBlock extends EntityThrowable implements IEntityAdditionalSpawnData {

	public Block block;
	public int meta;
	public int deathTime = rand.nextInt(20 * 3) + 20;
	
	public EntityBlock(World world) {
		super(world);
	}

	public EntityBlock(World world, int x, int y, int z, Block block, int meta) {
		super(world);

		setSize(0.2F, 0.2F);

		this.posX = x;
		this.posY = y;
		this.posZ = z;

		this.motionY = 0.5F;
		
		this.block = block;
		this.meta = meta;
	}

	@Override
	protected float getGravityVelocity() {
		return 0;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		if (ticksExisted > deathTime) setDead(); 
	}
	
	/*private void setBlock() {

		if (!worldObj.isRemote) {

			int x = MathHelper.floor_double(posX);
			int y = MathHelper.floor_double(posY);
			int z = MathHelper.floor_double(posZ);

			worldObj.setBlock(x, y, z, block, meta, 2);

			if (!worldObj.getBlock(x, y - 1, z).isNormalCube()) {
				worldObj.setBlockToAir(x, y, z);
				worldObj.setBlock(x, y - 1, z, block, meta, 2);
			}
		}

		setDead();
	}*/

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		block = Block.getBlockById(nbt.getInteger("block"));
		meta = nbt.getInteger("meta");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("block", Block.getIdFromBlock(block));
		nbt.setInteger("meta", meta);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		ByteBufUtils.writeTag(buffer, nbt);
	}

	@Override
	public void readSpawnData(ByteBuf additionalData) {
		NBTTagCompound nbt = ByteBufUtils.readTag(additionalData);
		readFromNBT(nbt);
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {

		/*if (ticksExisted > 20) {
			setBlock();
		}*/
	}
}

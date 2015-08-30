package calemi.fusionwarfare.entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import calemi.fusionwarfare.item.ItemMissile;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;

public class EntityMissile extends Entity implements IEntityAdditionalSpawnData {

	public ItemMissile missileType;
	
	private int targetX;
	private int targetZ;
	private boolean canExplode;
	
	public EntityMissile(World world) {
		super(world);
	}

	public EntityMissile(World world, int x, int y, int z, int targetX, int targetZ, ItemMissile missileType) {
		super(world);
		this.missileType = missileType;
		setPosition(x + 0.5, y + 0.1F, z + 0.5F);
		this.targetX = targetX;
		this.targetZ = targetZ;		
		canExplode = false;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		
		noClip = !canExplode;
		
		moveEntity(motionX, motionY, motionZ);
		
		for (int i = 0; i < 10; i++) {

			double randX = MathHelper.getRandomDoubleInRange(rand, -0.1D, 0.1D);
			double randZ = MathHelper.getRandomDoubleInRange(rand, -0.1D, 0.1D);

			worldObj.spawnParticle("smoke", posX, (posY + -motionY) + 0.4F, posZ, randX, 0, randZ);				
			if (i % 8 == 0) worldObj.spawnParticle("flame", posX, (posY + -motionY) + 0.4F, posZ, randX, 0, randZ);
		}
		
		if (!worldObj.isRemote) {
			
			if (worldObj.getTotalWorldTime() % 5L == 0L) {
				
				if (!canExplode) {
					worldObj.playSoundAtEntity(this, "random.fizz", 0.2F, 0.1F);
				}
				
				else {
					worldObj.playSoundAtEntity(this, "random.fizz", 5F, 0.1F);
				}
			}
			
			if (!canExplode && motionY < 3) {
				if (motionY < 0.3) motionY += 0.005F;
				else motionY += 0.2F;
			}
			
			if (posY > 300) {
				
				setPosition(targetX + 0.5F, 300, targetZ + 0.5F);
				motionY = -2;
				canExplode = true;
			}
			
			if (canExplode && onGround) {
				missileType.missileType.event.detonate(worldObj, (int)posX, (int)posY, (int)posZ);
				setDead();
			}
		}
	}
		
	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tag) {
		missileType = (ItemMissile) Item.getItemById(tag.getInteger("type"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tag) {
		tag.setInteger("type", Item.getIdFromItem(missileType));
	}

	@Override
	public void writeSpawnData(ByteBuf buffer) {
		NBTTagCompound tag = new NBTTagCompound();
		writeEntityToNBT(tag);
		ByteBufUtils.writeTag(buffer, tag);
	}

	@Override
	public void readSpawnData(ByteBuf buffer) {		
		readEntityFromNBT(ByteBufUtils.readTag(buffer));
	}
}

package calemi.fusionwarfare.entity;

import java.util.List;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.item.tool.ItemDesignator;
import calemi.fusionwarfare.packet.ClientPacketHandler;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileSiloCore;
import calemi.fusionwarfare.util.EnergyUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityDesignatorOrb extends EntityThrowable {

	private Entity shooter;

	public EntityDesignatorOrb(World world) {
		super(world);
	}

	public EntityDesignatorOrb(World world, EntityPlayer player) {
		super(world, player);

		setSize(0.5F, 0.5F);

		rotationPitch = player.rotationPitch;
		rotationYaw = player.rotationYaw;
		
		shooter = player;

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

	protected void onImpact(MovingObjectPosition mop) {
		
		int x = mop.blockX;
		int y = mop.blockY;
		int z = mop.blockZ;
		
		if (!worldObj.isRemote) {
					
			if (!worldObj.isAirBlock(x, y, z)) {
				
				EntityPlayer player = (EntityPlayer)shooter;
				
				if (player.inventory.getCurrentItem() != null) {
				
					ItemStack stack = player.inventory.getCurrentItem();
				
					if (stack != null && stack.getItem() instanceof ItemDesignator) {	
					
						ItemDesignator item = (ItemDesignator) stack.getItem();
					
						int unitX = item.getNBT(stack).getInteger("unitX");
						int unitY = item.getNBT(stack).getInteger("unitY");
						int unitZ = item.getNBT(stack).getInteger("unitZ");
					
						TileEntity tileEntity = worldObj.getTileEntity(unitX, unitY, unitZ);
						
						if (tileEntity != null && tileEntity instanceof TileEntityMissileLauncher) {
						
							TileEntityMissileLauncher launcher = (TileEntityMissileLauncher) tileEntity;
						
							launcher.targetX = x;
							launcher.targetZ = z;
									
							if (launcher.getStackInSlot(0) != null && EnergyUtil.canSubtractEnergy(launcher, 1000)) {
								worldObj.playSoundEffect(x, y, z, "mob.wither.spawn", 50, 1);
								FusionWarfare.network.sendTo(new ClientPacketHandler("force.launch%" + launcher.xCoord + "%" + launcher.yCoord + "%" + launcher.zCoord), (EntityPlayerMP) player);
								launcher.forceLaunch = true;
							}
							
							else if (!EnergyUtil.canSubtractEnergy(launcher, 1000)) {
								player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Missile Unit out of energy!"));				
							}
							
							else player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "No Missiles in Missile Unit!"));													
						}
						
						if (tileEntity != null && tileEntity instanceof TileEntityMissileSiloCore) {
							
							TileEntityMissileSiloCore launcher = (TileEntityMissileSiloCore) tileEntity;
						
							launcher.targetX = x;
							launcher.targetZ = z;
							
							boolean hasStack = false;
							
							for (ItemStack slotStack : launcher.slots) {				
								
								if (slotStack != null) {			
									hasStack = true;
								}
							}
							
							if (hasStack && EnergyUtil.canSubtractEnergy(launcher, 1000)) {
								worldObj.playSoundEffect(x, y, z, "mob.wither.spawn", 50, 1);
								launcher.forceLaunch = true;
							}
							
							else if (!EnergyUtil.canSubtractEnergy(launcher, 1000)) {
								player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Missile Unit out of energy!"));				
							}						
						
							else player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "No Missiles in Missile Unit!"));
						}
					}
				
					else {
					
						player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Could not send coords to held item!"));					
					}
				}
				
				else {
					
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Could not send coords to held item!"));					
				}
			}	
			
			setDead();
		}
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		
		shooter = worldObj.getPlayerEntityByName(nbt.getString("shooter"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		
		nbt.setString("shooter", ((EntityPlayer)shooter).getDisplayName());
	}
}
package calemi.fusionwarfare.item;

import java.util.List;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.entity.EntityFusionBullet;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.init.InitItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemFusionGun extends ItemBase {

	private int ammoCost;
	private int hitDamage;
	private int accuracy;
	private float gravityVelocity;
	private boolean isSniper;

	public ItemFusionGun(String imagePath, int reloadTime, int ammoCost, int hitDamage, int accuracy, float gravityVelocity, boolean isSniper) {
		super(imagePath, false, false);
		setMaxStackSize(1);
		setCreativeTab(InitCreativeTabs.creativeTabInfantry);
		setMaxDamage(reloadTime);
		this.ammoCost = ammoCost;
		this.hitDamage = hitDamage;
		this.gravityVelocity = gravityVelocity;
		this.accuracy = accuracy;
		this.isSniper = isSniper;
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		if (is.getItemDamage() > 0) list.add(EnumChatFormatting.RED + "Reloading: " + getCurrentProgress(is) * 100 / getMaxDamage() + "%");
	}

	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack is) {

		if (isSniper) {

			if (getNBT(is).getFloat("Scoping") == 0) {
				getNBT(is).setFloat("Scoping", 1);
			}

			else if (getNBT(is).getFloat("Scoping") == 1) {
				getNBT(is).setFloat("Scoping", 2);
			}

			else {
				getNBT(is).setFloat("Scoping", 0);
			}
		}

		return true;
	}

	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int i1, boolean b) {

		if (!world.isRemote) {

			if (is != ((EntityPlayer) entity).inventory.getCurrentItem()) {
				getNBT(is).setFloat("Scoping", 0);
			}

			if (getCurrentProgress(is) < getMaxDamage()) {
				is.damageItem(-1, (EntityLivingBase) entity);
			}
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {

		shootBullet(world, is, player);
		return is;
	}

	public int getCurrentProgress(ItemStack is) {
		return getMaxDamage() - is.getItemDamage();
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	public void shootBullet(World world, ItemStack is, EntityLivingBase entity) {
		
		if (((EntityPlayer)entity).capabilities.isCreativeMode) {
					
			world.playSoundAtEntity(entity, Reference.MOD_ID + ":gun_shot", 1, 1);

			if (!world.isRemote) {

				for (int i = 0; i < ammoCost; i++) {
					world.spawnEntityInWorld(new EntityFusionBullet(world, entity, hitDamage, accuracy, gravityVelocity));
				}
			}
		}
					
		else {
		
			if (getCurrentProgress(is) == getMaxDamage()) {

				for (ItemStack slot : ((EntityPlayer) entity).inventory.mainInventory) {
					
					if (slot != null && slot.getItem() == InitItems.fusion_ammo && slot.stackSize >= ammoCost) {
						
						for (int i = 0; i < ammoCost; i++) {
							((EntityPlayer) entity).inventory.consumeInventoryItem(InitItems.fusion_ammo);
						}

						world.playSoundAtEntity(entity, Reference.MOD_ID + ":gun_shot", 1, 1);

						if (!world.isRemote) {

							for (int i = 0; i < ammoCost; i++) {
								world.spawnEntityInWorld(new EntityFusionBullet(world, entity, hitDamage, accuracy, gravityVelocity));
							}

							is.damageItem(getMaxDamage(), entity);
						}
					}

					break;					
				}			
			}
		}		
	}
}

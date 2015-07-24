package calemi.fusionwarfare.util;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.entity.EntityFusionBullet;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.ItemFusionGun;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GunUtil {

	private static int getCurrentProgress(ItemStack is) {
		return is.getMaxDamage() - is.getItemDamage();
	}
	
	public static void shootBullet(World world, ItemStack is, EntityPlayer player) {
		
		ItemFusionGun item = (ItemFusionGun)is.getItem();
		
		int ammoCost = item.ammoCost;
		int hitDamage = item.hitDamage;
		int accuracy = item.accuracy;
		float gravityVelocity = item.gravityVelocity;
		
		if (player.capabilities.isCreativeMode) {
					
			world.playSoundAtEntity(player, Reference.MOD_ID + ":gun_shot", 1, 1);

			if (!world.isRemote) {

				for (int i = 0; i < ammoCost; i++) {
					world.spawnEntityInWorld(new EntityFusionBullet(world, player, hitDamage, accuracy, gravityVelocity));
				}
			}
		}
					
		else {
		
			if (getCurrentProgress(is) == is.getMaxDamage()) {
				
				for (ItemStack slot : player.inventory.mainInventory) {
					
					if (slot != null && slot.getItem() == InitItems.fusion_ammo && slot.stackSize >= ammoCost) {
						
						for (int i = 0; i < ammoCost; i++) {
							player.inventory.consumeInventoryItem(InitItems.fusion_ammo);
						}

						world.playSoundAtEntity(player, Reference.MOD_ID + ":gun_shot", 1, 1);

						if (!world.isRemote) {

							for (int i = 0; i < ammoCost; i++) {
								world.spawnEntityInWorld(new EntityFusionBullet(world, player, hitDamage, accuracy, gravityVelocity));
							}

							is.damageItem(is.getMaxDamage(), player);
						}
						
						break;
					}									
				}			
			}
		}		
	}	
}

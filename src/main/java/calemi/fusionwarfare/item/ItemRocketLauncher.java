package calemi.fusionwarfare.item;

import java.util.List;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.entity.EntityFusionBullet;
import calemi.fusionwarfare.entity.EntityRocket;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.init.InitItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemRocketLauncher extends ItemBase {

	public ItemRocketLauncher() {
		super("rocket_launcher", false, false);
		setCreativeTab(InitCreativeTabs.creativeTabInfantry);
		setMaxDamage(200);
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		if (is.getItemDamage() > 0) list.add(EnumChatFormatting.RED + "Reloading: " + getCurrentProgress(is) * 100 / getMaxDamage() + "%");
	}
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int i1, boolean b) {

		if (!world.isRemote) {

			if (getCurrentProgress(is) < getMaxDamage()) {
				is.damageItem(-1, (EntityLivingBase) entity);
			}
		}
	}
	
	public int getCurrentProgress(ItemStack is) {
		return getMaxDamage() - is.getItemDamage();
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p) {

		if (p.capabilities.isCreativeMode) {
			
			w.playSoundAtEntity(p, "mob.ghast.fireball", 1, 0.5F);
			
			if (!w.isRemote) {			
				
				EntityRocket entity = new EntityRocket(w, p);		
				w.spawnEntityInWorld(entity);
			
				is.damageItem(getMaxDamage(), p);
			}
		} 
		
		else {
			
			if (getCurrentProgress(is) == getMaxDamage()) {

				for (ItemStack slot : p.inventory.mainInventory) {

					if (slot != null && slot.getItem() == InitItems.rocket) {
						
						p.inventory.consumeInventoryItem(InitItems.rocket);
					
						if (!w.isRemote) {
						
							w.playSoundAtEntity(p, "mob.ghast.fireball", 1, 0.5F);
							
							EntityRocket entity = new EntityRocket(w, p);		
							w.spawnEntityInWorld(entity);
						
							is.damageItem(getMaxDamage(), p);
						}

						break;
					}
				}
			}
		}		
		
		return is;
	}
}

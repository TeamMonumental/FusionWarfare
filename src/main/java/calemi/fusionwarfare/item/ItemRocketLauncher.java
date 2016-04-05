package calemi.fusionwarfare.item;

import java.util.List;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.entity.EntityFusionBullet;
import calemi.fusionwarfare.entity.EntityRocket;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.util.gun.GunData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemRocketLauncher extends ItemBase {

	public static final int maxCD = 200;
	
	public ItemRocketLauncher() {
		super("rocket_launcher", false, false);
		setCreativeTab(InitCreativeTabs.creativeTabInfantry);
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		if (getNBT(is).getInteger("cd") > 0) list.add(EnumChatFormatting.RED + "Cooldown: " + (100 - getNBT(is).getInteger("cd") * 100 / maxCD + "%"));
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack is) {		
		return ((1.0F / maxCD) * getNBT(is).getInteger("cd"));
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack is) {		
		return getNBT(is).getInteger("cd") > 0;
	}
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int i1, boolean b) {
		
		if (!world.isRemote) {

			if (getNBT(is).getInteger("cd") > 0) {
				getNBT(is).setInteger("cd", getNBT(is).getInteger("cd") - 1);
			}
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p) {
			
		if (getNBT(is).getInteger("cd") <= 0) {

			if (p.inventory.hasItem(InitItems.rocket) || p.capabilities.isCreativeMode) {
				
				if (!w.isRemote) {
					
					w.playSoundAtEntity(p, "mob.ghast.fireball", 1, 0.5F);
					
					EntityRocket entity = new EntityRocket(w, p);		
					w.spawnEntityInWorld(entity);				
				}
							
				if (!p.capabilities.isCreativeMode) {
					getNBT(is).setInteger("cd", maxCD);
					p.inventory.consumeInventoryItem(InitItems.rocket);					
				}
			}
		}		
		
		return is;
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack is) {			
		return false;
	}
}

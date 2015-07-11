package calemi.fusionwarfare.event;

import java.util.Random;

import calemi.fusionwarfare.init.InitItems;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntitySpawnEvent {
	
	Random rand = new Random();
	
	@SubscribeEvent
	public void livingSpawnEvent(LivingSpawnEvent event) {				
		addArmorAndWeapons(event.entityLiving);
	}
	
	private void addArmorAndWeapons(EntityLivingBase entity) {
		
		if (entity instanceof EntityMob && !(entity instanceof EntityWitch) && !(entity instanceof EntityEnderman)) {		
			
			if (rand.nextInt(100) == 0) {
				entity.setCurrentItemOrArmor(0, new ItemStack(InitItems.steel.sword));
				entity.setCurrentItemOrArmor(4, new ItemStack(InitItems.steel.helmet));
				entity.setCurrentItemOrArmor(3, new ItemStack(InitItems.steel.chestplate));
				entity.setCurrentItemOrArmor(2, new ItemStack(InitItems.steel.leggings));
				entity.setCurrentItemOrArmor(1, new ItemStack(InitItems.steel.boots));
			}
			
			if (rand.nextInt(400) == 0) {
				
				if (rand.nextInt(2) == 0) {
					
					entity.setCurrentItemOrArmor(0, new ItemStack(InitItems.infused_steel.sword));
					entity.setCurrentItemOrArmor(4, new ItemStack(InitItems.infused_steel.helmet));
					entity.setCurrentItemOrArmor(3, new ItemStack(InitItems.infused_steel.chestplate));
					entity.setCurrentItemOrArmor(2, new ItemStack(InitItems.infused_steel.leggings));
					entity.setCurrentItemOrArmor(1, new ItemStack(InitItems.infused_steel.boots));
				}
				
				else {
					
					entity.setCurrentItemOrArmor(0, new ItemStack(InitItems.infused_steel_red.sword));
					entity.setCurrentItemOrArmor(4, new ItemStack(InitItems.infused_steel_red.helmet));
					entity.setCurrentItemOrArmor(3, new ItemStack(InitItems.infused_steel_red.chestplate));
					entity.setCurrentItemOrArmor(2, new ItemStack(InitItems.infused_steel_red.leggings));
					entity.setCurrentItemOrArmor(1, new ItemStack(InitItems.infused_steel_red.boots));
				}				
			}					
		}
	}
}

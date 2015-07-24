package calemi.fusionwarfare.item;

import java.util.List;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.entity.EntityFusionBullet;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemFusionGatlingGun extends ItemFusionGun {

	public ItemFusionGatlingGun(String imagePath, int ammoCost, int hitDamage, int accuracy, float gravityVelocity) {
		super(imagePath, 0, ammoCost, hitDamage, accuracy, gravityVelocity, false);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		
		player.setItemInUse(is, getMaxItemUseDuration(is));	
		return is;
	}
		
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack is) {
		return false;
	}

	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int i1, boolean b) {

		EntityPlayer player = (EntityPlayer)entity;
			
		if (world.isRemote) {
			
			if (player.getCurrentEquippedItem() == is) {
					
				if (player.isUsingItem()) {
										
					getNBT(is).setBoolean("using", true);
					
					if (player.getItemInUseDuration() > 20) {
				
						FusionWarfare.network.sendToServer(new ServerPacketHandler("shoot"));
					}	
				}
			
				else {
					getNBT(is).setBoolean("using", false);
				}			
			}	
		}
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
		return 72000;
	}
}
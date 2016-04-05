package calemi.fusionwarfare.item;

import java.util.List;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.entity.EntityFusionBullet;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.util.gun.GunData;
import calemi.fusionwarfare.util.gun.GunProfile;
import cpw.mods.fml.common.FMLCommonHandler;
import javafx.scene.chart.PieChart.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemFusionGatlingGun extends ItemFusionGun {

	public ItemFusionGatlingGun() {
		super("fusion_gatling_gun", new GunProfile(1, 1, 128, 1, 1, 0.06F, 0, true));
	}
		
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase player, EntityLivingBase entity) {
		return false;
	}
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int slot, boolean b) {
		super.onUpdate(is, world, entity, slot, b);
		
		GunData data = new GunData(is);
		EntityPlayer player = (EntityPlayer)entity;
		
		if (data.usingTicks > 0 && player.getCurrentEquippedItem() == is) {
			player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1, 1, true));
		}
		
		if (data.usingTicks > 5) {				
			shootBullet(world, is, player, data, 5);
		}
		
		data.flush();
	}
}
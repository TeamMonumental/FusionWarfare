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
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack is) {
		return true;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		return 72000;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {		
		
		GunData data = new GunData(is);
		
		data.usingTicks++;
				
		data.flush();
			
		return is;
	}
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int slot, boolean b) {
		super.onUpdate(is, world, entity, slot, b);
		
		GunData data = new GunData(is);
		EntityPlayer player = (EntityPlayer)entity;
		
		if (world.isRemote) {
			
			if (Minecraft.getMinecraft().currentScreen != null || player.getCurrentEquippedItem() != is || !Minecraft.getMinecraft().gameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindUseItem)) {
				data.usingTicks = 0;
			}		
			
			FusionWarfare.network.sendToServer(new ServerPacketHandler("stop.use%" + data.usingTicks + "%" + slot));
		}
		
		if (data.usingTicks > 5) {				
			shootBullet(world, is, player, data, 5);
		}	
		
		data.flush();
	}
}
package calemi.fusionwarfare.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.entity.DamageSourceCustom;
import calemi.fusionwarfare.entity.EntityFusionBullet;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.key.KeyBindings;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.util.gun.GunData;
import calemi.fusionwarfare.util.gun.GunProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemFusionGun extends ItemBase {

	public GunProfile profile;
	
	public ItemFusionGun(String imagePath, GunProfile profile) {
		super(imagePath, false, false);
		this.profile = profile;
		setMaxStackSize(1);
		setCreativeTab(InitCreativeTabs.creativeTabInfantry);
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean b) {
		
		GunData data = new GunData(is);
		
		list.add(EnumChatFormatting.GOLD + "Ammo: " + EnumChatFormatting.AQUA + data.ammo + "/" + profile.maxAmmo);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		return 72000;
	}
	
	@Override
	public boolean isFull3D() {
		return true;
	}
	
	@Override
	public double getDurabilityForDisplay(ItemStack is) {
		
		GunData data = new GunData(is);
		
		return 1 - ((1.0F / profile.maxAmmo) * data.ammo);
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack is) {
		
		GunData data = new GunData(is);
		
		return data.ammo < profile.maxAmmo;
	}
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int slot, boolean b) {
			
		if (!(entity instanceof EntityPlayer)) {
			return;
		}
		
		EntityPlayer player = (EntityPlayer)entity;
		GunData data = new GunData(is);
			
		if (player.getCurrentEquippedItem() == is) {
			
			if (world.isRemote) {	
						
				if (Minecraft.getMinecraft().currentScreen != null || player.getCurrentEquippedItem() != is || !Minecraft.getMinecraft().gameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindUseItem)) {
					data.usingTicks = 0;
				}		
				
				FusionWarfare.network.sendToServer(new ServerPacketHandler("stop.use%" + data.usingTicks + "%" + slot));
				
				if (data.ammo < profile.maxAmmo) {
				
					if (Keyboard.isKeyDown(KeyBindings.reloadButton.getKeyCode()) && Minecraft.getMinecraft().currentScreen == null) {
												
						FusionWarfare.network.sendToServer(new ServerPacketHandler("reload"));
						data.time = 0;
					}
				}			
			}
				
			else {					
				
				if (data.ammo < profile.maxAmmo && data.reloading && hasAmmo(player, profile.shotsPerFire)) {
					
					data.time++;
					
					if (data.time >= profile.reloadTime) {
					
						data.time = 0;
						
						for (int i = 0; i < profile.shotsPerFire; i++) {									
							player.inventory.consumeInventoryItem(InitItems.fusion_ammo);
						}											
						
						data.ammo++;
						world.playSoundAtEntity(player, "random.click", 1, data.ammo == profile.maxAmmo ? 0.5F : 1.8F);
					}
				}	
				
				else {					
					data.reloading = false;
				}
			}
		}	
		
		else {
			data.scope = 0;
			data.reloading = false;
		}
		
		data.flush();
	}	
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		
		GunData data = new GunData(is);
		
		if (is.getItem() != InitItems.fusion_gatling_gun) {
			
			if (profile.isAuto || data.usingTicks == 0) {
				
				shootBullet(world, is, player, data, 0);				
			}
		}		
		
		data.usingTicks++;
				
		data.flush();
				
		return is;
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack is) {
			
		if (is.getItem() == InitItems.fusion_sniper_rifle) {

			GunData data = new GunData(is);
			
			data.scope++;
			data.scope %= 3;
			data.flush();
		}
		
		return false;
	}
	
	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase player, EntityLivingBase entity) {
		
		if (player instanceof EntityPlayer && entity instanceof EntityPlayer) {
			
			entity.attackEntityFrom(new DamageSourceCustom(((EntityPlayer)entity).getDisplayName() + " was knocked out by " + ((EntityPlayer)player).getDisplayName() + "'s gun"), 4.0F);
		}
			
		else {
			entity.attackEntityFrom(DamageSource.generic, 2.0F);
		}	
		
		return true;
	}
		
	public boolean hasAmmo(EntityPlayer player, int ammo) {

		if (player.capabilities.isCreativeMode) return true;
		
		int amount = 0;
		
		for (ItemStack slot : player.inventory.mainInventory) {
					
			if (slot != null && slot.getItem() == InitItems.fusion_ammo) {				
				amount += slot.stackSize;							
			}	
			
			if (amount >= profile.shotsPerFire) {				
				return true;	
			}
		}	
		
		return false;
	}
		
	public void shootBullet(World world, ItemStack is, EntityPlayer player, GunData data, int hurtTime) {
		
		data.reloading = false;
		
		if (data.ammo > 0) {		

			if (world.isRemote) {
				
				FusionWarfare.network.sendToServer(new ServerPacketHandler("use.gun%" + profile.shotsPerFire + "%" + profile.damage + "%" + profile.accuracy + "%" + profile.gravityVelocity + "%" + hurtTime));
				
				player.rotationPitch -= profile.recoil;
			}			
		}
			
		else world.playSoundAtEntity(player, "random.click", 1, 0.5F);			
		
		data.flush();
	}	
}
package calemi.fusionwarfare.item;

import java.util.List;

import calemi.fusionwarfare.entity.EntityFusionBullet;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.util.explosion.GrenadeBlastEvent;
import calemi.fusionwarfare.util.missile.MissileType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemGrenade extends ItemBase {

	public GrenadeBlastEvent event;
	
	public ItemGrenade(String imagePath, GrenadeBlastEvent event) {
		super(imagePath, false, false);
		this.event = event;
		setCreativeTab(InitCreativeTabs.creativeTabInfantry);
		setMaxStackSize(16);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {

		if (world.isRemote) {			
			
		}
		
		if (!player.capabilities.isCreativeMode) is.stackSize--;
		
		return is;
	}
}

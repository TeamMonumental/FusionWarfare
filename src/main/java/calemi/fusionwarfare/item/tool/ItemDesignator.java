package calemi.fusionwarfare.item.tool;

import java.util.List;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.entity.EntityDesignatorOrb;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.item.ItemBase;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemDesignator extends ItemBase {
	
	public ItemDesignator() {
		super("designator");
		setCreativeTab(InitCreativeTabs.creativeTabTools);
		setMaxStackSize(1);
		setMaxDamage(100);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List list, boolean b) {
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			
			list.add(EnumChatFormatting.GOLD + "Missile Launching Unit");
			list.add(EnumChatFormatting.GOLD + "X: " + EnumChatFormatting.AQUA + getNBT(is).getInteger("unitX"));
			list.add(EnumChatFormatting.GOLD + "Y: " + EnumChatFormatting.AQUA + getNBT(is).getInteger("unitY"));
			list.add(EnumChatFormatting.GOLD + "Z: " + EnumChatFormatting.AQUA + getNBT(is).getInteger("unitZ"));
			list.add("");
			list.add("Calls a missile launching unit to fire at a Location.");
			list.add("Sneak Right-click: Grabs coords from launcher");
			list.add("Right-click: Fires an orb.");
			
			if (is.getItemDamage() > 0) {
				list.add("");
				list.add(EnumChatFormatting.RED + "Reloading: " + getCurrentProgress(is) * 100 / getMaxDamage() + "%");
			}
			
		} else {
			list.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
		}
	}
	
	@Override
	public boolean isFull3D() {
		return true;
	}
		
	public int getCurrentProgress(ItemStack is) {
		return getMaxDamage() - is.getItemDamage();
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int side, float f1, float f2, float f3) {
	
		if (p.isSneaking()) {
			
			Block block = w.getBlock(x, y, z);
			
			if (block == InitBlocks.missile_launcher || block == InitBlocks.missile_silo_core) {
				
				getNBT(is).setInteger("unitX", x);
				getNBT(is).setInteger("unitY", y);
				getNBT(is).setInteger("unitZ", z);
				
				p.worldObj.playSoundAtEntity(p, "random.orb", 1F, 1F);	
			} 
		}
		
		return false;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p) {

		if (!p.isSneaking()) {
			
			if (getCurrentProgress(is) == getMaxDamage()) {
				
				if (getNBT(is).getInteger("unitX") != 0 && getNBT(is).getInteger("unitY") != 0 && getNBT(is).getInteger("unitZ") != 0) {
				
					if (!w.isRemote) {

						EntityDesignatorOrb entity = new EntityDesignatorOrb(w, p);
						w.spawnEntityInWorld(entity);
						
						w.playSoundAtEntity(entity, Reference.MOD_ID + ":gun_shot", 1, 1);
						
						is.damageItem(getMaxDamage(), p);
					}			
				}
				
				else if (!w.isRemote) {
					p.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "The Missile Unit is not set!"));
				}
			}			
		}
		
		return is;
	}
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int i1, boolean b) {

		if (!world.isRemote) {

			if (getCurrentProgress(is) < getMaxDamage()) {
				is.damageItem(-1, (EntityLivingBase) entity);
			}
		}
	}
}
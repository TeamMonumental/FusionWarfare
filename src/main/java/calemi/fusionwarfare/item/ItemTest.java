package calemi.fusionwarfare.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.entity.EntityFallingSupplyCrate;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemTest extends ItemBase {
	
	public ItemTest() {
		super("test");
	}

	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List list, boolean b) {
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			
			list.add(EnumChatFormatting.GOLD + "Right-click = Blue");
			list.add(EnumChatFormatting.GOLD + "Sneak Right-click = Orange");
			list.add(EnumChatFormatting.GOLD + "Left-click = Red");
			
		} else {
			list.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
		if (!world.isRemote) {
			
			EntityFallingSupplyCrate entityCrate = new EntityFallingSupplyCrate(player.isSneaking() ? 1 : 0, world, (int)player.posX, (int)player.posZ);
			world.spawnEntityInWorld(entityCrate);
		}
		
		else {
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "A supply crate is falling at " + (int)player.posX + " " + (int)player.posZ));	
		}
		
		return stack;
	}
	
	@Override
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		
		if (!entityLiving.worldObj.isRemote) {
			
			EntityFallingSupplyCrate entityCrate = new EntityFallingSupplyCrate(2, entityLiving.worldObj, (int)entityLiving.posX, (int)entityLiving.posZ);
			entityLiving.worldObj.spawnEntityInWorld(entityCrate);
		}
		
		else {
			((EntityPlayer)entityLiving).addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "A supply crate is falling at " + (int)entityLiving.posX + " " + (int)entityLiving.posZ));	
		}
		
		return false;
	}
}

package calemi.fusionwarfare.item;

import java.util.List;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.api.IEnergy;
import calemi.fusionwarfare.init.InitCreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockEnergyBase extends ItemBlock {

	public ItemBlockEnergyBase(Block block) {
		super(block);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List list, boolean b) {

		if ((getMaxEnergy(is) > 0)) {
			
			if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			
				list.add(EnumChatFormatting.GOLD + "Energy: " + EnumChatFormatting.AQUA + getEnergy(is) + "/" + getMaxEnergy(is));
			
			} else {
				list.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
			}
		}			
	}
	
	public NBTTagCompound getNBT(ItemStack is) {
		if (is.getTagCompound() == null) {
			is.setTagCompound(new NBTTagCompound());
		}

		return is.getTagCompound();
	}
	
	public int getMaxEnergy(ItemStack is) {
		return getNBT(is).getInteger("maxEnergy");
	}
	
	public void setMaxEnergy(int energy, ItemStack is) {
		getNBT(is).setInteger("maxEnergy", energy);
	}
	
	public int getEnergy(ItemStack is) {
		return getNBT(is).getInteger("energy");
	}
	
	public void setEnergy(int energy, ItemStack is) {
		getNBT(is).setInteger("energy", energy);
	}
}

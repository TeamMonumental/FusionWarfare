package calemi.fusionwarfare.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public abstract class ItemEnergyBase extends ItemBase implements IEnergyItem {

	public ItemEnergyBase(String imagePath) {
		super(imagePath);
	}
	
	@Override
	public boolean isRepairable() {
		return false;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List l, boolean b) {
		l.add(EnumChatFormatting.GOLD + "Energy: " + EnumChatFormatting.AQUA + getEnergy(stack) + "/" + getMaxEnergy());
	}

	@Override
	public int getEnergy(ItemStack stack) {
		stack.setItemDamage(getMaxEnergy() - getNBT(stack).getInteger("energy"));
		return getNBT(stack).getInteger("energy");
	}

	@Override
	public void setEnergy(ItemStack stack, int energy) {
		getNBT(stack).setInteger("energy", energy);;
	}

	@Override
	public boolean addEnergy(ItemStack stack, int energy) {		
		
		if ((getMaxEnergy() - getEnergy(stack)) >= energy) {
			
			setEnergy(stack, getEnergy(stack) + energy);
			return true;
		}
		
		return false;	
	}

	@Override
	public boolean subtractEnergy(ItemStack stack, int energy) {
		
		if (getEnergy(stack) >= energy) {
			
			setEnergy(stack, getEnergy(stack) - energy);
			return true;
		}
		
		return false;
	}	
}
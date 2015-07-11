package calemi.fusionwarfare.item;

import java.util.List;

import calemi.fusionwarfare.util.EnergyItemUtil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemBattery extends ItemEnergyBase {

	public ItemBattery() {
		super("battery");
		setMaxDamage(getMaxEnergy());
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer player, List l, boolean b) {
		super.addInformation(is, player, l, b);		
		if(getNBT(is).getBoolean("output")) l.add(EnumChatFormatting.AQUA + "" + EnumChatFormatting.ITALIC + "Outputting to items.");
	}
	
	@Override
	public boolean hasEffect(ItemStack is) {
		return getNBT(is).getBoolean("output");
	}
	
	@Override
	public void onUpdate(ItemStack is, World world, Entity entity, int i, boolean b) {

		EntityPlayer entityPlayer = (EntityPlayer)entity;
		
		if (getNBT(is).getBoolean("output")) {				
				
			for (ItemStack slot : entityPlayer.inventory.mainInventory) {			
				
				if (slot != null) {
					
					EnergyItemUtil.transferEnergy(is, slot, 5);
				}
			}
		}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		
		if (!world.isRemote) {	
			
			if (!getNBT(is).getBoolean("output")) {			
				getNBT(is).setBoolean("output", true);
			}
			
			else {
				getNBT(is).setBoolean("output", false);
			}	
			
			player.worldObj.playSoundAtEntity(player, "random.orb", 1F, 1F);
		}
		
		return is;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, getMaxDamage()));
	}
	
	@Override
	public int getDamage(ItemStack stack) {
		return getMaxEnergy() - getEnergy(stack);
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}
	
	@Override
	public int getMaxEnergy() {
		return 5000;
	}
}

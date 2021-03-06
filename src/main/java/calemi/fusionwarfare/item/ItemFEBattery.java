package calemi.fusionwarfare.item;

import java.util.List;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.api.EnergyItemUtil;
import calemi.fusionwarfare.init.InitItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemFEBattery extends ItemEnergyBase {

	private IIcon[] overlays = new IIcon[9];
	
	public ItemFEBattery() {
		super("fe_battery");
		setMaxStackSize(1);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}
			
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack is, int overlay) {
				
		int energyScaled = getEnergy(is) * 8 / getMaxEnergy();

		if (overlay > 0) {
			
			return overlays[energyScaled];			
		}

		return super.getIcon(is, overlay);	
	}
	
	public boolean hasColor(ItemStack is) {
		return true;
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
			
			getNBT(is).setBoolean("output", !getNBT(is).getBoolean("output"));
			
			player.worldObj.playSoundAtEntity(player, "random.orb", 1F, 1F);
		}
		
		return is;
	}
	
	@Override
	public int getMaxEnergy() {
		return 15000;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":battery/battery_base");
		
		for (int i = 0; i < overlays.length; i++) {
			overlays[i] = iconRegister.registerIcon(Reference.MOD_ID + ":battery/battery_overlay_" + i);
		}
	}
}

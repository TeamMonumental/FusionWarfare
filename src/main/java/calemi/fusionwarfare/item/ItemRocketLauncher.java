package calemi.fusionwarfare.item;

import calemi.fusionwarfare.entity.EntityRocket;
import calemi.fusionwarfare.init.InitCreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRocketLauncher extends ItemBase {

	public ItemRocketLauncher() {
		super("rocket_launcher", false, false);
		setCreativeTab(InitCreativeTabs.creativeTabInfantry);
	}	
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p) {

		if (!w.isRemote) {
			
			EntityRocket entity = new EntityRocket(w, p);		
			w.spawnEntityInWorld(entity);
		}
		
		return is;
	}
}

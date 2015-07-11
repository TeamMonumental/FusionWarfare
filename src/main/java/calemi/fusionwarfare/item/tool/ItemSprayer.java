package calemi.fusionwarfare.item.tool;

import java.util.Random;

import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.ItemBase;
import calemi.fusionwarfare.item.ItemChargedSeeds;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSprayer extends ItemBase {

	Random rand = new Random();
	
	public ItemSprayer() {
		super("sprayer");
	}
	
	@SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }
	
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float f1, float f2, float f3) {
		
		for (Location loc : ShapeUtil.getSphere(world, x, y, z, 4)) {		
			
			if (rand.nextInt(2) == 0) {
				
				if (player.inventory.hasItem(InitItems.charged_seeds)) {
					
					if (ItemChargedSeeds.checkAndPlaceSeed(itemStack, world, loc.x, loc.y + 1, loc.z)) {
						player.inventory.consumeInventoryItem(InitItems.charged_seeds);
					}
				}
			}
		}
		
		return false;
	}
}

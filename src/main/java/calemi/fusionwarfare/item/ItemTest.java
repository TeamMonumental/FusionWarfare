package calemi.fusionwarfare.item;

import calemi.fusionwarfare.entity.EntityFallingSupplyCrate;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;
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
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		
		if (!world.isRemote) {
			
			EntityFallingSupplyCrate entityCrate = new EntityFallingSupplyCrate(player.isSneaking() ? 1 : 0, world, (int)player.posX, (int)player.posZ);
			world.spawnEntityInWorld(entityCrate);
		}
		
		else {
			player.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "A supply crate is falling at " + (int)player.posX + " " + (int)player.posZ));	
		}
		
			/*for (Location l : ShapeUtil.getFilteredCube(world, (int)player.posX, (int)player.posY - 50, (int)player.posZ, 10, 50, 10, InitBlocks.infused_catalyst_ore)) {
				
				player.setPositionAndUpdate(l.x, l.y, l.z);
			}*/
			
			/*EntityDragon entity = new EntityDragon(world);
			entity.setPositionAndUpdate(player.posX, player.posY, player.posZ);
			entity.setHealth(50.F);
			world.spawnEntityInWorld(entity);*/
		
		
		return stack;
	}
}

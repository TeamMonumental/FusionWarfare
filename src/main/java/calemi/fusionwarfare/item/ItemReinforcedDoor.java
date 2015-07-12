package calemi.fusionwarfare.item;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.tileentity.TileEntityReinforcedDoor;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemReinforcedDoor extends ItemBase {
	
	public ItemReinforcedDoor() {
		super("reinforced_door_placer");
	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int meta, float f1, float f2, float f3) {
		
		if (meta != 1) {
			return false;
		} 
		
		else {
			
			++y;
			Block block = InitBlocks.reinforced_door;
									
			if (player.canPlayerEdit(x, y, z, meta, itemStack) && player.canPlayerEdit(x, y + 1, z, meta, itemStack)) {
				
				if (!block.canPlaceBlockAt(world, x, y, z)) {					
					return false;
				} 
				
				else {
					int i1 = MathHelper.floor_double((double) ((player.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
					ItemDoor.placeDoorBlock(world, x, y, z, i1, block);
					
					TileEntityReinforcedDoor tileEntity = (TileEntityReinforcedDoor)world.getTileEntity(x, y, z);
					if (player.getTeam() != null) tileEntity.teamName = player.getTeam().getRegisteredName();
					
					--itemStack.stackSize;
					return true;
				}
				
			} else {
				return false;
			}
		}
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + imagePath);
	}
}
package calemi.fusionwarfare.item;

import java.util.Random;

import calemi.fusionwarfare.init.InitBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemChargedSeeds extends ItemBase {

	static Random rand = new Random();

	public ItemChargedSeeds() {
		super("charged_seeds");
	}

	@Override
	public boolean hasEffect(ItemStack is) {
		return true;
	}

	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float f1, float f2, float f3) {

		if (world.getBlock(x, y, z) != Blocks.snow_layer) {

			if (side == 0) {
				--y;
			}

			if (side == 1) {
				++y;
			}

			if (side == 2) {
				--z;
			}

			if (side == 3) {
				++z;
			}

			if (side == 4) {
				--x;
			}

			if (side == 5) {
				++x;
			}
		}

		if (!player.canPlayerEdit(x, y, z, side, itemStack)) {
			return false;
		}

		else {

			checkAndPlaceSeed(itemStack, world, x, y, z);

			return true;
		}
	}

	public static boolean checkAndPlaceSeed(ItemStack itemStack, World world, int x, int y, int z) {
			
		if (Blocks.redstone_wire.canPlaceBlockAt(world, x, y, z) && world.isAirBlock(x, y, z)) {

			if (world.getBlock(x, y - 1, z) == Blocks.grass) {

				if (!world.isRemote) {
					world.setBlock(x, y, z, InitBlocks.charged_plant);
					world.setBlockMetadataWithNotify(x, y, z, MathHelper.getRandomIntegerInRange(rand, 0, 3), 2);
				}
				
				--itemStack.stackSize;
				return true;
			}

			if (world.getBlock(x, y - 1, z) == Blocks.sand) {

				if (!world.isRemote) {
					world.setBlock(x, y, z, InitBlocks.charged_plant);
					world.setBlockMetadataWithNotify(x, y, z, 4, 2);
				}	
				
				--itemStack.stackSize;
				return true;
			}			
		}

		return false;
	}
}

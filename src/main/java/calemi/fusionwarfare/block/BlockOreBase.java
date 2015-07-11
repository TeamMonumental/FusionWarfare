package calemi.fusionwarfare.block;

import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.init.InitItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class BlockOreBase extends BlockBase {

	Random rand = new Random();
	
	private Item droppedItem;
	private int dropQuantityRange1, dropQuantityRange2;
	private int expAmountRange1, expAmountRange2;
	
	public BlockOreBase(String imagePath, Item droppedItem, int dropQuantityRange1, int dropQuantityRange2, int expAmountRange1, int expAmountRange2) {
		super(imagePath, 2, Material.rock, 3, 3, Block.soundTypeStone);
		this.droppedItem = droppedItem;
		this.dropQuantityRange1 = dropQuantityRange1;
		this.dropQuantityRange2 = dropQuantityRange2;
		this.expAmountRange1 = expAmountRange1;
		this.expAmountRange2 = expAmountRange2;
		GameRegistry.addSmelting(this, new ItemStack(droppedItem), dropQuantityRange1);
	}

	@Override
	public int getExpDrop(IBlockAccess blockAccess, int i1, int i2) {
		return MathHelper.getRandomIntegerInRange(rand, expAmountRange1, expAmountRange2);
	}

	public int quantityDroppedWithBonus(int fortune, Random random) {
		
		if (fortune > 0) {
			
			int j = random.nextInt(fortune + 2) - 1;

			if (j < 0) j = 0;

			return quantityDropped(random) * (j + 1);			
		} 
		
		else {
			return quantityDropped(random);
		}
	}

	@Override
	public int quantityDropped(Random rand) {
		return MathHelper.getRandomIntegerInRange(rand, dropQuantityRange1, dropQuantityRange2);
	}

	@Override
	public Item getItemDropped(int i1, Random rand, int i2) {
		return droppedItem;
	}
}

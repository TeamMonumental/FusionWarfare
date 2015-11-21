package calemi.fusionwarfare.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockChargedPlant extends ItemBlockMeta {

	private final static String[] SUBNAMES = new String[] {"growing_grass", "tallgrass", "fern", "growing_deadbush", "deadbush"};

	public ItemBlockChargedPlant(Block block) {
		super(block);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		
		int i = itemStack.getItemDamage();
		
		if (i < 0 || i >= SUBNAMES.length) {
			i = 0;
		}

		return super.getUnlocalizedName() + "." + SUBNAMES[i];
	}

}

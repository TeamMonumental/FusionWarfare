package calemi.fusionwarfare.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class InitArmorMaterials {
	
public static ItemArmor.ArmorMaterial toolMaterialScuba, toolMaterialSteel, toolMaterialInfusedSteel;
	
	public static void init() {
		
		toolMaterialScuba = EnumHelper.addArmorMaterial("ScubaGear", 0, new int[]{2, 6, 5, 2}, 0);
		toolMaterialSteel = EnumHelper.addArmorMaterial("Steel", 30, new int[]{2, 6, 5, 2}, 9);
		toolMaterialInfusedSteel = EnumHelper.addArmorMaterial("InfusedSteel", 66, new int[]{3, 8, 6, 3}, 10);
	}
}

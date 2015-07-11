package calemi.fusionwarfare.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class InitArmorMaterials {
	
public static ItemArmor.ArmorMaterial toolMaterialSteel, toolMaterialInfusedSteel;
	
	public static void init() {
		
		toolMaterialSteel = EnumHelper.addArmorMaterial("Steel", 30, new int[]{2, 6, 5, 2}, 9);
		toolMaterialInfusedSteel = EnumHelper.addArmorMaterial("InfusedSteel", 66, new int[]{3, 8, 6, 3}, 10);
	}
}

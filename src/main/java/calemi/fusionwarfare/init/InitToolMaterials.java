package calemi.fusionwarfare.init;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class InitToolMaterials {
	
public static Item.ToolMaterial toolMaterialSteel, toolMaterialInfusedSteel, toolMaterialFusionMatterDeconstructor;
	
	public static void init() {
		
		toolMaterialSteel = EnumHelper.addToolMaterial("Steel", 2, 500, 6.0F, 2.0F, 14);
		toolMaterialInfusedSteel = EnumHelper.addToolMaterial("InfusedSteel", 3, 2000, 10.0F, 3.0F, 10);
		toolMaterialFusionMatterDeconstructor = EnumHelper.addToolMaterial("FusionMatterDeconstructor", 3, 0, 20.0F, 5.0F, 10);
	}
}

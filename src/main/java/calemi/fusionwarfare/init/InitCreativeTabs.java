package calemi.fusionwarfare.init;

import calemi.fusionwarfare.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class InitCreativeTabs {

	public static final CreativeTabs creativeTabCore = new CreativeTabs(Reference.MOD_ID) {
		
		@Override
		public Item getTabIconItem() {
			return InitItems.infused_crystal;
		}
		
		@Override
		public String getTranslatedTabLabel() {			
			return "Fusion Warfare - Core";
		};
	};
	
	public static final CreativeTabs creativeTabTools = new CreativeTabs(Reference.MOD_ID) {
		
		@Override
		public Item getTabIconItem() {
			return InitItems.infused_steel.pickaxe;
		}
		
		@Override
		public String getTranslatedTabLabel() {			
			return "Fusion Warfare - Tools & Armor";
		};
	};
	
	public static final CreativeTabs creativeTabMachines = new CreativeTabs(Reference.MOD_ID) {
		
		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(InitBlocks.network_controller_1);
		}
		
		@Override
		public String getTranslatedTabLabel() {			
			return "Fusion Warfare - Machines";
		};
	};
	
	public static final CreativeTabs creativeTabInfantry = new CreativeTabs(Reference.MOD_ID) {
		
		@Override
		public Item getTabIconItem() {
			return InitItems.fusion_pistol;
		}
		
		@Override
		public String getTranslatedTabLabel() {			
			return "Fusion Warfare - Infantry";
		};
	};
	
	public static final CreativeTabs creativeTabMissiles = new CreativeTabs(Reference.MOD_ID) {
		
		@Override
		public Item getTabIconItem() {
			return InitItems.velocity_missile_T1;
		}
		
		@Override
		public String getTranslatedTabLabel() {			
			return "Fusion Warfare - Missiles";
		};
	};
}

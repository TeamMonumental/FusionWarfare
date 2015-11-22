package calemi.fusionwarfare.item;

import calemi.fusionwarfare.block.BlockNetworkController;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUpgradeChip extends ItemBase {

	public ItemUpgradeChip(String imagePath) {
		super(imagePath + "_upgrade_chip");
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int meta, float f1, float f2, float f3) {
		
		Block block = w.getBlock(x, y, z);
		
		if (!w.isRemote && block instanceof BlockNetworkController) {
			
			TileEntityNetworkController tileEntityOld = (TileEntityNetworkController)w.getTileEntity(x, y, z);
					
			int energy = tileEntityOld.getEnergy();
			ItemStack[] stacks = tileEntityOld.slots.clone();
					
			for (int i = 0; i < tileEntityOld.getSizeInventory(); i++) {				
				tileEntityOld.setInventorySlotContents(i, null);
			}
			
			if (block == InitBlocks.network_controller_1 && this == InitItems.advanced_upgrade_chip) w.setBlock(x, y, z, InitBlocks.network_controller_2, meta, 2);
			if (block == InitBlocks.network_controller_2 && this == InitItems.hyper_upgrade_chip) w.setBlock(x, y, z, InitBlocks.network_controller_3, meta, 2);
			
			TileEntityNetworkController tileEntityNew = (TileEntityNetworkController)w.getTileEntity(x, y, z);		
			tileEntityNew.setEnergy(energy);
			
			for (int i = 0; i < tileEntityNew.getSizeInventory(); i++) {
				tileEntityNew.setInventorySlotContents(i, stacks[i]);				
			}
			
			return true;		
		}
		
		return false;
	}
}

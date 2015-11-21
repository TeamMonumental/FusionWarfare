package calemi.fusionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.tileentity.reactor.TileEntityReactorCore;
import calemi.fusionwarfare.tileentity.reactor.TileEntitySteelCasing;

public class BlockSteelCasing extends BlockBasicMachineBase {

	public BlockSteelCasing() {
		super("steel_casing", TileEntitySteelCasing.class);			
		setCreativeTab(InitCreativeTabs.creativeTabCore);
		setAllSideImages("steel_casing");
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float f, float f2, float f3) {
		
		if (!w.isRemote) {
	
			TileEntitySteelCasing tileEntity = (TileEntitySteelCasing) w.getTileEntity(x, y, z);
		
			if ((p.getCurrentEquippedItem() == null || !(Block.getBlockFromItem(p.getCurrentEquippedItem().getItem()) instanceof BlockSteelCasing)) && tileEntity.getMaster() != null) {
				
				tileEntity.getMaster().getBlockType().onBlockActivated(w, tileEntity.getMaster().xCoord, tileEntity.getMaster().yCoord, tileEntity.getMaster().zCoord, p, i, f, f2, f3);
				
				return true;
			}
		}
		
		return false;
	}
}

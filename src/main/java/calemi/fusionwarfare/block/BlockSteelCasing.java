package calemi.fusionwarfare.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.tileentity.gen.reactor.TileEntityReactorCore;
import calemi.fusionwarfare.tileentity.gen.reactor.TileEntitySteelCasing;

public class BlockSteelCasing extends BlockBasicMachineBase {

	public BlockSteelCasing() {
		super("steel_casing", TileEntitySteelCasing.class, 0, false, true, "steel_casing", "steel_casing", "steel_casing", false);	
		setCreativeTab(InitCreativeTabs.creativeTabCore);
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float f, float f2, float f3) {
		
		if(w.isRemote) {
			return true;
		} 
		
		else {	
	
			TileEntitySteelCasing tileEntity = (TileEntitySteelCasing) w.getTileEntity(x, y, z);
		
			if (tileEntity.getMaster() != null) {
				
				tileEntity.getMaster().getBlockType().onBlockActivated(w, tileEntity.getMaster().xCoord, tileEntity.getMaster().yCoord, tileEntity.getMaster().zCoord, p, i, f, f2, f3);
				
				return true;
			}	
			
			else {
				
			}
						
			return canPlaceBlockAt(w, x, y, z);
		} 	
	}
}

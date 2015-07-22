package calemi.fusionwarfare.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.tileentity.gen.reactor.TileEntityReactorCasing;

public class BlockReactorCasing extends BlockBasicMachineBase {

	public BlockReactorCasing() {
		super("reactor_casing", TileEntityReactorCasing.class, 0, false, true, "steel_casing", "steel_casing", "steel_casing");		
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float f, float f2, float f3) {
		
		if(w.isRemote) {
			return true;
		} 
		
		else {	
	
			TileEntityReactorCasing tileEntity = (TileEntityReactorCasing) w.getTileEntity(x, y, z);
		
			if (tileEntity.getMaster() != null) {
				
				FMLNetworkHandler.openGui(p, FusionWarfare.instance, FusionWarfare.guiIDReactorCore, w, tileEntity.x, tileEntity.y, tileEntity.z);		
				return true;
			}			
						
			return canPlaceBlockAt(w, x, y, z);
		} 	
	}
}

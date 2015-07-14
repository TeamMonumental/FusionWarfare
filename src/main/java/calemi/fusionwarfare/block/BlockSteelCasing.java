package calemi.fusionwarfare.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.init.InitCreativeTabs;
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
				
				FMLNetworkHandler.openGui(p, FusionWarfare.instance, FusionWarfare.guiIDReactorCore, w, tileEntity.x, tileEntity.y, tileEntity.z);		
				return true;
			}			
						
			return canPlaceBlockAt(w, x, y, z);
		} 	
	}
}

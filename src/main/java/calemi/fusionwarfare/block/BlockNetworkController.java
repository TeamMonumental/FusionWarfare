package calemi.fusionwarfare.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;

public class BlockNetworkController extends BlockBasicMachineBase {

	public int tier;
	
	public BlockNetworkController(int tier, int guiID) {
		super("network_controller_" + tier, null, guiID, false, "mech_top_" + tier, "mech_blank", "network_controller_" + tier + "_side", false);
		this.tier = tier;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int i) {
			
		TileEntityNetworkController tileEntity = new TileEntityNetworkController();
		
		tileEntity.maxEnergy = tier * 25000;
		
		return tileEntity;
	}
}

package calemi.fusionwarfare.block;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockNetworkController extends BlockBasicMachineBase {

	public final int tier;
	
	public BlockNetworkController(int tier) {
		super("network_controller_" + tier, null);
		this.tier = tier;
		setTopImage("mech_top_" + tier);
		setAllFourSideImages();
	}
		
	@Override
	public TileEntity createNewTileEntity(World world, int i) {			
		return new TileEntityNetworkController(tier);
	}
}

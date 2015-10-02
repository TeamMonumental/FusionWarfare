package calemi.fusionwarfare.block;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockNetworkController extends BlockBasicMachineBase {

	public int tier;
	
	public BlockNetworkController(int tier) {
		super("network_controller_" + tier, null, Reference.guiIDNetworkController, false, true, "mech_top_" + tier, "steel_casing", "network_controller_" + tier + "_side");
		this.tier = tier;
		setCreativeTab(InitCreativeTabs.creativeTabMachines);
	}
		
	@Override
	public TileEntity createNewTileEntity(World world, int i) {
			
		TileEntityNetworkController tileEntity = new TileEntityNetworkController();

		tileEntity.tier = tier;
		
		int energy = 25000;
		
		if (tier == 2) energy = 50000; 
		if (tier == 3) energy = 100000; 
		
		tileEntity.maxEnergy = energy;
		return tileEntity;
	}
}

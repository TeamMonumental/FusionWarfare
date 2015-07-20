package calemi.fusionwarfare.block;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.item.ItemBlockEnergyBase;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockNetworkController extends BlockBasicMachineBase {

	public int tier;
	
	public BlockNetworkController(int tier) {
		super("network_controller_" + tier, null, FusionWarfare.guiIDNetworkController, false, false, "mech_top_" + tier, "steel_casing", "network_controller_" + tier + "_side", false);
		this.tier = tier;
		setCreativeTab(InitCreativeTabs.creativeTabMachines);
		GameRegistry.registerBlock(this, ItemBlockEnergyBase.class, imagePath);
	}
	
	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase e, ItemStack is) {
		
		((TileEntityBase)w.getTileEntity(x, y, z)).setEnergy(((ItemBlockEnergyBase)is.getItem()).getEnergy(is));
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int i) {
			
		TileEntityNetworkController tileEntity = new TileEntityNetworkController();

		tileEntity.tier = tier;
		
		int energy = 25000;
		
		if (tier == 2) energy = 50000; 
		if (tier == 3) energy = 100000; 
		
		if (tier == 1) tileEntity.maxEnergy = energy;
		return tileEntity;
	}
}

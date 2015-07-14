package calemi.fusionwarfare.block;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.item.ItemBlockEnergyBase;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockNetworkController extends BlockBasicMachineBase {

	public int tier;
	
	public BlockNetworkController(int tier, int guiID) {
		super("network_controller_" + tier, null, guiID, false, false, "mech_top_" + tier, "mech_blank", "network_controller_" + tier + "_side", false);
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
		
		tileEntity.maxEnergy = tier * 25000;
		
		return tileEntity;
	}
}

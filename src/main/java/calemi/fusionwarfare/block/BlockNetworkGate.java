package calemi.fusionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkGate;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNetworkGate extends BlockBasicMachineBase {

	@SideOnly(Side.CLIENT)
	private IIcon block_closed;	
	
	public BlockNetworkGate() {
		super("network_gate", TileEntityNetworkGate.class, 0, false, "network_gate", "network_gate", "network_gate", false);
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {		
		return meta == 1 ? block_closed : blockIcon;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconreg) {
		
		this.blockIcon = iconreg.registerIcon(Reference.MOD_ID + ":" + imagePath + "_open");
		this.block_closed = iconreg.registerIcon(Reference.MOD_ID + ":" + imagePath + "_closed");
	}
}

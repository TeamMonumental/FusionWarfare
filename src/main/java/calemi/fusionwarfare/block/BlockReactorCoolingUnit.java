package calemi.fusionwarfare.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockReactorCoolingUnit extends BlockBase {

	@SideOnly(Side.CLIENT)
	private IIcon block_top;
	@SideOnly(Side.CLIENT)
	private IIcon block_bottom;

	public BlockReactorCoolingUnit() {
		super("reactor_cooling_unit", 2, Material.iron, 3F, 3F, Block.soundTypeMetal);
		setCreativeTab(InitCreativeTabs.creativeTabMachines);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {

		if (side == 1) {
			return block_top;
		}

		if (side == 0) {
			return block_bottom;
		}

		return blockIcon;
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconreg) {

		this.block_top = iconreg.registerIcon(Reference.MOD_ID + ":" + imagePath + "_top");
		this.block_bottom = iconreg.registerIcon(Reference.MOD_ID + ":" + "steel_casing");
		this.blockIcon = iconreg.registerIcon(Reference.MOD_ID + ":" + imagePath + "_side");
	}
}

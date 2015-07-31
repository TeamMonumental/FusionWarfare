package calemi.fusionwarfare.block;

import java.util.List;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.item.ItemBlockCrate;
import calemi.fusionwarfare.item.ItemBlockMeta;
import calemi.fusionwarfare.tileentity.TileEntitySupplyCrate;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSupplyCrate extends BlockContainerBase {

	public static final String[] textureNames = new String[] { "blue", "orange", "red" };
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockSupplyCrate() {
		super("supply_crate", 0, Material.wood, 2, 2, Block.soundTypeWood, false);
		setBlockUnbreakable();
		GameRegistry.registerBlock(this, ItemBlockCrate.class, imagePath);
	}
		
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
		
        if (meta >= this.icons.length) {
            meta = 0;
        }

        return this.icons[meta];
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		
		for (int i = 0; i < textureNames.length; i++) {
			list.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		
		icons = new IIcon[textureNames.length];
		
		for (int i = 0; i < textureNames.length; i++) {
			
			icons[i] = iconRegister.registerIcon(Reference.MOD_ID + ":" + imagePath + "_" + textureNames[i]);
		}		
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntitySupplyCrate();
	}

	@Override
	public int getGuiID() {
		return Reference.guiIDSupplyCrate;
	}
}

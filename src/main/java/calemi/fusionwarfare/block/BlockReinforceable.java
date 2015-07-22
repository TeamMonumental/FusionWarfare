package calemi.fusionwarfare.block;

import java.util.List;
import java.util.Random;

import javax.swing.Icon;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.item.ItemBlockMeta;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockReinforceable extends BlockBase {

	public int maxMeta;

	public BlockReinforceable(String imagePath, int maxMeta) {
		super(imagePath, 2, Material.rock, 0, 6000000.0F, Block.soundTypeMetal, false);
		this.maxMeta = maxMeta;
		GameRegistry.registerBlock(this, ItemBlockMeta.class, imagePath);
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		return 3 + (10F * world.getBlockMetadata(x, y, z));
	}

	@Override
	public int damageDropped(int meta) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		return 0xFFFFFF;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderColor(int i) {
		return this.getBlockColor();
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
		return getBlockColor() - (blockAccess.getBlockMetadata(x, y, z) * 0x151515);
	}
}

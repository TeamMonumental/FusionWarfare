package calemi.fusionwarfare.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockBase extends Block {

	public String imagePath;

	public BlockBase(String imagePath, int harvestLevel, Material material, float hardness, float resistance, Block.SoundType stepSound, boolean isRegistered) {
		
		super(material);	
		setHarvestLevel("pickaxe", harvestLevel);
		setBlockName(imagePath);
		setStepSound(stepSound);
		setCreativeTab(InitCreativeTabs.creativeTabCore);
		setHardness(hardness);
		setResistance(resistance);
		this.imagePath = imagePath;
		if (isRegistered) GameRegistry.registerBlock(this, imagePath);
	}
	
	public BlockBase(String imagePath, int harvestLevel, Material material, float hardness, float resistance, Block.SoundType stepSound) {
		this(imagePath, harvestLevel, material, hardness, resistance, stepSound, true);
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + imagePath);
	}
}

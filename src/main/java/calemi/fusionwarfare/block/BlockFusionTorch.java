package calemi.fusionwarfare.block;

import java.util.Random;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.entity.EntityFusionParticleFX;
import calemi.fusionwarfare.init.InitCreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;

public class BlockFusionTorch extends BlockTorch {

	public String imagePath = "fusion_torch";

	public BlockFusionTorch() {

		setBlockName(imagePath);
		setStepSound(Block.soundTypeWood);
		setCreativeTab(InitCreativeTabs.creativeTabCore);
		setHardness(0);
		setLightLevel(1.0F);
		GameRegistry.registerBlock(this, imagePath);
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		
		int metadata = world.getBlockMetadata(x, y, z);
		double d0 = (double) ((float) x + 0.5F);
		double d1 = (double) ((float) y + 0.7F);
		double d2 = (double) ((float) z + 0.5F);
		double d3 = 0.2199999988079071D;
		double d4 = 0.27000001072883606D;

		if (metadata == 1) {
			Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFusionParticleFX(world, "blue_flame", d0 - d4, d1 + d3, d2));
			world.spawnParticle("smoke", d0 - d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		} 
		
		else if (metadata == 2) {
			Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFusionParticleFX(world, "blue_flame", d0 + d4, d1 + d3, d2));
			world.spawnParticle("smoke", d0 + d4, d1 + d3, d2, 0.0D, 0.0D, 0.0D);
		} 
		
		else if (metadata == 3) {
			Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFusionParticleFX(world, "blue_flame", d0, d1 + d3, d2 - d4));
			world.spawnParticle("smoke", d0, d1 + d3, d2 - d4, 0.0D, 0.0D, 0.0D);
		} 
		
		else if (metadata == 4) {
			Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFusionParticleFX(world, "blue_flame", d0, d1 + d3, d2 + d4));
			world.spawnParticle("smoke", d0, d1 + d3, d2 + d4, 0.0D, 0.0D, 0.0D);
		} 
		
		else {
			Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFusionParticleFX(world, "blue_flame", d0, d1, d2));
			world.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + imagePath);
	}
}

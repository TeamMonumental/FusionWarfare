package calemi.fusionwarfare.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.item.ItemBlockMeta;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChargedPlant extends BlockBase {

	private static final String[] textureNames = new String[] { "growing_grass", "tallgrass", "fern", "growing_deadbush", "deadbush" };
	
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	Random rand = new Random();
	
	public BlockChargedPlant() {
		super("charged_plant", 0, Material.vine, 0, 0, Block.soundTypeGrass, false);
		
		float f = 0.4F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
		
		setTickRandomly(true);
		GameRegistry.registerBlock(this, ItemBlockMeta.class, imagePath);
	}
	
	@Override
	public void updateTick(World w, int x, int y, int z, Random r) {
		
		if (!w.isRemote) {
			
			System.out.println("tick");
			
			if (w.getBlockMetadata(x, y, z) == 3) {		
				w.setBlockMetadataWithNotify(x, y, z, 4, 2);
			}	
			
			if (w.getBlockMetadata(x, y, z) == 0) {		
				w.setBlockMetadataWithNotify(x, y, z, MathHelper.getRandomIntegerInRange(r, 1, 2), 2);
			}	
		}
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z,	Entity entity) {
		
		if (!world.isRemote) {
			
			if (world.getBlockMetadata(x, y, z) != 0 && world.getBlockMetadata(x, y, z) != 3 && entity instanceof EntityPlayer) {		
			
				world.createExplosion(null, x, y, z, 10, false);
				world.setBlockToAir(x, y, z);				
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
        int meta = blockAccess.getBlockMetadata(x, y, z);
        return meta == 0 || meta == 1 || meta == 2 ? blockAccess.getBiomeGenForCoords(x, z).getBiomeGrassColor(x, y, z) : 16777215;
    }
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 1;
    }
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
		
        if (meta >= this.icons.length) {
            meta = 0;
        }

        return this.icons[meta];
	}
	
	@Override
	public Item getItemDropped(int i1, Random p_149650_2_, int i2) {
		return null;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
		return new ItemStack(InitItems.charged_seeds);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconReg) {
		
		this.icons = new IIcon[textureNames.length];

		for (int i = 0; i < this.icons.length; ++i) {
			this.icons[i] = iconReg.registerIcon((i == 0 || i == 3 ? (Reference.MOD_ID + ":") : "") + textureNames[i]);
		}
	}
}

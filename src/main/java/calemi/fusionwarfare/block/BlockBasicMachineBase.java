package calemi.fusionwarfare.block;

import calemi.fusionwarfare.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockBasicMachineBase extends BlockContainerBase {

	public Class tileEntity;
	public boolean isDirectional = false;
	public boolean hasCustomModel = false;

	public String topImage, bottomImage, sideImage1, sideImage2, sideImage3, sideImage4;
	public String particleImage;
	
	float pixel = 1F/16F;

	@SideOnly(Side.CLIENT)
	private IIcon block_top, block_bottom, block_front, block_side_2, block_side_3, block_side_4;
	
	public BlockBasicMachineBase(String name, Class tileEntity) {
		super(name, 2, Material.iron, 6F, 10F, Block.soundTypeMetal);
		this.tileEntity = tileEntity;
		this.topImage = "mech_top_1";
		this.bottomImage = "steel_casing";
		this.sideImage1 = "mech_side";
		this.sideImage2 = "mech_side";
		this.sideImage3 = "mech_side";
		this.sideImage4 = "mech_side";
		this.particleImage = "steel_casing";
	}
		
	public BlockBasicMachineBase setTopImage(String imageName) {	
		topImage = imageName;
		return this;		
	}
	
	public BlockBasicMachineBase setTopImage() {	
		topImage = imagePath + "_top";
		return this;		
	}
	
	public BlockBasicMachineBase setBottomImage(String imageName) {	
		topImage = imageName;
		return this;		
	}
	
	public BlockBasicMachineBase setBottomImage() {	
		bottomImage = imagePath + "_bottom";
		return this;
	}
	
	public BlockBasicMachineBase setAllFourSideImages(String imageName) {	
		sideImage1 = imageName;
		sideImage2 = imageName;
		sideImage3 = imageName;
		sideImage4 = imageName;
		return this;		
	}
	
	public BlockBasicMachineBase setAllFourSideImages() {	
		sideImage1 = imagePath + "_side";
		sideImage2 = imagePath + "_side";
		sideImage3 = imagePath + "_side";
		sideImage4 = imagePath + "_side";
		return this;		
	}
	
	public BlockBasicMachineBase setAllSideImages(String imageName) {	
		topImage = imageName;
		bottomImage = imageName;
		sideImage1 = imageName;
		sideImage2 = imageName;
		sideImage3 = imageName;
		sideImage4 = imageName;
		return this;		
	}
	
	public BlockBasicMachineBase setAllSideImages() {	
		topImage = imagePath + "_side";
		bottomImage = imagePath + "_side";
		sideImage1 = imagePath + "_side";
		sideImage2 = imagePath + "_side";
		sideImage3 = imagePath + "_side";
		sideImage4 = imagePath + "_side";
		return this;		
	}	
	
	public BlockBasicMachineBase setParticleImage() {	
		particleImage = imagePath + "_particle";
		return this;		
	}
	
	public BlockBasicMachineBase setBounds(float xStart, float yStart, float zStart, float xEnd, float yEnd, float zEnd) {
		setBlockBounds(xStart * pixel, yStart * pixel, zStart * pixel, xEnd * pixel, yEnd * pixel, zEnd * pixel);
		return this;
	}
	
	public BlockBasicMachineBase setDirectional() {		
		isDirectional = true;
		return this;
	}
	
	public BlockBasicMachineBase setHasCustomModel() {
		hasCustomModel = true;
		return this;
	}
	
	public int getRenderType() {
		return hasCustomModel ? -1 : super.getRenderType();
	}

	public boolean renderAsNormalBlock() {
		return !hasCustomModel;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {

		if (hasCustomModel) {
			return super.getIcon(side, meta);
		}
		
		if (isDirectional) {

			if (meta == 0 && side == 3) {
				return block_front;
			}

			if (meta == 2 && side == 2) {
				return block_front;
			}

			if (meta == 3 && side == 5) {
				return block_front;
			}

			if (meta == 1 && side == 4) {
				return block_front;
			}
		}
		
		if (side == 0) {
			return block_bottom;
		}

		if (side == 1) {
			return block_top;
		}
				
		if (side == 3) {
			return block_side_2;
		}
		
		if (side == 4) {
			return block_side_3;
		}
		
		if (side == 5) {
			return block_side_4;
		}

		return blockIcon;
	}

	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase e, ItemStack is) {
		
		int l = MathHelper.floor_double((double) (e.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
		
		if (isDirectional) {
			w.setBlockMetadataWithNotify(x, y, z, l, 2);
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconreg) {

		if (hasCustomModel) {
			this.blockIcon = iconreg.registerIcon(Reference.MOD_ID + ":" + particleImage);
		}
		
		else {
			this.block_top = iconreg.registerIcon(Reference.MOD_ID + ":" + topImage);
			this.block_bottom = iconreg.registerIcon(Reference.MOD_ID + ":" + bottomImage);
			if (isDirectional) this.block_front = iconreg.registerIcon(Reference.MOD_ID + ":" + imagePath + "_front");
			this.block_side_2 = iconreg.registerIcon(Reference.MOD_ID + ":" + sideImage2);
			this.block_side_3 = iconreg.registerIcon(Reference.MOD_ID + ":" + sideImage3);
			this.block_side_4 = iconreg.registerIcon(Reference.MOD_ID + ":" + sideImage4);
			this.blockIcon = iconreg.registerIcon(Reference.MOD_ID + ":" + sideImage1);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		
		try {
			return (TileEntity) tileEntity.newInstance();
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		} 
				
		return null;
	}
}

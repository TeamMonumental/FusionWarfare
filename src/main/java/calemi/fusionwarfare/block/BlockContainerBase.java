package calemi.fusionwarfare.block;

import java.util.Random;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockContainerBase extends BlockContainer {

	private final Random random = new Random();
	public String imagePath;

	public BlockContainerBase(String imagePath, int harvestLevel, Material material, float hardness, float resistance, Block.SoundType stepSound, boolean isRegistered) {

		super(material);
		setHarvestLevel("pickaxe", harvestLevel);
		this.imagePath = imagePath;
		if (isRegistered) setCreativeTab(InitCreativeTabs.creativeTabMachines);
		setBlockName(imagePath);
		setStepSound(stepSound);
		setHardness(hardness);
		setResistance(resistance);
		if (isRegistered) GameRegistry.registerBlock(this, imagePath);
	}
	
	public BlockContainerBase(String imagePath, int harvestLevel, Material material, float hardness, float resistance, Block.SoundType stepSound) {
		this(imagePath, harvestLevel, material, hardness, resistance, stepSound, true);
	}
	
	public abstract int getGuiID();
	
	public void breakBlock(World world, int x, int y, int z, Block block, int i) {

		if (!(world.getTileEntity(x, y, z) instanceof TileEntityBase)) {
			return;
		}
		
		TileEntityBase tileentity = (TileEntityBase) world.getTileEntity(x, y, z);
		
		if (tileentity != null) {
			for (int i1 = 0; i1 < tileentity.getSizeInventory(); ++i1) {
				ItemStack itemstack = tileentity.getStackInSlot(i1);

				if (itemstack != null) {
					float f = this.random.nextFloat() * 0.8F + 0.1F;
					float f1 = this.random.nextFloat() * 0.8F + 0.1F;
					EntityItem entityitem;

					for (float f2 = this.random.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
						int j1 = this.random.nextInt(21) + 10;

						if (j1 > itemstack.stackSize) {
							j1 = itemstack.stackSize;
						}

						itemstack.stackSize -= j1;
						entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (double) ((float) this.random.nextGaussian() * f3);
						entityitem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.2F);
						entityitem.motionZ = (double) ((float) this.random.nextGaussian() * f3);

						if (itemstack.hasTagCompound()) {
							entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
						}
					}
				}
			}

			world.func_147453_f(x, y, z, block);
		}

		super.breakBlock(world, x, y, z, block, i);
	}
	
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int i, float f, float f2, float f3) {
		
		if(!w.isRemote) {
			
			TileEntity tileEntity = w.getTileEntity(x, y, z);
			
			if (tileEntity instanceof TileEntitySecurity) {	
	
				if (((TileEntitySecurity)tileEntity).isSameTeam(p)) {
						
					FMLNetworkHandler.openGui(p, FusionWarfare.instance, getGuiID(), w, x, y, z);
				}
			}
			
			else {
				FMLNetworkHandler.openGui(p, FusionWarfare.instance, getGuiID(), w, x, y, z);
			}
		}
		
		return true;	
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(Reference.MOD_ID + ":" + imagePath);
	}
}

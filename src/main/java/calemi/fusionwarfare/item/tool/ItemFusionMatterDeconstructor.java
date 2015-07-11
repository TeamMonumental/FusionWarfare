package calemi.fusionwarfare.item.tool;

import java.util.List;
import java.util.Set;

import calemi.fusionwarfare.Reference;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.init.InitToolMaterials;
import calemi.fusionwarfare.item.IEnergyItem;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemFusionMatterDeconstructor extends ItemTool implements IEnergyItem {

	public String imagePath;
	private static ToolMaterial toolMaterial = InitToolMaterials.toolMaterialFusionMatterDeconstructor;
	
	public ItemFusionMatterDeconstructor() {
		super(4F, InitToolMaterials.toolMaterialFusionMatterDeconstructor, null);
		setCreativeTab(InitCreativeTabs.creativeTabTools);
		this.imagePath = "fusion_matter_deconstructor";
		setUnlocalizedName(this.imagePath);
		GameRegistry.registerItem(this, this.imagePath);
		setMaxStackSize(1);		
		setMaxDamage(getMaxEnergy());		
	}
	
	@Override
	public int getMaxEnergy() {
		return 5000;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister p_94581_1_) {}
	
	public NBTTagCompound getNBT(ItemStack is) {
		if (is.getTagCompound() == null) {
			is.setTagCompound(new NBTTagCompound());
		}

		return is.getTagCompound();
	}

	public boolean onBlockDestroyed(ItemStack is, World world, Block block, int x, int y, int z, EntityLivingBase entity) {

		if ((double) block.getBlockHardness(world, x, y, z) != 0.0D) {
			
			is.damageItem(10, entity);
		}

		return true;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		return getEnergy(stack) == 0;
	}
	
	public boolean hitEntity(ItemStack is, EntityLivingBase hitEntity, EntityLivingBase entity) {
		is.damageItem(10, entity);
		return false;
	}
	
	@Override
	public float getDigSpeed(ItemStack is, Block block, int meta) {

		if (getEnergy(is) == 0) return 0;

		return func_150893_a(is, block);
	}

	public int getItemEnchantability() {
		return this.toolMaterial.getEnchantability();
	}

	public EnumAction getItemUseAction(ItemStack is) {
		return EnumAction.block;
	}

	public int getMaxItemUseDuration(ItemStack is) {
		return 72000;
	}

	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		player.setItemInUse(is, this.getMaxItemUseDuration(is));
		return is;
	}

	public boolean func_150897_b(Block block) {

		return block == Blocks.snow ? true : block == Blocks.snow_layer ? true : block == Blocks.web ? true : block == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (block != Blocks.diamond_block && block != Blocks.diamond_ore ? (block != Blocks.emerald_ore && block != Blocks.emerald_block ? (block != Blocks.gold_block && block != Blocks.gold_ore ? (block != Blocks.iron_block && block != Blocks.iron_ore ? (block != Blocks.lapis_block && block != Blocks.lapis_ore ? (block != Blocks.redstone_ore && block != Blocks.redstone_ore ? (block.getMaterial() == Material.rock ? true : (block.getMaterial() == Material.iron ? true : block.getMaterial() == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
	}

	public float func_150893_a(ItemStack is, Block block) {

		return toolMaterial.getEfficiencyOnProperMaterial();
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(new ItemStack(item, 1, getMaxDamage()));
	}

	@Override
	public int getEnergy(ItemStack stack) {
		stack.setItemDamage(getMaxEnergy() - getNBT(stack).getInteger("energy"));
		return getNBT(stack).getInteger("energy");
	}

	@Override
	public void setEnergy(ItemStack stack, int energy) {
		getNBT(stack).setInteger("energy", energy);;
	}

	@Override
	public boolean addEnergy(ItemStack stack, int energy) {		
		
		if ((getMaxEnergy() - getEnergy(stack)) >= energy) {
			
			setEnergy(stack, getEnergy(stack) + energy);
			return true;
		}
		
		return false;	
	}

	@Override
	public boolean subtractEnergy(ItemStack stack, int energy) {
		
		if (getEnergy(stack) >= energy) {
			
			setEnergy(stack, getEnergy(stack) - energy);
			return true;
		}
		
		return false;
	}	
}
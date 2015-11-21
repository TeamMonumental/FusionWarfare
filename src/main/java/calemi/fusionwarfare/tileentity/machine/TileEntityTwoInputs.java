package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.gui.GuiTwoInputs;
import calemi.fusionwarfare.inventory.ContainerTwoInputs;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityTwoInputs extends TileEntityEnergyBase implements ITileEntityGuiHandler {

	public TwoInputRecipe currentRecipe;
	public EnumRecipeType recipeType;
	
	public TileEntityTwoInputs() {
		maxProgress = 100;
		maxEnergy = 25000;
	}
	
	public TileEntityTwoInputs(EnumRecipeType recipeType) {
		maxProgress = 100;
		this.recipeType = recipeType;
		maxEnergy = (recipeType == EnumRecipeType.INFUSION_TABLE ? 10000 : 5000);
	}
	
	@Override
	public void updateEntity() {
		
		checkForRecipe();
		
		if (!worldObj.isRemote) {
			
			if (!isDone() && currentRecipe != null && EnergyUtil.canSubtractEnergy(this, currentRecipe.energyCost)) {				
				progress++;
			} 
			
			else resetProgress();
			
			if (isDone()) {
				
				resetProgress();
				EnergyUtil.subtractEnergy(this, currentRecipe.energyCost);
				
				decrStackSize(0, currentRecipe.input1.stackSize);
				decrStackSize(1, currentRecipe.input2.stackSize);
				
				if (slots[2] == null) slots[2] = currentRecipe.output.copy();				
				else slots[2].stackSize += currentRecipe.output.copy().stackSize; 
			}
		}
	}
	
	private boolean canFit(TwoInputRecipe recipe) {
		
		ItemStack output = recipe.output.copy();
		
		if (slots[2] == null) return true;
		if (output.getItem() != slots[2].getItem()) return false;
		
		return (slots[2].getMaxStackSize() - slots[2].stackSize) >= output.stackSize;
	}
	
	private void checkForRecipe() {
		
		if (recipeType != null) {
			
			for (TwoInputRecipe recipe : TwoInputRecipeRegistry.getRecipes(recipeType)) {
			
				if (recipe.isRecipe(slots[0], slots[1]) && canFit(recipe)) {					
					currentRecipe = recipe;	
					maxProgress = recipe.progressTime;
					return;
				}			
			}
		}	
		
		currentRecipe = null;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(0, 1, 0);
	}
	
	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side) {
		return (side == 1 && slot == 0) || (side != 1 && side != 0 && slot == 1);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side) {
		return side == 0 && slot == 2;
	}

	@Override
	public int getSizeInventory() {
		return 4;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return true;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.INPUT;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return slots[3];
	}
	
	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerTwoInputs(player, this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiTwoInputs(player, this, recipeType);
	}
}

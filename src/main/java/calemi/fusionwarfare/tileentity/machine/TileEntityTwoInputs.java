package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityTwoInputs extends TileEntityBase {

	public TwoInputRecipe currentRecipe;
	public EnumRecipeType recipeType;
	
	public TileEntityTwoInputs() {
		maxEnergy = 10000;
		maxProgress = 100;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
			
		maxEnergy = recipeType == EnumRecipeType.INFUSION_TABLE ? 10000 : 5000;
		
		System.out.println(getMaxProgress());
		
		checkForRecipe();
		
		if (!worldObj.isRemote) {
			
			if (currentRecipe != null && energy >= currentRecipe.energyCost) {				
				progress++;
			} 
			
			else {
				resetProgress();
			}
			
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
	
	//--------------------------------------------------------------------
	
	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {0,1,2};
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
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	
		nbt.setString("recipeType", recipeType.toString());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	
		recipeType = recipeType.valueOf(nbt.getString("recipeType"));
	}
	
	//-----------------------------------------------------------
	
	@Override
	public Packet getDescriptionPacket() {
		
		super.getDescriptionPacket();
		
		NBTTagCompound syncData = new NBTTagCompound();
	
		syncData.setString("recipeType", recipeType.toString());

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, syncData);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		
		super.onDataPacket(net, pkt);
		
		recipeType = recipeType.valueOf(pkt.func_148857_g().getString("recipeType"));		
	}
}

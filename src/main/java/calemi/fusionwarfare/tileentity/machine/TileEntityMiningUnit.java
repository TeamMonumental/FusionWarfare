package calemi.fusionwarfare.tileentity.machine;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import calemi.fusionwarfare.tileentity.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.util.EnergyUtil;
import calemi.fusionwarfare.util.Location;

public class TileEntityMiningUnit extends TileEntityBase {

	private int energyCost = 35;

	public TileEntityMiningUnit() {
		maxEnergy = 5000;
		maxProgress = 30;
	}

	@Override
	public void updateEntity() {
		super.updateEntity();

		if (!worldObj.isRemote) {

			if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && energy >= energyCost) {
				progress++;
			}

			else {
				resetProgress();
			}

			if (isDone()) {

				resetProgress();

				Location location = findBlock();

				if (location != null) {
					breakBlock(location);
					EnergyUtil.subtractEnergy(this, energyCost);
				}
			}
		}
	}

	private boolean isOre(ItemStack stack) {
		
		String name = OreDictionary.getOreName(OreDictionary.getOreID(stack));		
		return name.startsWith("ore");
	}
	
	private Location findBlock() {

		int range = 10;
		
		for (int y = yCoord - 1; y > 1; y--) {
			for (int x = xCoord - range; x <= xCoord + range; x++) {
				for (int z = zCoord - range; z <= zCoord + range; z++) {

					Location location = new Location(worldObj, x, y, z);

					if (isOre(new ItemStack(location.getBlock(), 1, location.getBlockMetadata()))) {
						return location;
					}					
				}
			}
		}

		return null;
	}

	private boolean addItemToInv(ItemStack stack) {

		for (int i = 0; i < slots.length; i++) {

			if (slots[i] == null) {				
				slots[i] = stack;	
				return true;
			}
			
			if (slots[i].isItemEqual(stack) && getSpace(i) >= 1) {				
				slots[i].stackSize++;
				return true;
			}		
		}	
		
		return false;
	}
	
	private int getSpace(int i) {
		return slots[i].getMaxStackSize() - slots[i].stackSize;				
	}

	private void breakBlock(Location location) {

		ItemStack stack = new ItemStack(location.getBlock());

		if (!addItemToInv(stack)) {

			EntityItem item = new EntityItem(location.world);
			item.setEntityItemStack(stack);
			item.setPosition(xCoord, yCoord + 1, zCoord);
			item.setVelocity(0, 0, 0);
			location.world.spawnEntityInWorld(item);			
		}

		worldObj.setBlock(location.x, location.y, location.z, Blocks.stone);
	}

	// -----------------------------------------------------------------------------------------------

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		return new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return false;
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack item, int side) {		
		return (slot != 21 && side == 0);
	}

	@Override
	public int getSizeInventory() {
		return 22;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		return true;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.INPUT;
	}

	@Override
	public ItemStack getOverclockingSlot() {
		return slots[21];
	}
}

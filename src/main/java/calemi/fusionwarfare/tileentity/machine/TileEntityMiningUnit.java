package calemi.fusionwarfare.tileentity.machine;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.gui.GuiMiningUnit;
import calemi.fusionwarfare.inventory.ContainerMiningUnit;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.util.Location;

public class TileEntityMiningUnit extends TileEntityEnergyBase implements ITileEntityGuiHandler {

	private int energyCost = 50;

	public TileEntityMiningUnit() {
		maxEnergy = 10000;
		maxProgress = 30;
	}

	@Override
	public void updateEntity() {

		if (!worldObj.isRemote) {

			if (!isDone() && EnergyUtil.canSubtractEnergy(this, energyCost) && worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
				progress++;
			}

			else resetProgress();

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
		
		for (int id : OreDictionary.getOreIDs(stack)) {
			
			String name = OreDictionary.getOreName(id);
			return name.startsWith("ore");
		}		
		
		return false;		
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

		ItemStack stack = new ItemStack(location.getBlock(), 1, location.getBlockMetadata());

		if (!addItemToInv(stack)) {

			EntityItem item = new EntityItem(location.world);
			item.setEntityItemStack(stack);
			item.setPosition(xCoord, yCoord + 1, zCoord);
			item.setVelocity(0, 0, 0);
			location.world.spawnEntityInWorld(item);			
		}

		worldObj.setBlock(location.x, location.y, location.z, Blocks.stone);
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

	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerMiningUnit(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiMiningUnit(player, this);
	}
}

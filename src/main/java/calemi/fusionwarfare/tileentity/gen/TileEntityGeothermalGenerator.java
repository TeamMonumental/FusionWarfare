package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.gui.GuiEnergyTank;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.util.Location;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class TileEntityGeothermalGenerator extends TileEntityEnergyBase implements ITileEntityGuiHandler {

	public TileEntityGeothermalGenerator() {
		maxEnergy = 750;
	}

	@Override
	public void updateEntity() {

		if (!worldObj.isRemote) {
			
			if (worldObj.getTotalWorldTime() % 40L == 0L) {			
				findLava();
			}		
		}
	}

	private void findLava() {

		for (int x = xCoord - 1; x <= xCoord + 1; x++) {
			for (int z = zCoord - 1; z <= zCoord + 1; z++) {
				
				Location location = new Location(worldObj, x, yCoord, z);

				if (location.getBlock() == Blocks.lava && location.getBlockMetadata() == 0 ) {
					EnergyUtil.addEnergy(this, 5);
				}
			}
		}
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public EnumIO getIOType() {
		return EnumIO.OUTPUT;
	}
	
	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
	
	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerEnergyTank(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiEnergyTank(player, this, "Geothermal Generator", false);
	}
}

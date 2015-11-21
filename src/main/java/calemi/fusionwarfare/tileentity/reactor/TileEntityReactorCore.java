package calemi.fusionwarfare.tileentity.reactor;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenOcean;
import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.gui.GuiReactorCore;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitItems;
import calemi.fusionwarfare.inventory.ContainerReactorCore;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.util.Location;
import calemi.fusionwarfare.util.ShapeUtil;

public class TileEntityReactorCore extends TileEntityEnergyBase implements ITileEntityGuiHandler {

	private boolean isAssembled;

	public TileEntityReactorCore() {
		maxEnergy = 50000;
	}

	@Override
	public void updateEntity() {

		int r = 2;

		if (!worldObj.isRemote) {

			if (worldObj.getTotalWorldTime() % 20L == 0L) {
				
				isAssembled = isCorrectStructure();

				if (isAssembled && hasEnoughWater()) {

					for (Location l : ShapeUtil.getCube(worldObj, xCoord, yCoord, zCoord, 1, 1, 1)) {

						if (l.getBlock() == Blocks.air) {
							l.setBlock(Blocks.water, 0);
						}
					}
				}

				for (Location l : ShapeUtil.getCube(worldObj, xCoord, yCoord, zCoord, r, r, r)) {

					if (l.getTileEntity() instanceof TileEntitySteelCasing) {

						TileEntitySteelCasing tileEntity = (TileEntitySteelCasing) l.getTileEntity();

						if (isAssembled) {
							tileEntity.x = xCoord;
							tileEntity.y = yCoord;
							tileEntity.z = zCoord;
						}

						else {
							tileEntity.x = 0;
							tileEntity.y = 0;
							tileEntity.z = 0;
						}
					}
				}
			}

			if (isAssembled && worldObj.getBiomeGenForCoords(xCoord, zCoord) instanceof BiomeGenOcean && hasEnoughWater()) {

				for (int i = 0; i < slots.length; i++) {
					
					if (slots[i] != null && slots[i].getItem() == InitItems.advanced_infused_catalyst) {

						if (slots[i].getItemDamage() >= slots[i].getMaxDamage()) {
							decrStackSize(i, 1);
							break;
						}
					
						if (EnergyUtil.canAddEnergy(this, 5)) {

							slots[i].setItemDamage(slots[i].getItemDamage() + 1);
							EnergyUtil.addEnergy(this, 5);
						}
						
						break;
					}
				}			
			}
		}
	}

	private boolean hasEnoughWater() {

		for (int y = yCoord + 4; y < (yCoord + 4) + 20; y++) {

			Location loc = new Location(worldObj, xCoord, y, zCoord);

			if (loc.getBlock() != Blocks.water || loc.getBlockMetadata() > 0) {
				return false;
			}
		}

		return true;
	}

	private boolean isCorrectStructure() {

		int r = 2;

		int top = yCoord + r;
		int bottom = yCoord - r;
		int minX = xCoord - r;
		int maxX = xCoord + r;
		int minZ = zCoord - r;
		int maxZ = zCoord + r;

		Location coolingUnit = new Location(worldObj, xCoord, yCoord + 3, zCoord);

		if (!coolingUnit.compareBlocks(InitBlocks.reactor_cooling_unit)) {
			return false;
		}

		for (Location l : ShapeUtil.getCube(worldObj, xCoord, yCoord, zCoord, r, r, r)) {

			if (l.y == top || l.y == bottom) {

				if (l.getBlock() != InitBlocks.steel_casing) {
					return false;
				}
			}

			else if ((l.x == minX || l.x == maxX) && (l.z == minZ || l.z == maxZ)) {

				if (l.getBlock() != InitBlocks.steel_casing) {
					return false;
				}
			}

			else if ((l.x == minX || l.x == maxX) || (l.z == minZ || l.z == maxZ)) {

				if (l.getBlock() != InitBlocks.reinforced_glass) {
					return false;
				}
			}
		}

		return true;
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
	public int getSizeInventory() {
		return 6;
	}
	
	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerReactorCore(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiReactorCore(player, this, "Reactor");
	}
}

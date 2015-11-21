package calemi.fusionwarfare.tileentity.gen;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.gui.GuiEnergyTank;
import calemi.fusionwarfare.inventory.ContainerEnergyTank;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenMesa;

public class TileEntitySolarGenerator extends TileEntityEnergyBase implements ITileEntityGuiHandler {

	public TileEntitySolarGenerator() {
		maxEnergy = 500;
	}

	@Override
	public void updateEntity() {
		
		if (!worldObj.isRemote) {

			if (worldObj.getTotalWorldTime() % 40L == 0L) {

				if (worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord) && !worldObj.isRaining()) {

					int lightValue = worldObj.getSavedLightValue(EnumSkyBlock.Sky, xCoord, yCoord + 1, zCoord) - (worldObj.skylightSubtracted);

					if (lightValue > 12) {
						
						if (worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature > 1.5F) {
							EnergyUtil.addEnergy(this, 10);
						}
						
						EnergyUtil.addEnergy(this, 15);
					}
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
	@SideOnly(Side.CLIENT)
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiEnergyTank(player, this, "Solar Generator", false);
	}
}

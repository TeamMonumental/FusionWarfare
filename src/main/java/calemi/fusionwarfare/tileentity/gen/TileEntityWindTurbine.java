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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.biome.BiomeGenOcean;

public class TileEntityWindTurbine extends TileEntityEnergyBase implements ITileEntityGuiHandler {

	public float speed, rotation;

	public TileEntityWindTurbine() {
		maxEnergy = 750;
	}

	@Override
	public void updateEntity() {

		rotation += speed;

		if (yCoord >= 75 && worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord)) {

			speed = 5F;

			if (worldObj.getTotalWorldTime() % 40L == 0L) {

				if (!worldObj.isRemote)
					EnergyUtil.addEnergy(this, 30);

				if (worldObj.getBiomeGenForCoords(xCoord, zCoord) instanceof BiomeGenOcean) {
					speed = 8F;
					if (!worldObj.isRemote)
					EnergyUtil.addEnergy(this, 10);
				}
			}
		}

		else {
			speed = 0;
		}
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1).expand(1, 1, 1);
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
		return new GuiEnergyTank(player, this, "Wind Generator", false);
	}
}

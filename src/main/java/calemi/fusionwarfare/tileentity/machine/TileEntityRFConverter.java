package calemi.fusionwarfare.tileentity.machine;

import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.gui.GuiRFConverter;
import calemi.fusionwarfare.inventory.ContainerRFConverter;
import calemi.fusionwarfare.recipe.EnumRecipeType;
import calemi.fusionwarfare.recipe.TwoInputRecipe;
import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import calemi.fusionwarfare.tileentity.ITileEntityGuiHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import calemi.fusionwarfare.util.Location;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cofh.api.energy.IEnergyStorage;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityRFConverter extends TileEntityEnergyBase implements IEnergyHandler, ITileEntityGuiHandler {

	public EnergyStorage storage = new EnergyStorage(500000, 25000, 25000);

	public boolean outputFE = false;

	public static final int FERatio = 5;
	public static final int RFRatio = 500;
	
	private int overclock;
	
	public TileEntityRFConverter() {
		maxEnergy = 5000;
	}

	@Override
	public void updateEntity() {
				
		if (!worldObj.isRemote) {
					
			if (outputFE) {

				overclock = getCurrentRFScaled(40);
					
				for (int i = 0; i <= overclock; i++) {
									
					if (EnergyUtil.canAddEnergy(this, FERatio) && storage.getEnergyStored() >= RFRatio) {

						EnergyUtil.addEnergy(this, FERatio);
						storage.extractEnergy(RFRatio, false);
					}
					
					else break;
				}
			}

			else {
								
				overclock = getCurrentEnergyScaled(40);
								
				for (int i = 0; i <= overclock; i++) {
					
					if (EnergyUtil.canSubtractEnergy(this, FERatio) && (storage.getMaxEnergyStored() - storage.getEnergyStored()) >= RFRatio) {

						EnergyUtil.subtractEnergy(this, FERatio);
						storage.receiveEnergy(RFRatio, false);
					}
					
					else break;
				}			
				
				for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
					
					Location loc = new Location(worldObj, xCoord, yCoord, zCoord).add(dir);
					TileEntity entity = loc.getTileEntity();
					
					if (entity != null && entity instanceof IEnergyReceiver) {
						
						IEnergyReceiver receiver = (IEnergyReceiver) entity;
						
						int maxExtract = storage.getMaxExtract(); //Gets the maximum amount of energy that can be extracted from this tile in one tick.
						int maxAvailable = storage.extractEnergy(maxExtract, true); //Simulates removing "maxExtract" to find out how much energy is actually available.
						int energyTransferred = receiver.receiveEnergy(dir.getOpposite(), maxAvailable, false); //Sends "maxAvailable" to the target tile and records how much energy was accepted. 

						storage.extractEnergy(energyTransferred, false);//Extract the energy transferred from the internal storage.
					}
				}
			}
		}
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return storage.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return storage.getMaxEnergyStored();
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	public int getCurrentRFScaled(int i) {
		return storage.getEnergyStored() * i / storage.getMaxEnergyStored();
	}

	public void toggle() {
		outputFE = !outputFE;
	}

	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public EnumIO getIOType() {
		return outputFE ? EnumIO.OUTPUT : EnumIO.INPUT;
	}

	@Override
	public ItemStack getOverclockingSlot() {
		return null;
	}
	
	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		outputFE = nbt.getBoolean("outputFE");
		storage.readFromNBT(nbt);
	}

	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
		nbt.setBoolean("outputFE", outputFE);
		storage.writeToNBT(nbt);
	}
	
	@Override
	public Container getTileContainer(EntityPlayer player) {
		return new ContainerRFConverter(player, this);
	}

	@Override
	public GuiContainer getTileGuiContainer(EntityPlayer player) {
		return new GuiRFConverter(player, this);
	}
}

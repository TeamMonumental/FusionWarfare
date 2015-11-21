package calemi.fusionwarfare.inventory;

import java.util.ArrayList;
import java.util.Iterator;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.packet.ClientPacketHandler;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerEnergyBase extends ContainerBase {

	private int lastProgressTime;
	
	public TileEntityEnergyBase tileEntityEnergy = (TileEntityEnergyBase)tileEntity;
	
	public ContainerEnergyBase(EntityPlayer player, TileEntityEnergyBase tileEntity) {
		super(player, tileEntity);
	}

	public void addCraftingToCrafters(ICrafting craft) {
		super.addCraftingToCrafters(craft);

		craft.sendProgressBarUpdate(this, 1, tileEntityEnergy.progress);
	}
	
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		if (player instanceof EntityPlayerMP) {
			FusionWarfare.network.sendTo(new ClientPacketHandler("sync.fusion%" + tileEntityEnergy.energy), (EntityPlayerMP) player);
		}
		
		for (int i = 0; i < this.crafters.size(); ++i) {
			
			ICrafting craft = (ICrafting) this.crafters.get(i);
			
			if (this.lastProgressTime != tileEntityEnergy.progress) {
				craft.sendProgressBarUpdate(this, 1, tileEntityEnergy.progress);
			}
		}
		
		lastProgressTime = tileEntityEnergy.progress;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int i2) {
		
		if (i == 0) tileEntityEnergy.energy = i2;		
		if (i == 1) tileEntityEnergy.progress = i2;
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer p, int i) {
		return null;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return true;
	}
}

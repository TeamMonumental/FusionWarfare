package calemi.fusionwarfare.inventory;

import java.util.ArrayList;
import java.util.Iterator;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.packet.ClientPacketHandler;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerBase extends Container {

	public TileEntityBase fusion;
	public EntityPlayer player;

	private int lastEnergyTime;
	private int lastProgressTime;
	
	public ContainerBase(EntityPlayer player, TileEntityBase tileEntity) {
		this.fusion = tileEntity;
		this.player = player;
	}
	
	public void addPlayerInv(int x, int y) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, x + (j * 18), y + (i * 18)));
			}
		}
	}

	public void addHotbar(int x, int y) {
		for (int i = 0; i < 9; i++) {
			this.addSlotToContainer(new Slot(player.inventory, i, x + i * 18, y));
		}
	}

	public void addCraftingToCrafters(ICrafting craft) {
		super.addCraftingToCrafters(craft);

		//craft.sendProgressBarUpdate(this, 0, fusion.energy);
		craft.sendProgressBarUpdate(this, 1, fusion.progress);
	}
	
	public void detectAndSendChanges() {

		super.detectAndSendChanges();

		FusionWarfare.network.sendTo(new ClientPacketHandler("sync%" + fusion.energy), (EntityPlayerMP) player);
		
		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting craft = (ICrafting) this.crafters.get(i);

			/*if (this.lastEnergyTime != fusion.energy) {
				craft.sendProgressBarUpdate(this, 0, fusion.energy);
			}*/
			
			if (this.lastProgressTime != fusion.progress) {
				craft.sendProgressBarUpdate(this, 1, fusion.progress);
			}
		}
		
		//lastEnergyTime = fusion.energy;
		lastProgressTime = fusion.progress;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int i, int i2) {
		if (i == 0) {
			fusion.energy = i2;
		}
		
		if (i == 1) {
			fusion.progress = i2;
		}
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

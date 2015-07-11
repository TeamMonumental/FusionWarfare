package calemi.fusionwarfare.inventory;

import java.util.ArrayList;
import java.util.Iterator;

import calemi.fusionwarfare.tileentity.TileEntityBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerTwoInputs extends ContainerBase {

	public ContainerTwoInputs(EntityPlayer player, TileEntityBase tileentity) {
		super(player, tileentity);	
		
		//new Slot(inv, id, x, y)
		
		//Gui's Slots

		//Input Slot
		addSlotToContainer(new Slot(tileentity, 0, 56, 25));
		
		//Input Slot 2
		addSlotToContainer(new Slot(tileentity, 1, 56, 47));
		
		//Output Slot
		addSlotToContainer(new SlotOutput(tileentity, 2, 116, 36));	

		//Overclocking
		addSlotToContainer(new SlotOverclocking(tileentity, 3, 152, 62, 15));
		
		addPlayerInv(8, 84);		
		addHotbar(8, 142);		
	}
}
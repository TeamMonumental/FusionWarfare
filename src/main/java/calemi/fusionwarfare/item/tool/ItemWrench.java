package calemi.fusionwarfare.item.tool;

import java.util.List;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.block.BlockNetworkController;
import calemi.fusionwarfare.block.BlockReinforcedDoor;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.item.ItemBase;
import calemi.fusionwarfare.item.ItemBlockEnergyBase;
import calemi.fusionwarfare.tileentity.TileEntityBase;
import calemi.fusionwarfare.tileentity.TileEntityReinforcedDoor;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkController;
import calemi.fusionwarfare.tileentity.reactor.TileEntitySteelCasing;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemWrench extends ItemBase {

	public ItemWrench() {
		super("wrench");
		setCreativeTab(InitCreativeTabs.creativeTabTools);
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List l, boolean b) {
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
				
			l.add("Drops machines that are");
			l.add("right-clicked and also holds energy");
			l.add("from Network Controllers.");
				
		} else {
			l.add("Press " + EnumChatFormatting.GOLD + "SHIFT" + EnumChatFormatting.RESET + EnumChatFormatting.GRAY + " for more info");
		}				
	}
	
	@Override
	public boolean isFull3D() {
		return true;
	}
	
	/*@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int m, float f1, float f2, float f3) {
		
		if (!w.isRemote && p.isSneaking()) {
			
			if (w.getTileEntity(x, y, z) instanceof TileEntitySteelCasing) {
				
				Block block = w.getBlock(x, y, z);
		
				w.setBlockToAir(x, y, z);
				block.dropBlockAsItem(w, x, y, z, m, 2);
				
				return true;
			}
			
			if (w.getTileEntity(x, y, z) instanceof TileEntityBase && !(w.getTileEntity(x, y, z) instanceof TileEntityReinforcedDoor)) {				
				
				Block block = w.getBlock(x, y, z);
				
				TileEntityBase tileEntityBase = (TileEntityBase)w.getTileEntity(x, y, z);
			
				if (tileEntityBase instanceof TileEntityNetworkController) {
					
					block.breakBlock(w, x, y, z, block, m);	
					w.setBlockToAir(x, y, z);
					
					ItemStack itemStack = new ItemStack(block);
					((ItemBlockEnergyBase)itemStack.getItem()).setEnergy(tileEntityBase.getEnergy(), itemStack);
					((ItemBlockEnergyBase)itemStack.getItem()).setMaxEnergy(tileEntityBase.getMaxEnergy(), itemStack);
					
					EntityItem droppedItem = new EntityItem(w, x, y, z);
					droppedItem.setEntityItemStack(itemStack);
					w.spawnEntityInWorld(droppedItem);
					
					return true;
				}
				
				if (tileEntityBase instanceof TileEntitySecurity) {
					
					TileEntitySecurity tileEntitySecurity = (TileEntitySecurity)w.getTileEntity(x, y, z);
					
					if (tileEntitySecurity.isSameTeam(p.getTeam())) {					
						
						w.setBlockToAir(x, y, z);
						block.dropBlockAsItem(w, x, y, z, m, 2);
						return true;
					}
					
					else {
						p.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This unit belongs to " + tileEntitySecurity.teamName));
					}
				}
				
				else {
					
					w.setBlockToAir(x, y, z);
					block.dropBlockAsItem(w, x, y, z, m, 2);
				}
				
				return true;
			}
		}	
		
		return false;
	}*/
}

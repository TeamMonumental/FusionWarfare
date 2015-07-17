package calemi.fusionwarfare.item;

import java.util.List;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.init.InitBlocks;
import calemi.fusionwarfare.init.InitCreativeTabs;
import calemi.fusionwarfare.packet.ServerPacketHandler;
import calemi.fusionwarfare.tileentity.machine.TileEntityMissileLauncher;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemLaserDesignator extends ItemBase {
	
	public ItemLaserDesignator() {
		super("designator");
		setCreativeTab(InitCreativeTabs.creativeTabTools);
		setMaxStackSize(1);
	}
	
	@Override
	public void addInformation(ItemStack is, EntityPlayer p, List l, boolean b) {
		
		int x = getNBT(is).getInteger("X");
		int y = getNBT(is).getInteger("Y");
		int z = getNBT(is).getInteger("Z");
		
		l.add("Lancher X: " + x);
		l.add("Lancher Y: " + y);
		l.add("Lancher Z: " + z);
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer p, World w, int x, int y, int z, int side, float f1, float f2, float f3) {
	
		if (p.isSneaking()) {
			
			Block block = w.getBlock(x, y, z);
			
			if (block == InitBlocks.missile_launcher || block == InitBlocks.missile_silo_core) {
				getNBT(is).setInteger("X", x);
				getNBT(is).setInteger("Y", y);
				getNBT(is).setInteger("Z", z);
				
				p.worldObj.playSoundAtEntity(p, "random.orb", 1F, 1F);	
			} 
		}
		
		return false;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p) {

		int x = getNBT(is).getInteger("X");
		int y = getNBT(is).getInteger("Y");
		int z = getNBT(is).getInteger("Z");
		
		if (!p.isSneaking()) {
			
			MovingObjectPosition mop = w.rayTraceBlocks(p.getPosition(100), p.getLook(100));		
					
			int tx = mop.blockX;
			int ty = mop.blockY;
			int tz = mop.blockZ;
			
			w.createExplosion(p, tx, ty, tz, 10, false);
			
			TileEntity tileEntity = w.getTileEntity(x, y, z);
			
			if (tileEntity != null && tileEntity instanceof TileEntityMissileLauncher) {
				
				TileEntityMissileLauncher launcher = (TileEntityMissileLauncher) tileEntity;
				
				launcher.targetX = tx;
				launcher.targetZ = tz;
				
				launcher.forceLaunch = true;
				
				p.worldObj.playSoundAtEntity(p, "mob.wither.spawn", 1F, 1F);	
			}
		}
		
		return is;
	}
}
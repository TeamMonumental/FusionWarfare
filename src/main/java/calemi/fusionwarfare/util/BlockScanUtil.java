package calemi.fusionwarfare.util;

import java.util.ArrayList;
import java.util.List;

import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.api.IEnergy;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockScanUtil {

	public static List<IEnergy> scan(Location loc) {
		List<IEnergy> tempList = new ArrayList<IEnergy>();
		
		for (ForgeDirection dir : ForgeDirection.values()) {
			checkBlock(loc, tempList);
		}
		
		return tempList;
	}
	
	private static void checkBlock(Location loc, List<IEnergy> list) {
		
		TileEntity entity = loc.getTileEntity();
		
		if (entity != null && entity instanceof IEnergy) {
		
			IEnergy energy = (IEnergy) entity;
			
			if (!list.contains(entity) && energy.getIOType() != EnumIO.REJECTED) {
				
				list.add(energy);
				
				for (ForgeDirection dir : ForgeDirection.values()) {
					
					checkBlock(loc.add(dir), list);
				}
			}
		}
	}
}
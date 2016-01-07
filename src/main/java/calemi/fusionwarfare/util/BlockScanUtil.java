package calemi.fusionwarfare.util;

import java.util.ArrayList;
import java.util.List;

import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.api.IEnergy;
import calemi.fusionwarfare.api.INetwork;
import calemi.fusionwarfare.tileentity.network.TileEntityNetworkBeacon;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockScanUtil {

	public static List<INetwork> scan(Location loc) {
		List<INetwork> tempList = new ArrayList<INetwork>();
		
		for (ForgeDirection dir : ForgeDirection.values()) {
			checkBlock(loc, tempList);
		}
		
		return tempList;
	}
	
	private static void checkBlock(Location loc, List<INetwork> list) {
		
		TileEntity tileEntity = loc.getTileEntity();
		
		if (tileEntity != null) {
			
			if (tileEntity instanceof TileEntityNetworkBeacon) {
							
				TileEntityNetworkBeacon tileEntityBeacon = (TileEntityNetworkBeacon)tileEntity;
				
				if (list.contains(tileEntityBeacon)) return;
				
				list.add(tileEntityBeacon);
				
				for (Object obj : loc.world.loadedTileEntityList) {
					
					if (obj instanceof TileEntityNetworkBeacon) {
						
						TileEntityNetworkBeacon targetBeacon = (TileEntityNetworkBeacon)obj;
						
						if (tileEntityBeacon != targetBeacon && tileEntityBeacon.isSameTeam(targetBeacon.getTeam()) && tileEntityBeacon.code == targetBeacon.code) {
							
							System.out.println("found");
							
							list.add(targetBeacon);
							
							for (ForgeDirection dir : ForgeDirection.values()) {
								
								checkBlock(new Location(targetBeacon).add(dir), list);
							}						
						}
					}
				}
			}
			
			else if (tileEntity instanceof INetwork) {		
		
				INetwork energy = (INetwork) tileEntity;
			
				if (!list.contains(tileEntity) && energy.getIOType() != EnumIO.REJECTED) {
				
					list.add(energy);
				
					for (ForgeDirection dir : ForgeDirection.values()) {
					
						checkBlock(loc.add(dir), list);
					}
				}
			}
		}
	}
}
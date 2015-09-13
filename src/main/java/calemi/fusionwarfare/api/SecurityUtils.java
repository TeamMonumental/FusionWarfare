package calemi.fusionwarfare.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class SecurityUtils {

	public static void placeBlock(int x, int y, int z, World w, EntityPlayer p) {
		
		if (p.getTeam() != null) {
		
			TileEntity tileEntity = w.getTileEntity(x, y, z);
			
			if (tileEntity instanceof ISecurity) {
				
				ISecurity security = (ISecurity) tileEntity;
				
				security.setTeam(p.getTeam());
			}
			
		} else {
			
			p.addChatMessage(new ChatComponentText("You are not on a team. No security will be added"));	
		}
	}
}
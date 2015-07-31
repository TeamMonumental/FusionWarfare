package calemi.fusionwarfare.event;

import java.util.List;
import java.util.Random;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.config.FWConfig;
import calemi.fusionwarfare.entity.EntityFallingSupplyCrate;
import calemi.fusionwarfare.packet.ClientPacketHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class SupplyCrateEvent {
	
	private boolean canCallSpawn = true;
	
	Random rand = new Random();
	
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event) {	
		
		if (!FWConfig.disableFallingCrates) {
			
			boolean b = false;
			
			for (Object o : event.world.playerEntities) {
				
				if ((EntityPlayer)o != null) {
					b = true;
				}
			}				
			
			if (b && canCallSpawn && event.world.getCurrentMoonPhaseFactor() == 1.0F && (event.world.getWorldTime() % 24000) > 18000) {
								
				canCallSpawn = false;

				int range = 250;

				int randX = event.world.getSpawnPoint().posX + (rand.nextInt(range * 2) - range);
				int randZ = event.world.getSpawnPoint().posZ + (rand.nextInt(range * 2) - range);

				int randomInt = rand.nextInt(100);

				int meta;

				if (randomInt < 10) meta = 2;
				else if (randomInt < 40) meta = 1;
				else meta = 0;
				
				EntityFallingSupplyCrate entityCrate = new EntityFallingSupplyCrate(meta, event.world, randX, randZ);
				
				if (!event.world.isRemote) {	
					
					event.world.playBroadcastSound(1013, (int) entityCrate.posX, (int) entityCrate.posY, (int) entityCrate.posZ, 0);
					event.world.spawnEntityInWorld(entityCrate);
										
					System.out.println(entityCrate.posX + " " + entityCrate.posZ);
					
					for (Object o : event.world.playerEntities) {
					
						FusionWarfare.network.sendTo(new ClientPacketHandler("broadcast.supplycrates%" + meta + "%" + randX + "%" + randZ), (EntityPlayerMP) o);
					}
				}			
			}

			if (canCallSpawn == false && event.world.getCurrentMoonPhaseFactor() < 1.0F && (event.world.getWorldTime() % 24000) >= 18000) {
				canCallSpawn = true;
				System.out.println("stop");
			}
		}
	}	
}

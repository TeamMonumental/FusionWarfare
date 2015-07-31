package calemi.fusionwarfare.event;

import java.util.List;
import java.util.Random;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.config.FWConfig;
import calemi.fusionwarfare.entity.EntityFallingSupplyCrate;
import calemi.fusionwarfare.packet.ClientPacketHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class SupplyCrateEvent {

	private static boolean canCallSpawn = true;

	Random rand = new Random();

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onPlayerTick(PlayerTickEvent event) {
	
		if (!event.player.worldObj.isRemote && event.player.dimension == 0) {
			
			System.out.println(canCallSpawn);

			if (!FWConfig.disableFallingCrates) {

				World world = event.player.worldObj;
				
				if (canCallSpawn && world.getCurrentMoonPhaseFactor() == 1.0F && (world.getWorldTime() % 24000) > 18000) {

					canCallSpawn = false;

					int range = 250;

					int randX = world.getSpawnPoint().posX + (rand.nextInt(range * 2) - range);
					int randZ = world.getSpawnPoint().posZ + (rand.nextInt(range * 2) - range);

					int randomInt = rand.nextInt(100);

					int meta;

					if (randomInt < 10) meta = 2;
					else if (randomInt < 40) meta = 1;
					else meta = 0;

					EntityFallingSupplyCrate entityCrate = new EntityFallingSupplyCrate(meta, world, randX, randZ);
					
					world.spawnEntityInWorld(entityCrate);

					System.out.println(entityCrate.posX + " " + entityCrate.posZ);

					for (Object o : world.playerEntities) {

						FusionWarfare.network.sendTo(new ClientPacketHandler("broadcast.supplycrates%" + meta + "%" + randX + "%" + randZ), (EntityPlayerMP) o);
					}					
				}

				if (canCallSpawn == false && world.getCurrentMoonPhaseFactor() < 1.0F && (world.getWorldTime() % 24000) >= 18000) {
					canCallSpawn = true;
					System.out.println("reset");
				}
			}
		}

	}
}

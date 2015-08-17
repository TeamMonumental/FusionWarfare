package calemi.fusionwarfare.event;

import java.util.List;
import java.util.Random;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.config.FWConfig;
import calemi.fusionwarfare.entity.EntityFallingSupplyCrate;
import calemi.fusionwarfare.packet.ClientPacketHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class SupplyCrateEvent {

	private static boolean canCallSpawn = true;

	Random rand = new Random();

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
	
		if (!event.world.isRemote && event.world.provider.dimensionId == 0) {
			
			if (!FWConfig.disableFallingCrates) {

				World world = event.world;
				
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

					world.playBroadcastSound(1013, randX, 60, randZ, 0);
					
					if (meta == 0) MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(EnumChatFormatting.AQUA + "A blue supply crate is falling at x" + randX + ", z" + randZ + "!"));
					if (meta == 1) MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(EnumChatFormatting.GOLD + "An orange supply crate is falling at x" + randX + ", z" + randZ + "!!"));
					if (meta == 2) MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "A red supply crate is falling at x" + randX + ", z" + randZ + "!!!"));
									
					//FusionWarfare.network.sendTo(new ClientPacketHandler("broadcast.supplycrates%" + meta + "%" + randX + "%" + randZ), (EntityPlayerMP) o);
				}

				if (canCallSpawn == false && world.getCurrentMoonPhaseFactor() < 1.0F && (world.getWorldTime() % 24000) >= 18000) {
					canCallSpawn = true;
				}
			}
		}

	}
}

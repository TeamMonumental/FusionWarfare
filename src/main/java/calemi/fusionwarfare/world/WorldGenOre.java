package calemi.fusionwarfare.world;

import java.util.Random;

import calemi.fusionwarfare.config.FWConfig;
import calemi.fusionwarfare.init.InitBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenOre implements IWorldGenerator {
	
	public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		
		switch(world.provider.dimensionId){
		case -1:
			generateNether(world, rand, chunkX * 16, chunkZ * 16);
		case 0:
			generateSurface(world, rand, chunkX * 16, chunkZ * 16);		
		}		
	}
	
	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
		
		//i = rarity
		
		if (!FWConfig.disableInfusedCrystalOre) {
			
			for (int i = 0; i < 5; i++) {
			
				int randPosX = chunkX + rand.nextInt(16);
				int randPosY = rand.nextInt(20) + 10;
				int randPosZ = chunkZ + rand.nextInt(16);
		
				(new WorldGenMinable(InitBlocks.infused_crystal_ore, 8)).generate(world, rand, randPosX, randPosY, randPosZ);			
			}
		}
		
		if (!FWConfig.disableInfusedCatalystOre) {
		
			for (int i = 0; i < 3; i++) {
			
				int randPosX = chunkX + rand.nextInt(16);
				int randPosY = rand.nextInt(10) + 10;
				int randPosZ = chunkZ + rand.nextInt(16);
			
				if (world.getBlock(randPosX, randPosY, randPosZ) == Blocks.stone) {	
					world.setBlock(randPosX, randPosY, randPosZ, InitBlocks.infused_catalyst_ore);
				}
			}
		}
		
		if (!FWConfig.disableInfusedAzuriteOre) {
		
			for (int i = 0; i < 5; i++) {
			
				int randPosX = chunkX + rand.nextInt(16);
				int randPosY = rand.nextInt(10) + 30;
				int randPosZ = chunkZ + rand.nextInt(16);
			
				(new WorldGenMinable(InitBlocks.infused_azurite_ore, 8)).generate(world, rand, randPosX, randPosY, randPosZ);			
			}		
		}
	}

	private void generateNether(World world, Random rand, int i, int j) {}
}
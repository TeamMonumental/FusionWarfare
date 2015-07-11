package calemi.fusionwarfare.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ShapeUtil {

	public static List<Location> getSphere(World world, int x, int y, int z, int r) {

		ArrayList<Location> l = new ArrayList<Location>();

		for (int xx = -r; xx <= r; xx++) {
			for (int yy = -r; yy <= r; yy++) {
				for (int zz = -r; zz <= r; zz++) {

					double rx = (xx + x);
					double ry = (yy + y);
					double rz = (zz + z);

					double dx = (x - rx) * (x - rx);
					double dy = (y - ry) * (y - ry);
					double dz = (z - rz) * (z - rz);

					double dis = Math.sqrt(dx + dy + dz);

					if (dis <= (r)) {

						Location loc = new Location(world, xx + x, yy + y, zz + z);
						l.add(loc);
					}
				}
			}
		}

		return l;
	}

	public static List<Location> getCircle(World world, int x, int y, int z, int r) {

		ArrayList<Location> l = new ArrayList<Location>();

		for (int xx = -r; xx <= r; xx++) {
			for (int zz = -r; zz <= r; zz++) {

				double rx = (xx + x);
				double rz = (zz + z);

				double dx = (x - rx) * (x - rx);
				double dz = (z - rz) * (z - rz);

				double dis = Math.sqrt(dx + dz);

				if (dis <= (r)) {

					Location loc = new Location(world, xx + x, y, zz + z);
					l.add(loc);
				}
			}
		}

		return l;
	}
	
	public static List<Location> getCube(World world, int x, int y, int z, int xr, int yr, int zr) {

		ArrayList<Location> l = new ArrayList<Location>();

		for (int xx = x - xr; xx <= x + xr; xx++) {
			for (int yy = y - yr; yy <= y + yr; yy++) {
				for (int zz = z - zr; zz <= z + zr; zz++) {
					
					l.add(new Location(world, xx, yy, zz));				
				}
			}
		}

		return l;
	}
	
	public static List<Location> getFilteredCube(World world, int x, int y, int z, int xr, int yr, int zr, Block... blocks) {

		ArrayList<Location> l = new ArrayList<Location>();

		for (int xx = x - xr; xx <= x + xr; xx++) {
			for (int yy = y - yr; yy <= y + yr; yy++) {
				for (int zz = z - zr; zz <= z + zr; zz++) {
					
					Location temp = new Location(world, xx, yy, zz);
					
					if (Arrays.asList(blocks).contains(temp.getBlock())) { 
						l.add(temp);
					}													
				}
			}
		}

		return l;
	}
}

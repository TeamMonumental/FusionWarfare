package calemi.fusionwarfare.init;

import calemi.fusionwarfare.FusionWarfare;
import calemi.fusionwarfare.entity.EntityBlock;
import calemi.fusionwarfare.entity.EntityDesignatorOrb;
import calemi.fusionwarfare.entity.EntityFallingSupplyCrate;
import calemi.fusionwarfare.entity.EntityFusionBullet;
import calemi.fusionwarfare.entity.EntityMissile;
import calemi.fusionwarfare.entity.EntityRocket;
import cpw.mods.fml.common.registry.EntityRegistry;

public class InitEntities {

	private static int nextEntityId = 5;	
	
	public static void init() {	
				
		EntityRegistry.registerModEntity(EntityFusionBullet.class, "fusionBullet", nextEntityId++, FusionWarfare.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityDesignatorOrb.class, "designator_orb", nextEntityId++, FusionWarfare.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityRocket.class, "rocket", nextEntityId++, FusionWarfare.instance, 64, 1, true);
		EntityRegistry.registerModEntity(EntityMissile.class, "missile", nextEntityId++, FusionWarfare.instance, 128, 1, true);	
		EntityRegistry.registerModEntity(EntityBlock.class, "ghostBlock", nextEntityId++, FusionWarfare.instance, 128, 1, true);
		EntityRegistry.registerModEntity(EntityFallingSupplyCrate.class, "fallingSupplyCrate", nextEntityId++, FusionWarfare.instance, 128, 1, true);	
	}
}

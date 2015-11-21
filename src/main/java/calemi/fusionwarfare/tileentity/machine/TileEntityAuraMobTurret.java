package calemi.fusionwarfare.tileentity.machine;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.entity.DamageSourceTurret;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;

public class TileEntityAuraMobTurret extends TileEntityAuraBase {

	@Override
	public String getName() {
		return "Aura Mob Turret";
	}
	
	@Override
	public int getEnergyCost() {
		return 10;
	}

	@Override
	public int getMaxEnergy() {
		return 5000;
	}

	@Override
	public int getProgressTime() {
		return 25;
	}

	@Override
	public boolean onAction() {
		
		for (Object o : entitiesInAura) {
			
			if (o != null && o instanceof EntityMob) {
				
				EntityMob mob = (EntityMob)o;				
				mob.attackEntityFrom(DamageSource.magic, 12);
				System.out.println(mob.toString());
				return true;
			}
		}
		
		return false;
	}
}

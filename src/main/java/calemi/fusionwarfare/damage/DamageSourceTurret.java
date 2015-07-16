package calemi.fusionwarfare.damage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class DamageSourceTurret extends DamageSource {
	
	protected DamageSourceTurret() {
		super("turret");
	}
}
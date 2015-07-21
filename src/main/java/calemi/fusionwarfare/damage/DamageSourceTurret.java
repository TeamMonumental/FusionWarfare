package calemi.fusionwarfare.damage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class DamageSourceTurret extends DamageSource {
	
	private EntityPlayer player;
	private String team;
	
	public DamageSourceTurret(EntityPlayer player, String team) {
		super("turret");
		this.team = team;
		this.player = player;
	}
	
	public IChatComponent func_151519_b(EntityLivingBase entity) {
	   
		return new ChatComponentText(player.getDisplayName() + " was shot to death by a Turret from " + team);
	}
}
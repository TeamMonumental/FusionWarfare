package calemi.fusionwarfare.entity;

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

public class DamageSourceCustom extends DamageSource {
	
	private String text;

	public DamageSourceCustom(String text) {
		super("turret");
		this.text = text;
	}
	
	public IChatComponent func_151519_b(EntityLivingBase entity) {
	   
		return new ChatComponentText(text);
		
		//return new ChatComponentText(target.getDisplayName() + " was pummeled by Fusion Bullets from " + source.getDisplayName());
	}
}
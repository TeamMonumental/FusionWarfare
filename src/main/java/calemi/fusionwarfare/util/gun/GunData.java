package calemi.fusionwarfare.util.gun;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;

public class GunData {

	private final ItemStack stack;
	public int time, ammo, scope, usingTicks;
	public boolean reloading;
	
	public GunData(ItemStack stack) {
		this.stack = stack;
		init();
	}	
	
	private void init() {
		
		time = getNBT().getInteger("Time");
		ammo = getNBT().getInteger("Ammo");		
		scope = getNBT().getInteger("Scope");
		usingTicks = getNBT().getInteger("UsingTicks");
		
		reloading = getNBT().getBoolean("Reloading");
	}
	
	public void flush() {
		
		int clampedTime = MathHelper.clamp_int(time, 0, 512);
		int clampedAmmo = MathHelper.clamp_int(ammo, 0, 512);
		int clampedScope = MathHelper.clamp_int(scope, 0, 512);

		getNBT().setInteger("Time", clampedTime);
		getNBT().setInteger("Ammo", clampedAmmo);
		getNBT().setInteger("Scope", clampedScope);
		getNBT().setInteger("UsingTicks", usingTicks);
		
		getNBT().setBoolean("Reloading", reloading);
	}
	
	private NBTTagCompound getNBT() {
		
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
		}

		return stack.getTagCompound();
	}
}

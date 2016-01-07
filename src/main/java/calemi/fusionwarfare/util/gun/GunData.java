package calemi.fusionwarfare.util.gun;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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
		
		getNBT().setInteger("Time", time);
		getNBT().setInteger("Ammo", ammo);		
		getNBT().setInteger("Scope", scope);
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

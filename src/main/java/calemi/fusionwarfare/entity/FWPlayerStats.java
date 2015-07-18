package calemi.fusionwarfare.entity;

import java.lang.ref.WeakReference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class FWPlayerStats implements IExtendedEntityProperties {

	public static final String PROP_NAME = "FW";
	
	public WeakReference<EntityPlayer> player;
	
	public boolean firstLogIn;
	public boolean isLoggedIn;
	
	public FWPlayerStats(EntityPlayer player) {
		this.player = new WeakReference<EntityPlayer>(player);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound nbt) {
		NBTTagCompound fwTag = new NBTTagCompound();
		fwTag.setBoolean("firstLogIn", firstLogIn);
		fwTag.setBoolean("isLoggedIn", isLoggedIn);
		nbt.setTag(PROP_NAME, fwTag);
	}

	@Override
	public void loadNBTData(NBTTagCompound nbt) {
		
		NBTTagCompound properties = (NBTTagCompound) nbt.getTag(PROP_NAME);
		
		firstLogIn = properties.getBoolean("firstLogIn");
		isLoggedIn = properties.getBoolean("isLoggedIn");
	}

	@Override
	public void init(Entity entity, World world) {
		this.player = new WeakReference<EntityPlayer>((EntityPlayer) entity);
	}
	
	public static final void register (EntityPlayer player) {
        player.registerExtendedProperties(FWPlayerStats.PROP_NAME, new FWPlayerStats(player));
    }
	
	public static final FWPlayerStats get (EntityPlayer player) {
        return (FWPlayerStats) player.getExtendedProperties(PROP_NAME);
    }
}

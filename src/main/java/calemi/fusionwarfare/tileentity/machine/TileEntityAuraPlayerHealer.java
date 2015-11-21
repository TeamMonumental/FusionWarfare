package calemi.fusionwarfare.tileentity.machine;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;

public class TileEntityAuraPlayerHealer extends TileEntityAuraBase {

	@Override
	public String getName() {
		return "Aura Player Healer";
	}
	
	@Override
	public int getEnergyCost() {
		return 500;
	}

	@Override
	public int getMaxEnergy() {
		return 5000;
	}

	@Override
	public int getProgressTime() {
		return 20;
	}

	@Override
	public boolean onAction() {
		
		for (Object o : entitiesInAura) {
			
			if (o instanceof EntityPlayer) {
				
				EntityPlayer player = (EntityPlayer)o;
										
				if (!player.capabilities.isCreativeMode && isSameTeam(player.getTeam()) && player.getHealth() < player.getMaxHealth()) {
					
					player.heal(2);
					return true;										
				}
			}
		}
		
		return false;
	}	
}

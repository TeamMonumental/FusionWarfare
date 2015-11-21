package calemi.fusionwarfare.tileentity.machine;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import calemi.fusionwarfare.api.EnergyUtil;
import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.api.IEnergy;
import calemi.fusionwarfare.block.BlockContainerBase;
import calemi.fusionwarfare.entity.DamageSourceTurret;
import calemi.fusionwarfare.tileentity.TileEntitySecurity;
import calemi.fusionwarfare.tileentity.base.TileEntityEnergyBase;
import ibxm.Player;

public class TileEntityAuraPlayerTurret extends TileEntityAuraBase {

	@Override
	public String getName() {
		return "Aura Player Turret";
	}
	
	@Override
	public int getEnergyCost() {
		return 1000;
	}

	@Override
	public int getMaxEnergy() {
		return 15000;
	}

	@Override
	public int getProgressTime() {
		return 20;
	}

	@Override
	public boolean onAction() {
		
		for (Entity e : entitiesInAura) {
			
			if (e instanceof EntityPlayer) {
				
				EntityPlayer player = (EntityPlayer)e;
				
				if (!player.capabilities.isCreativeMode && !isSameTeam(player.getTeam()) && player.hurtResistantTime == 0) {
					
					player.attackEntityFrom(new DamageSourceTurret(player, teamName), 12.0F);	
					player.hurtResistantTime = 5;
					
					return true;
				}	
			}		
		}
		
		return false;
	}		
}

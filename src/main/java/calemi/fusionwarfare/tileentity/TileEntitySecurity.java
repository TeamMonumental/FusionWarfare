package calemi.fusionwarfare.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.ObjectiveStat;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntitySecurity extends TileEntityBase {

	public Team team;
	public boolean running;
	
	@Override
	public void updateEntity() {
		System.out.println(team);
	}
	
	public boolean isSameTeam(EntityPlayer player) {
		
		if (team != null) {
			return team.isSameTeam(player.getTeam());
		}
		
		return true;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		if (team != null) {
			nbt.setString("team", team.getRegisteredName());
		}	
		
		else {
			nbt.removeTag("team");
		}
		
		nbt.setBoolean("running", running);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		if (nbt.hasKey("team")) {
			team = worldObj.getScoreboard().getTeam(nbt.getString("team"));
		}
				
		running = nbt.getBoolean("running");
	}	
}

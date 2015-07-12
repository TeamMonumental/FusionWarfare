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

	public String teamName;
	public boolean running;
	
	public boolean isSameTeam(EntityPlayer player) {
		
		if (getTeam() != null) {
			return getTeam().isSameTeam(player.getTeam());
		}
		
		return true;
	}
	
	public Team getTeam() {
		return worldObj.getScoreboard().getTeam(teamName);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		if (teamName != null) {
			nbt.setString("team", teamName);
		}		
					
		nbt.setBoolean("running", running);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		System.out.println(nbt.getString("team"));
		
		if (nbt.hasKey("team")) {
			teamName = nbt.getString("team");
		}		
			
		running = nbt.getBoolean("running");
	}	
}

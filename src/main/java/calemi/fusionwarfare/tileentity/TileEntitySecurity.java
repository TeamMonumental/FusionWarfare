package calemi.fusionwarfare.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.ObjectiveStat;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntitySecurity extends TileEntityBase {

	public Team team;
	public boolean running;
	
	public boolean isSameTeam(EntityPlayer player) {
		if (team != null) {
			return team.isSameTeam(player.getTeam());
		}
		
		return true;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setString("team", team.getRegisteredName());
		nbt.setBoolean("running", running);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		team = worldObj.getScoreboard().getPlayersTeam(nbt.getString("team"));
		running = nbt.getBoolean("running");
	}
}

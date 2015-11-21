package calemi.fusionwarfare.tileentity;

import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.api.ISecurity;
import calemi.fusionwarfare.tileentity.base.TileEntityBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.tileentity.TileEntity;

public class TileEntityReinforcedDoor extends TileEntityBase implements ISecurity {

	private String teamName;

	@Override
	public boolean isSameTeam(Team team) {
		return getTeam() == null || (team != null && getTeam().isSameTeam(team));
	}

	@Override
	public void setTeam(Team team) {
		teamName = team.getRegisteredName();
	}
	
	@Override
	public Team getTeam() {
		return worldObj.getScoreboard().getTeam(teamName);
	}
	
	@Override
	public void writeSyncNBT(NBTTagCompound nbt) {
		super.writeSyncNBT(nbt);
	
		if (teamName != null) {
			nbt.setString("team", teamName);
		}
	}
	
	@Override
	public void readSyncNBT(NBTTagCompound nbt) {
		super.readSyncNBT(nbt);
		
		if (nbt.hasKey("team")) {
			teamName = nbt.getString("team");
		}
	}
}
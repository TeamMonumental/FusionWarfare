package calemi.fusionwarfare.tileentity;

import calemi.fusionwarfare.api.EnumIO;
import calemi.fusionwarfare.api.ISecurity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.Team;
import net.minecraft.tileentity.TileEntity;

public class TileEntityReinforcedDoor extends TileEntity implements ISecurity {

	private String teamName;

	@Override
	public boolean isSameTeam(Team team) {
		return geTeam() != null && geTeam().isSameTeam(team);
	}

	@Override
	public void setTeam(Team team) {
		teamName = team.getRegisteredName();
	}
	
	@Override
	public Team geTeam() {
		return worldObj.getScoreboard().getTeam(teamName);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		if (teamName != null) {
			nbt.setString("team", teamName);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		if (nbt.hasKey("team")) {
			teamName = nbt.getString("team");
		}
	}
}
package calemi.fusionwarfare.api;

import net.minecraft.scoreboard.Team;

public interface ISecurity {

	public boolean isSameTeam(Team team);
	
	public void setTeam(Team team);
	
	public Team geTeam();
}

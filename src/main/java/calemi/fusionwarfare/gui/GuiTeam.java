package calemi.fusionwarfare.gui;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.recipe.TwoInputRecipeRegistry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;

public class GuiTeam extends GuiScreenBase {

	private ArrayList<Team> teams = new ArrayList<Team>();
	
	private int currentTeamIndex;
	
	private GuiTextField teamNameField;
	
	private GuiFusionButton addButton1, addButton2;
	private GuiFusionButton subButton1, subButton2;
	private GuiFusionButton upButton1, upButton2;
	private GuiFusionButton downButton1, downButton2;
	private GuiFusionButton doneButton;
	
	private EntityPlayer player;
	
	public GuiTeam(EntityPlayer player) {
		this.player = player;
	}
	
	@Override
	public String getGuiTextures() {
		return "team";
	}

	@Override
	public int getGuiSizeX() {
		return 256;
	}

	@Override
	public int getGuiSizeY() {
		return 176;
	}
	
	@Override
	public void initGui() {
		super.initGui();
	
		refresh();
				
		int nextButtonID = 1;
		int otherSideOffsetX = 58;
		int offsetX = 91;
		
		Keyboard.enableRepeatEvents(true);
		
		teamNameField = new GuiTextField(this.fontRendererObj, getScreenX() + 11, getScreenY() + 21, 75, 12);
		teamNameField.setTextColor(-1);
		teamNameField.setDisabledTextColour(-1);
		teamNameField.setEnableBackgroundDrawing(false);
		teamNameField.setMaxStringLength(20);
		
		addButton1 = new GuiFusionButton(nextButtonID++, getScreenX() + offsetX, getScreenY() + 16, 16, "+");
		buttonList.add(addButton1);		
		addButton2 = new GuiFusionButton(nextButtonID++, getScreenX() + offsetX + otherSideOffsetX, getScreenY() + 16, 16, "+");
		buttonList.add(addButton2);
		
		subButton1 = new GuiFusionButton(nextButtonID++, getScreenX() + offsetX, getScreenY() + 94, 16, "X");
		buttonList.add(subButton1);		
		subButton2 = new GuiFusionButton(nextButtonID++, getScreenX() + offsetX + otherSideOffsetX, getScreenY() + 94, 16, "X");
		buttonList.add(subButton2);
		
		upButton1 = new GuiFusionButton(nextButtonID++, getScreenX() + offsetX, getScreenY() + 74, 16, "/\\");
		buttonList.add(upButton1);
		upButton2 = new GuiFusionButton(nextButtonID++, getScreenX() + offsetX + otherSideOffsetX, getScreenY() + 74, 16, "/\\");
		buttonList.add(upButton2);		
		
		downButton1 = new GuiFusionButton(nextButtonID++, getScreenX() + offsetX, getScreenY() + 114, 16, "\\/");
		buttonList.add(downButton1);
		downButton2 = new GuiFusionButton(nextButtonID++, getScreenX() + offsetX + otherSideOffsetX, getScreenY() + 114, 16, "\\/");
		buttonList.add(downButton2);
		
		doneButton = new GuiFusionButton(nextButtonID++, getScreenX() + offsetX, getScreenY() + 152, 74, "Done");
		buttonList.add(doneButton);
	}

	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawCenteredStringWithoutShadow("Teams", 47, 6);
		drawCenteredStringWithoutShadow("Players", 207, 6);	
		this.teamNameField.drawTextBox();
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
				
		for (int i = -3; i <= 3; i++) {
			
			Team team = getTeam(i + currentTeamIndex);
			
			if (team != null) {
				
				drawLimitedString(team.getRegisteredName(), 9, 98 + (i * 10), 78, mouseX, mouseY, i == 0);	
			}
		}
		
		for (int i = -3; i <= 3; i++) {
			
			Team team = getTeam(i + currentTeamIndex);
			
			if (team != null) {
				
				drawSelectedStringOverBox(team.getRegisteredName(), 9, 98 + (i * 10), 78, mouseX, mouseY);
			}
		}		
	}	
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		if (upButton1.id == button.id) currentTeamIndex--;
		if (downButton1.id == button.id) currentTeamIndex++;		
		
		if (doneButton.id == button.id) {
			player.closeScreen();
		}	
		
		if (currentTeamIndex < 0) {
			currentTeamIndex = teams.size() - 1;
		}

		currentTeamIndex %= teams.size();
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}
	
	@Override
	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
		teamNameField.textboxKeyTyped(c, i);
    }
	 
	@Override
	protected void mouseClicked(int x, int y, int i) {
	    super.mouseClicked(x, y, i);
	    teamNameField.mouseClicked(x, y, i);
	}
	
	private Team getSelectedTeam() {		
		
		try {
			return teams.get(currentTeamIndex);
		}
		
		catch (IndexOutOfBoundsException e) {
			return null;
		}	
	}
	
	private Team getTeam(int index) {
		
		try {
			return teams.get(index);
		}
	
		catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private void refresh() {
		
		teams.clear();
				
		for (Object team : player.worldObj.getScoreboard().getTeams()) {		
			
			teams.add((Team)team);			
		}
	}
}

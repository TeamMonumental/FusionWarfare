package calemi.fusionwarfare.gui;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.gui.button.GuiFusionButton;
import calemi.fusionwarfare.util.EnumColorUtil;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;

public class GuiTeamSystem extends GuiScreenBase {

	private ArrayList<Team> teams = new ArrayList<Team>();
	private ArrayList<String> playerNames = new ArrayList<String>();
	private ArrayList<EnumColorUtil> colors = new ArrayList<EnumColorUtil>();
	
	private int currentTeamIndex;
	private int currentPlayerIndex;
	private int currentColorIndex;
	
	private GuiTextField teamNameField;
	private GuiTextField playerNameField;
	
	private GuiFusionButton addButton1, addButton2;
	private GuiFusionButton subButton1, subButton2;
	private GuiFusionButton upButton1, upButton2;
	private GuiFusionButton downButton1, downButton2;
	private GuiFusionButton joinButton;
	private GuiFusionButton doneButton;
		
	//Team Options
	private GuiFusionButton friendlyFireButton;
	private GuiFusionButton colorButton;
	private GuiFusionButton addColorButton;
	
	private EntityPlayer player;
	
	public GuiTeamSystem(EntityPlayer player) {
		this.player = player;
		colors.addAll(Arrays.asList(EnumColorUtil.values()));
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
		return 209;
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
		teamNameField.setMaxStringLength(16);
		
		playerNameField = new GuiTextField(this.fontRendererObj, getScreenX() + 171, getScreenY() + 21, 75, 12);
		playerNameField.setTextColor(-1);
		playerNameField.setDisabledTextColour(-1);
		playerNameField.setEnableBackgroundDrawing(false);
		playerNameField.setMaxStringLength(16);
		
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
		
		joinButton = new GuiFusionButton(nextButtonID++, getScreenX() + offsetX + 18, getScreenY() + 94, 38, "Join");
		buttonList.add(joinButton);
		
		doneButton = new GuiFusionButton(nextButtonID++, getScreenX() + offsetX, getScreenY() + 152, 74, "Done");
		buttonList.add(doneButton);
		
		//Team Options
		
		colorButton = new GuiFusionButton(nextButtonID++, getScreenX() + 130, getScreenY() + 188, 48, EnumColorUtil.getPrefixByColor(getSelectedColor()) + "Color");
		buttonList.add(colorButton);		
		
		addColorButton = new GuiFusionButton(nextButtonID++, getScreenX() + 130 + otherSideOffsetX, getScreenY() + 188, 16, "+");
		buttonList.add(addColorButton);
		
		friendlyFireButton = new GuiFusionButton(nextButtonID++, getScreenX() + 51, getScreenY() + 188, 74, "Friendly Fire");
		buttonList.add(friendlyFireButton);
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		refresh();		
		
		colorButton.displayString = EnumColorUtil.getPrefixByColor(getSelectedColor()) + "Color";
		
		if (teamNameField.isFocused()) {
									
			if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
				
				if (!teamNameField.getText().isEmpty()) {			
					
					((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams add " + teamNameField.getText());	
					teamNameField.setText("");
				}		
			}
		}
		
		if (playerNameField.isFocused()) {
			
			if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
				
				if (getSelectedTeam() != null && !playerNameField.getText().isEmpty()) {			
					
					((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams join " + getSelectedTeam().getRegisteredName() + " " + playerNameField.getText());	
					playerNameField.setText("");
				}		
			}
		}
		
		if (getSelectedTeam() != null) {
			friendlyFireButton.clicked = getSelectedTeam().getAllowFriendlyFire();
		}
		
		colorButton.enabled = (getSelectedTeam() != null);
		addColorButton.enabled = (getSelectedTeam() != null);	
		friendlyFireButton.enabled = (getSelectedTeam() != null);
	}
	
	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		drawCenteredStringWithoutShadow("Teams", 47, 6);
		drawCenteredStringWithoutShadow("Players", 207, 6);	
		drawCenteredStringWithoutShadow("Team Options", getGuiSizeX() / 2, 178);
		teamNameField.drawTextBox();
		playerNameField.drawTextBox();
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
				
		int range = 6;
		
		for (int i = -range; i <= range; i++) {
			
			Team team = getTeam(i + currentTeamIndex);
			
			if (team != null) {				
				drawLimitedString((i > 0 && i == 0 ? " " : "") + ((ScorePlayerTeam)teams.get(i + currentTeamIndex)).getColorPrefix() + team.getRegisteredName(), 10, 98 + (i * 10), 78, mouseX, mouseY, i == 0);
			}
		}
				
		for (int i = -range; i <= range; i++) {
			
			String p = getPlayer(i + currentPlayerIndex);
			
			if (p != null) {				
				drawLimitedString((i > 0 && i == 0 ? " " : "") + p, 170, 98 + (i * 10), 78, mouseX, mouseY, i == 0);
			}
		}
		
		for (int i = -range; i <= range; i++) {
			
			Team team = getTeam(i + currentTeamIndex);
		
			if (team != null) {				
				drawSelectedStringOverBox(team.getRegisteredName(), 10, 98 + (i * 10), 78, mouseX, mouseY);
			}
		}	
		
		for (int i = -range; i <= range; i++) {
			
			String p = getPlayer(i + currentPlayerIndex);
						
			if (p != null) {				
				drawSelectedStringOverBox(p, 170, 98 + (i * 10), 78, mouseX, mouseY);
			}
		}
	}	
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		if (upButton1.id == button.id) currentTeamIndex--;
		if (downButton1.id == button.id) currentTeamIndex++;
		
		if (upButton2.id == button.id) currentPlayerIndex--;
		if (downButton2.id == button.id) currentPlayerIndex++;
		
		if (addButton1.id == button.id) {
			
			if (!teamNameField.getText().isEmpty()) {			
				
				((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams add " + teamNameField.getText());	
				teamNameField.setText("");
			}
		}
		
		if (addButton2.id == button.id) {
			
			if (getSelectedTeam() != null && !playerNameField.getText().isEmpty()) {			
				
				((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams join " + getSelectedTeam().getRegisteredName() + " " + playerNameField.getText());	
				playerNameField.setText("");
			}
		}
		
		if (subButton1.id == button.id) {
						
			if (getSelectedTeam() != null) {
				
				((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams remove " + getSelectedTeam().getRegisteredName());
			}
		}
		
		if (subButton2.id == button.id) {
			
			if (getSelectedPlayer() != null) {
				
				((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams leave " + getSelectedPlayer());
			}
		}
		
		if (joinButton.id == button.id) {
			
			if (getSelectedTeam() != null) {			
				
				((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams join " + getSelectedTeam().getRegisteredName() + " " + player.getDisplayName());	
			}
		}
		
		if (doneButton.id == button.id) {
			player.closeScreen();
		}	
				
		//Team Options
		
		if (colorButton.id == button.id) currentColorIndex++;
		
		if (addColorButton.id == button.id && getSelectedTeam() != null) {
			((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams option " + getSelectedTeam().getRegisteredName() + " color " + getSelectedColor().toString().toLowerCase());	
		}
		
		if (friendlyFireButton.id == button.id) {
			
			if (getSelectedTeam() != null) {			
				
				((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams option " + getSelectedTeam().getRegisteredName() + " friendlyFire " + !(getSelectedTeam().getAllowFriendlyFire()));	
			}
		}
		
		//-----------
		
		if (teams.size() != 0) {
			
			currentTeamIndex %= teams.size();
			
			if (currentTeamIndex < 0) {
				currentTeamIndex = teams.size() - 1;
			}
		}
		
		if (playerNames.size() != 0) {
			
			currentPlayerIndex %= playerNames.size();
			
			if (currentPlayerIndex < 0) {
				currentPlayerIndex = playerNames.size() - 1;
			}
		}
		
		if (colors.size() != 0) {
			
			currentColorIndex %= colors.size();
			
			if (currentColorIndex < 0) {
				currentColorIndex = colors.size() - 1;
			}
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
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
		playerNameField.textboxKeyTyped(c, i);
    }

	@Override
	protected void mouseClicked(int x, int y, int i) {
	    super.mouseClicked(x, y, i);
	    teamNameField.mouseClicked(x, y, i);
	    playerNameField.mouseClicked(x, y, i);
	}
	
	private Team getSelectedTeam() {		
		
		try {
			return teams.get(currentTeamIndex);
		}
		
		catch (IndexOutOfBoundsException e) {
			return null;
		}	
	}
		
	private ScorePlayerTeam getSelectedScoreTeam() {
		
		if (teams.get(currentTeamIndex) instanceof ScorePlayerTeam) {
			
			try {			
				return (ScorePlayerTeam)teams.get(currentTeamIndex);						
			}
		
			catch (IndexOutOfBoundsException e) {
				return null;
			}	
		}
			
		return null;	
	}
	
	private EnumColorUtil getSelectedColor() {		
		return colors.get(currentColorIndex);
	}
		
	private Team getTeam(int index) {
		
		try {
			return teams.get(index);
		}
	
		catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private String getSelectedPlayer() {		
		
		try {
			return playerNames.get(currentPlayerIndex);
		}
		
		catch (IndexOutOfBoundsException e) {
			return null;
		}	
	}
	
	private String getPlayer(int index) {
		
		try {
			return playerNames.get(index);
		}
	
		catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private void refresh() {
		
		teams.clear();
		playerNames.clear();
				
		for (Object team : player.worldObj.getScoreboard().getTeams()) {		
			
			teams.add((Team)team);			
		}	
		
		Team team = getSelectedTeam();
		
		if (team != null) {
			
			for (Object p : ((ScorePlayerTeam)team).getMembershipCollection()) {
				
				playerNames.add(p.toString());
			}			
		}
		
		if (teams.size() == 1) {
			currentTeamIndex = 0;
		}
		
		if (playerNames.size() == 1) {
			currentPlayerIndex = 0;
		}	
	}

	@Override
	public boolean canCloseWithInvKey() {
		return !teamNameField.isFocused() && !playerNameField.isFocused();
	}
}

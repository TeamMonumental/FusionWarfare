package calemi.fusionwarfare.gui;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class GuiNumberField extends GuiTextField {

	public GuiNumberField(FontRenderer fontRenderer, int xPos, int yPos, int width, int height) {
		super(fontRenderer, xPos, yPos, width, height);
	}

	@Override
	public void writeText(String str) {
			
		String newString = "";
		
		for (int i = 0; i < str.length(); i++) {
			
			if (Character.isDigit(str.charAt(i))) {		
				newString += str.charAt(i);
			}
		}
			
		super.writeText(newString);
	}
	
	@Override
	public boolean textboxKeyTyped(char c, int i) {		
		
		if (Character.isDigit(c) ||
			Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) ||
			Keyboard.isKeyDown(Keyboard.KEY_RCONTROL) ||
			Keyboard.isKeyDown(Keyboard.KEY_BACK) ||
			Keyboard.isKeyDown(Keyboard.KEY_DELETE) ||
			Keyboard.isKeyDown(Keyboard.KEY_LEFT) ||
			Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			return super.textboxKeyTyped(c, i); 
		
		return false;		
	}	
}

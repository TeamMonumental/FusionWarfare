package calemi.fusionwarfare.key;

import org.lwjgl.input.Keyboard;

import calemi.fusionwarfare.Reference;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindings {

	public static KeyBinding teamGuiButton = new KeyBinding("Team Gui", Keyboard.KEY_G, Reference.MOD_NAME);
	public static KeyBinding reloadButton = new KeyBinding("Reload Gun", Keyboard.KEY_R, Reference.MOD_NAME);
}

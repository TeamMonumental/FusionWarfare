package calemi.fusionwarfare.util;

import org.apache.commons.codec.binary.Hex;

public enum EnumColorUtil {

	BLACK("0", 0, 0, 0, 0x000000),
	DARK_BLUE("1", 0, 0, 170, 0x0000AA),
	DARK_GREEN("2", 0, 170, 0, 0x00AA00),
	DARK_AQUA("3", 0, 170, 170, 0x00AAAA),
	DARK_RED("4", 170, 0, 0, 0xAA0000),
	DARK_PURPLE("5", 170, 0, 170, 0xAA00AA),
	GOLD("6", 170, 170, 0, 0xFFAA00),
	GRAY("7", 170, 170, 170, 0xAAAAAA),
	DARK_GRAY("8", 85, 85, 85, 0x555555),
	BLUE("9", 85, 85, 255, 0x5555FF),
	GREEN("a", 85, 255, 85, 0x55FF55),
	AQUA("b", 85, 255, 255, 0x55FFFF),
	RED("c", 255, 85, 85, 0xFF5555),
	LIGHT_PURPLE("d", 255, 85, 255, 0xFF55FF),
	YELLOW("e", 255, 255, 85, 0xFFFF55),
	WHITE("f", 255, 255, 255, 0xFFFFFF);
	
	public String colorID;
	public int red, green, blue;
	public int hex;
	
	private EnumColorUtil(String colorID, int red, int green, int blue, int hex) {
		this.colorID = colorID;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.hex = hex;
	}
	
	public static EnumColorUtil getColorByPrefix(String colorPrefix) {
			
		if (!colorPrefix.isEmpty()) {
			
			String colorID = colorPrefix.substring(1);
			
			for (EnumColorUtil color : EnumColorUtil.values()) {
			
				if (colorID.equals(color.colorID)) {			
					return color;
				}
			}
		}	
		
		return WHITE;
	}
	
	public static String getPrefixByColor(EnumColorUtil enumColor) {
		
		return "§" + enumColor.colorID;		
	}
}


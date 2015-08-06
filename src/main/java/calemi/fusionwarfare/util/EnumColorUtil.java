package calemi.fusionwarfare.util;

import org.apache.commons.codec.binary.Hex;

public enum EnumColorUtil {

	BLACK("0", 0, 0, 0, 0x000000),
	DARK_BLUE("1", 0, 0, 170, 0x00002A),
	DARK_GREEN("2", 0, 170, 0, 0x002A00),
	DARK_AQUA("3", 0, 170, 170, 0x002A2A),
	DARK_RED("4", 170, 0, 0, 0x2A0000),
	DARK_PURPLE("5", 170, 0, 170, 0x2A002A),
	GOLD("6", 170, 170, 0, 0x2A2A00),
	GRAY("7", 170, 170, 170, 0x2A2A2A),
	DARK_GRAY("8", 85, 85, 85, 0x151515),
	BLUE("9", 85, 85, 255, 0x15153F),
	GREEN("a", 85, 255, 85, 0x153F15),
	AQUA("b", 85, 255, 255, 0x153F3F),
	RED("c", 255, 85, 85, 0x3F1515),
	LIGHT_PURPLE("d", 255, 85, 255, 0x3F153F),
	YELLOW("e", 255, 255, 85, 0x3F3F15),
	WHITE("f", 255, 255, 255, 0x3F3F3F);
	
	private String colorID;
	private int red, green, blue;
	private int hex;
	
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


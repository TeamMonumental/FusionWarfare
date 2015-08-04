package calemi.fusionwarfare.util;

public enum EnumColorUtil {

	BLACK("0", 0, 0, 0),
	DARK_BLUE("1", 0, 0, 170),
	DARK_GREEN("2", 0, 170, 0),
	DARK_AQUA("3", 0, 170, 170),
	DARK_RED("4", 170, 0, 0),
	DARK_PURPLE("5", 170, 0, 170),
	GOLD("6", 170, 170, 0),
	GRAY("7", 170, 170, 170),
	DARK_GRAY("8", 85, 85, 85),
	BLUE("9", 85, 85, 255),
	GREEN("a", 85, 255, 85),
	AQUA("b", 85, 255, 255),
	RED("c", 255, 85, 85),
	LIGHT_PURPLE("d", 255, 85, 255),
	YELLOW("e", 255, 255, 85),
	WHITE("f", 255, 255, 255);
	
	private String colorID;
	private int red, green, blue;
	
	private EnumColorUtil(String colorID, int red, int green, int blue) {
		this.colorID = colorID;
		this.red = red;
		this.green = green;
		this.blue = blue;
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
}


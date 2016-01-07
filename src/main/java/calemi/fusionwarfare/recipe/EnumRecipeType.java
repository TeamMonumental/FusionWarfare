package calemi.fusionwarfare.recipe;

public enum EnumRecipeType {

	INFUSION_TABLE("Infusion Table", 0),
	INFUSION_FOUNDRY("Infusion Foundry", 1),
	MISSILE_FACTORY("Missile Factory", 2);
	
	public String name;
	public int index;
		
	private EnumRecipeType(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	public static EnumRecipeType getRecipeType(int index) {
		
		for (EnumRecipeType recipe : values()) {
			
			if (index == recipe.index) {			
				return recipe;
			}
		}
		
		return INFUSION_TABLE;
	}
}

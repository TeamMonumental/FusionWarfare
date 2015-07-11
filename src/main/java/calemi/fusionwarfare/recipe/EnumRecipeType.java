package calemi.fusionwarfare.recipe;

public enum EnumRecipeType {

	INFUSION_TABLE("Infusion Table"),
	INFUSION_FOUNDRY("Infusion Foundry"),
	MISSILE_FACTORY("Missile Factory"),
	GUN_TABLE("Gun Table");
	
	public String name;
	
	private EnumRecipeType(String name) {
		this.name = name;
	}
}

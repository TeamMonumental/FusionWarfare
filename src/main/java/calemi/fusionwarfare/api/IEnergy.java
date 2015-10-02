package calemi.fusionwarfare.api;

public interface IEnergy {

	public int getEnergy();
	public void setEnergy(int energy);

	public int getMaxEnergy();
	public void setMaxEnergy(int energy);

	public EnumIO getIOType();
}
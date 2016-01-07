package calemi.fusionwarfare.api;

public interface IEnergy extends INetwork {

	public int getEnergy();
	public void setEnergy(int energy);

	public int getMaxEnergy();
	public void setMaxEnergy(int energy);
}
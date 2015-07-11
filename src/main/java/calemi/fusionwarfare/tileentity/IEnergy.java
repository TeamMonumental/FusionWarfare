package calemi.fusionwarfare.tileentity;

public interface IEnergy {

	public int getEnergy();
	public void setEnergy(int energy);
	public int getMaxEnergy();
	public EnumIO getIOType();
}

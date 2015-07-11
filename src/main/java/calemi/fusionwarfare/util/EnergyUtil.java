package calemi.fusionwarfare.util;

import calemi.fusionwarfare.tileentity.IEnergy;

public class EnergyUtil {

	public static void transferEnergy(IEnergy from, IEnergy to, int amount) {	
		
		if (isFull(to) || from.getEnergy() <= 0) {
			return;
		}
		
		if (canSubtractEnergy(from, amount) && canAddEnergy(to, amount)) { 			
			subtractEnergy(from, amount);
			addEnergy(to, amount);
			return;
		}
		
		int sourceEnergy = from.getEnergy();
		int targetSpace = getSpace(to);
		
		int min = Math.min(sourceEnergy, targetSpace);
		
		if (min <= amount) {		
			transferEnergy(from, to, min);
		}
	}
	
	public static void addEnergy(IEnergy e, int amount) {		
		if(canAddEnergy(e, amount)) e.setEnergy(e.getEnergy() + amount); 
	}

	public static void subtractEnergy(IEnergy e, int amount) {		
		if(canSubtractEnergy(e, amount)) e.setEnergy(e.getEnergy() - amount); 	
	}
	
	public static boolean canAddEnergy(IEnergy e, int amount) {		
		return amount <= getSpace(e);		
	}
	
	public static boolean canSubtractEnergy(IEnergy e, int amount) {		
		return e.getEnergy() >= amount;		
	}
	
	public static boolean hasEnergy(IEnergy e, int amount) {
		return e.getEnergy() >= amount;
	}
	
	public static boolean isFull(IEnergy e) {
		return e.getEnergy() >= e.getMaxEnergy();
	}
	
	public static int getSpace(IEnergy e) {
		return e.getMaxEnergy() - e.getEnergy();
	}
}

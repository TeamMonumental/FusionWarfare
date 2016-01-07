package calemi.fusionwarfare.util.gun;

public class GunProfile {

	public int damage, shotsPerFire, maxAmmo, reloadTime, accuracy;
	public float gravityVelocity, recoil; 
	public boolean isAuto;
	
	public GunProfile(int damage, int shotsPerFire, int maxAmmo, int reloadTime, int accuracy, float gravityVelocity, float recoil, boolean isAuto) {
		this.damage = damage;
		this.shotsPerFire = shotsPerFire;
		this.maxAmmo = maxAmmo;
		this.reloadTime = reloadTime;
		this.accuracy = accuracy;
		this.gravityVelocity = gravityVelocity;
		this.recoil = recoil;
		this.isAuto = isAuto;
	}
}

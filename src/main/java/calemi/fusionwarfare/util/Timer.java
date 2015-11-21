package calemi.fusionwarfare.util;

public class Timer {

	public long target, lastTime;
	
	public Timer(long target) {
		this.target = target;
		reset();
	}
	
	public boolean isDoneAndReset() {
		if (isDone()) {
			reset();
			return true;
		}
		
		return false;
	}
	
	public boolean isDone() {
		return (System.currentTimeMillis() - lastTime) >= target;
	}
	
	public void reset() {
		lastTime = System.currentTimeMillis();
	}
}
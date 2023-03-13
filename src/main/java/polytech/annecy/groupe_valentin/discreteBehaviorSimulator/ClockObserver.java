package polytech.annecy.groupe_valentin.discreteBehaviorSimulator;

public interface ClockObserver {
	public void clockChange(int time);
	public void nextClockChange(int nextJump);
}

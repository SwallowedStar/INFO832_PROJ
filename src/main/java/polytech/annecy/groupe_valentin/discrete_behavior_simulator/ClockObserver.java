package polytech.annecy.groupe_valentin.discrete_behavior_simulator;

public interface ClockObserver {
	public void clockChange(int time);
	public void nextClockChange(int nextJump);
}

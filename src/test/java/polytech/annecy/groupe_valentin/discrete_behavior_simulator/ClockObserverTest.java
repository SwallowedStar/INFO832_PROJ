package polytech.annecy.groupe_valentin.discrete_behavior_simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClockObserverTest implements ClockObserver {
    private int observedTime;
    private int observedNextJump;

    @Test
    public void testClockObserver() {
        Clock clock = Clock.getInstance();
        clock.addObserver(this);

        // Set next jump to 10
        clock.setNextJump(10);

        // Increase time by 10
        try {
            clock.increase(10);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check if the observer methods were called with the correct values
        assert(observedTime == 10);
        assert(observedNextJump == 10);
    }

    @Override
    public void clockChange(int time) {
        observedTime = time;
    }

    @Override
    public void nextClockChange(int nextJump) {
        observedNextJump = nextJump;
    }
}
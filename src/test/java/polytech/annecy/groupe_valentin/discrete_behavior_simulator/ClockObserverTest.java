package polytech.annecy.groupe_valentin.discrete_behavior_simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ClockObserverTest implements ClockObserver {
    private int observedTime;
    private int observedNextJump;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field instance = Clock.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void testClockObserver() {
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
        assertEquals(10, observedTime);
        assertEquals(10, observedNextJump);
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

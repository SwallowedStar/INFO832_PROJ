package polytech.annecy.groupe_valentin.discrete_behavior_simulator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ClockTest {
    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field instance = Clock.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    void getInstance() {
        Clock clock1 = Clock.getInstance();
        Clock clock2 = Clock.getInstance();

        assertSame(clock1, clock2);
    }

    @Test
    void setVirtual() {
        Clock clk=Clock.getInstance();
        boolean bl1=clk.isVirtual();
        assertTrue(bl1);
        clk.setVirtual(false);
        boolean bl2=clk.isVirtual();
        assertFalse(bl2);
    }

    @Test
    void isVirtual() {
        Clock ck = Clock.getInstance();
        assertTrue(ck.isVirtual());
    }

    private class TestClassClockObserver implements ClockObserver{
        public Integer time = 0;
        public Integer jump = 0;
        @Override
        public void clockChange(int time) {this.time = time;}

        @Override
        public void nextClockChange(int nextJump) {this.jump = nextJump;}
    }

    @Test
    void increase(){
        Clock clock = Clock.getInstance();
        TestClassClockObserver testClock = new TestClassClockObserver();
        clock.addObserver(testClock);
        clock.setVirtual(true);
        clock.setNextJump(1);
        assertEquals(1, testClock.jump);

        clock.increase(1);

        assertEquals(1, clock.getTime());
        assertEquals(1, testClock.time);

        assertThrowsExactly(IllegalArgumentException.class, () -> clock.increase(2)) ;

    }

    @Test
    void getTime() {
        Clock clk=Clock.getInstance();
        long dt1=clk.getTime();


        assertEquals(0,dt1);

        clk.setVirtual(false);

        long dt2 = clk.getTime();
        long tActual = new Date().getTime();

        assertEquals(tActual, dt2);
    }
}
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

    @Test
    void setNextJump() {

    }

    @Test
    void increase() throws Exception{
        Clock clock = Clock.getInstance();
        clock.setVirtual(true);
        clock.setNextJump(1);


        //
        clock.increase(1);

        assertEquals(1, clock.getTime());


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
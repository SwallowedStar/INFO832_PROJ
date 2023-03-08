package polytech.annecy.groupeValentin.discreteBehaviorSimulator;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ClockTest {


    @Test
    void getInstance() {
        Clock clock1 = Clock.getInstance();
        Clock clock2 = Clock.getInstance();

        assertSame(clock1, clock2);

    }

    @Test
    void addObserver() {

    }

    @Test
    void removeObserver() {
    }

    @Test
    void setVirtual() {
        Clock clk=Clock.getInstance();
        boolean bl1=clk.isVirtual();
        assertEquals(true,bl1);
        clk.setVirtual(false);
        boolean bl2=clk.isVirtual();
        assertEquals(false,bl2);
    }

    @Test
    void isVirtual() {
    }

    @Test
    void setNextJump() {
    }

    @Test
    void increase() {
    }

    @Test
    void getTime() {
        Clock clk=Clock.getInstance();
        long dt1=clk.getTime();


        assertEquals(0,dt1);

        clk.setVirtual(false);

        long dt2= clk.getTime();
        long t_actual=new Date().getTime();

        assertEquals(t_actual,dt2);
    }

    @Test
    void lockReadAccess() {
    }

    @Test
    void unlockReadAccess() {
    }

    @Test
    void lockWriteAccess() {
    }

    @Test
    void unlockWriteAccess() {
    }

    @Test
    void testToString() {
    }
}
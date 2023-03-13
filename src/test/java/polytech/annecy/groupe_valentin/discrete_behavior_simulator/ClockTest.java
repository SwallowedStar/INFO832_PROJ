package polytech.annecy.groupe_valentin.discrete_behavior_simulator;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
        Clock ck=Clock.getInstance();
        boolean bool=true;
        assertEquals(bool,ck.isVirtual());
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
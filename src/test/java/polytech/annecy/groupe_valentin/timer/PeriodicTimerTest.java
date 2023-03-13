package polytech.annecy.groupe_valentin.timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import polytech.annecy.groupe_valentin.timer.PeriodicTimer;
import polytech.annecy.groupe_valentin.timer.Timer;

import java.util.NoSuchElementException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class PeriodicTimerTest {
    private PeriodicTimer t1;
    private PeriodicTimer t2;
    private PeriodicTimer t3;
    private PeriodicTimer t4;

    @BeforeEach
    protected void setUp() throws Exception {
        this.t1 = new PeriodicTimer(2);
        this.t2 = new PeriodicTimer(2, 9);
        this.t3 = new PeriodicTimer(0);
        this.t4 = new PeriodicTimer(-5);
    }

    @Test
    public void testHasNext(){
        assertTrue(this.t1.hasNext());
        assertTrue(this.t2.hasNext());
        assertTrue(this.t3.hasNext());
        assertTrue(this.t4.hasNext());
    }

    @Test
    public void testNext() {
        assertEquals(2, this.t1.next());
        assertEquals(9, this.t2.next());
        assertEquals(2, this.t2.next());
        assertEquals(0, this.t3.next());
        assertEquals(-5, this.t3.next());
    }
}

package polytech.annecy.groupeValentin.timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import polytech.annecy.groupeValentin.timer.OneShotTimer;
import polytech.annecy.groupeValentin.timer.PeriodicTimer;
import polytech.annecy.groupeValentin.timer.Timer;

import static org.junit.jupiter.api.Assertions.*;

public class MergedTimerTest {
    private Timer t1;
    private Timer t2;
    
    @BeforeEach
    protected void setUp() throws Exception {
        this.t1 = new MergedTimer(new PeriodicTimer(3), new PeriodicTimer(2));
        this.t2 = new MergedTimer(new PeriodicTimer(4), new OneShotTimer(1));
    }

    @Test
    public void testHasNext(){
        assertTrue(this.t1.hasNext());
        this.t1.next();
        assertTrue(this.t1.hasNext());
        this.t1.next();
        assertTrue(this.t1.hasNext());

        assertTrue(this.t2.hasNext());
        this.t2.next();
        assertFalse(this.t2.hasNext());
    }

    @Test
    public void testNext() {
        assertEquals(5, this.t1.next());
        assertEquals(5, this.t1.next());
        assertEquals(5, this.t1.next());

        assertEquals(5, this.t2.next());
        assertNull(this.t2.next());
    }
}

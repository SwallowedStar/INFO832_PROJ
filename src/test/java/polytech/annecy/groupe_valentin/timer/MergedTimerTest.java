package polytech.annecy.groupe_valentin.timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MergedTimerTest {
    private Timer t1;
    private Timer t2;
    
    @BeforeEach
    protected void setUp() throws Exception {
        this.t1 = new MergedTimer(new PeriodicTimer(3), new PeriodicTimer(2));
        this.t2 = new MergedTimer(new PeriodicTimer(4), new OneShotTimer(1));
    }

    @Test
    void testHasNext(){
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
    void testNext() {
        assertEquals(5, this.t1.next());
        assertEquals(5, this.t1.next());
        assertEquals(5, this.t1.next());

        assertEquals(5, this.t2.next());
        assertNull(this.t2.next());
    }
}

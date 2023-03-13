package polytech.annecy.groupe_valentin.timer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeBoundedTimerTest {
    private int startTime = 0;
    private int stopTime = 4;
    private PeriodicTimer timer1;
    private PeriodicTimer timer2;
    private TimeBoundedTimer timerBound1;
    private TimeBoundedTimer timerBound2;

    @BeforeEach
    protected void setUp() throws Exception {
        this.timer1 = new PeriodicTimer(1);
        this.timer2 = new PeriodicTimer(3);
        this.timerBound1 = new TimeBoundedTimer(timer1, startTime);
        this.timerBound2 = new TimeBoundedTimer(timer2, startTime, stopTime);
    }

    @Test
    public void testHasNext(){
        assertTrue(this.timerBound1.hasNext());
        this.timerBound1.next();
        assertTrue(this.timerBound1.hasNext());

        assertTrue(this.timerBound2.hasNext());
        this.timerBound2.next();
        assertTrue(this.timerBound2.hasNext());
        this.timerBound2.next();
        assertTrue(this.timerBound2.hasNext());
    }

    @Test
    public void testNext(){
        assertNotNull(this.timerBound2.next()); // 3
        assertNotNull(this.timerBound2.next()); // null car 6 < 4 ne marche pas
    }
}

package polytech.annecy.groupe_valentin.timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PeriodicTimerTest {
    private PeriodicTimer t1;
    private PeriodicTimer t2;
    private PeriodicTimer t3;

    @BeforeEach
    protected void setUp() throws Exception {
        this.t1 = new PeriodicTimer(2);
        this.t2 = new PeriodicTimer(2, 9);
        this.t3 = new PeriodicTimer(0);
    }

    @Test
    void testHasNext(){
        assertTrue(this.t1.hasNext());
        assertTrue(this.t2.hasNext());
        assertTrue(this.t3.hasNext());
    }

    @Test
    void testNext() {
        assertEquals(2, this.t1.next());
        assertEquals(9, this.t2.next());
        assertEquals(2, this.t2.next());
        assertEquals(0, this.t3.next());
    }
    
    @Test
    void testExceptionThrown_NegativeArgument () {
    	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    		new PeriodicTimer(-5);
        });

        String expectedMessage = "argument is negative";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}

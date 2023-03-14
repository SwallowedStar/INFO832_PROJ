package polytech.annecy.groupe_valentin.timer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DateTimerTest {
    private Timer t1;
    private Timer t2;
    
    @BeforeEach
    protected void setUp() throws Exception {
        List<Integer> v1 = new ArrayList<>();
        v1.add(5);
        List<Integer> v2 = new ArrayList<>();
        v2.add(4);
        v2.add(5);
        v2.add(1);
        v2.add(3);
        v2.add(-9);

        this.t1 = new DateTimer(v1);
        this.t2 = new DateTimer(v2);
    }

    @Test
    void testHasNext(){
        assertTrue(this.t1.hasNext());
        assertTrue(this.t2.hasNext());

        this.t1.next();
        this.t2.next();

        assertFalse(this.t1.hasNext());
        assertTrue(this.t2.hasNext());
    }

    @Test
    void testNext() {
        assertEquals(5, this.t1.next());

        assertEquals(4, this.t2.next());
        assertEquals(5, this.t2.next());
        assertEquals(1, this.t2.next());
        assertEquals(3, this.t2.next());
        assertEquals(-9, this.t2.next());
    }
}

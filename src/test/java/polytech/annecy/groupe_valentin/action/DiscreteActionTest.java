package polytech.annecy.groupe_valentin.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import polytech.annecy.groupe_valentin.timer.DateTimer;
import polytech.annecy.groupe_valentin.timer.Timer;

import java.util.NoSuchElementException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class DiscreteActionTest {
    private DiscreteAction da1;
    private DiscreteAction da2;
    @BeforeEach
    protected void setUp() throws Exception {
        String testString = "Damien";

        Vector<Integer> v1 = new Vector<>();
        v1.add(5);
        Vector<Integer> v2 = new Vector<>();
        v2.add(4);

        Timer t1 = new DateTimer(v1);
        Timer t2 = new DateTimer(v2);

        this.da1 = new DiscreteAction(testString, "toUpperCase", t1);
        this.da2 = new DiscreteAction(testString, "toLowerCase", t2);
    }

    @Test
    public void testCompareTo(){
        assertTrue(this.da1.compareTo(this.da2) > 0);
        assertTrue(this.da2.compareTo(this.da1) < 0);
        assertEquals(0, this.da1.compareTo(this.da1));
    }

    @Test
    public void testSpendTime() {
        this.da1.spendTime(1);
        this.da1.next();
        this.da2.next();

        this.da1.spendTime(1);
        this.da2.spendTime(1);

        assertEquals(4, this.da1.getCurrentLapsTime());
        assertEquals(3, this.da2.getCurrentLapsTime());
    }

    @Test
    public void testGetMethod() {
        assertEquals( "toUpperCase" ,this.da1.getMethod().getName());
        assertEquals( "toLowerCase" ,this.da2.getMethod().getName());
    }
    @Test
    public void testNext() {
        DiscreteAction da3 = (DiscreteAction) da1.next();
        assertEquals(0, da3.compareTo(da1));
        assertFalse(da3.hasNext());
        assertThrowsExactly(NoSuchElementException.class, () -> {da3.next();});
        assertEquals(-1, da3.compareTo(da2));
    }
}
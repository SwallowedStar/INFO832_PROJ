package polytech.annecy.groupeValentin.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import polytech.annecy.groupeValentin.timer.DateTimer;
import polytech.annecy.groupeValentin.timer.Timer;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void compareTo(){
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

    public void testGetMethod() {
    }

    public void testGetCurrentLapsTime() {
    }

    public void testGetObject() {
    }

    public void testCompareTo() {
    }

    public void testTestToString() {
    }

    public void testNext() {
    }

    public void testHasNext() {
    }
}
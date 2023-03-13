package polytech.annecy.groupeValentin.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import polytech.annecy.groupeValentin.timer.DateTimer;
import polytech.annecy.groupeValentin.timer.Timer;

import java.util.NoSuchElementException;
import java.util.Vector;
import static org.junit.jupiter.api.Assertions.*;


class DiscreteActionDependentTest {

    private DiscreteActionDependent dad1;
    private DiscreteActionDependent dad2;

    @BeforeEach
    void setUp() {
        String testString = "damien";
        Vector<Integer> v1 = new Vector<>();
        v1.add(5);
        Vector<Integer> v2 = new Vector<>();
        v2.add(4);

        Timer t1 = new DateTimer(v1);
        Timer t2 = new DateTimer(v2);

        this.dad1 = new DiscreteActionDependent(testString, "toUpperCase", t1);
        this.dad2 = new DiscreteActionDependent(testString, "toLowerCase", t2);

        Vector<Integer> v3 = new Vector<>();
        v3.add(3);
        Vector<Integer> v4 = new Vector<>();
        v4.add(2);

        Timer t3 = new DateTimer(v3);
        Timer t4 = new DateTimer(v4);

        this.dad1.addDependence("This is the way", "strip", t3);
        this.dad2.addDependence("This is the way", "getBytes", t4);

    }

    @Test
    void spendTime() {
        this.dad1.next();
        this.dad1.spendTime(1);
        assertEquals(4, this.dad1.getCurrentLapsTime());
    }

    @Test
    void isEmpty() {
        assertFalse(this.dad1.isEmpty());
        this.dad1.next();
        this.dad1.next();
        assertTrue(this.dad1.isEmpty());
    }

    @Test
    void next() {
        assertNull(this.dad1.getCurrentLapsTime());
        this.dad1.next();
        assertNotNull(this.dad1.getCurrentLapsTime());
        DiscreteActionDependent d = (DiscreteActionDependent)this.dad1.next();
        assertEquals( "strip", d.currentAction.getMethod().getName());
    }

    @Test
    void hasNext() {
        // Il y a un rapport de bug à faire
        assertTrue(this.dad1.hasNext());
        this.dad1.next();
        assertTrue(this.dad1.hasNext());
        this.dad1.next();
        assertFalse(this.dad1.hasNext());

        Vector<Integer> v2 = new Vector<>();
        v2.add(4);

        Timer t2 = new DateTimer(v2);
        DiscreteActionDependent dad2 = new DiscreteActionDependent("sergkshdf", "toUpperCase", t2);
        dad2.next();
        assertFalse(this.dad1.hasNext());
    }

    @Test
    void addDependence(){
        assertEquals(1, this.dad1.depedentActions.size());

        Vector<Integer> v3 = new Vector<>();
        v3.add(3);

        Timer t3 = new DateTimer(v3);
        DiscreteAction d = new DiscreteAction("This is the way", "isEmpty", t3);
        this.dad1.addDependence("This is the way", "isEmpty", t3);
        assertEquals(2, this.dad1.depedentActions.size());
        assertEquals(d.getMethod().getName(), this.dad1.depedentActions.last().getMethod().getName());
    }

}
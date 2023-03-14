package polytech.annecy.groupe_valentin.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import polytech.annecy.groupe_valentin.timer.DateTimer;
import polytech.annecy.groupe_valentin.timer.Timer;

import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class DiscreteActionOnOffDependentTest {
	
	private DiscreteActionOnOffDependent da1;
	private DiscreteActionOnOffDependent da2;
	private DiscreteActionOnOffDependent da3;
	private DiscreteActionOnOffDependent da4;
	private DiscreteActionOnOffDependent da5;
	
	
	@BeforeEach
    protected void setUp() throws Exception {
		String object = "Valentin";
		
		String on = "toUpperCase";
		String off = "toLowerCase";
		
		Vector<Integer> v1 = new Vector<>();
        v1.add(5);
        Vector<Integer> v2 = new Vector<>();
        v2.add(4);
        Vector<Integer> v3 = new Vector<>();

        Timer t1 = new DateTimer(v1);
        Timer t2 = new DateTimer(v2);
        Timer t3 = new DateTimer(v3);
        
        this.da1 = new DiscreteActionOnOffDependent (object, on, t1, off, t2);
        this.da2 = new DiscreteActionOnOffDependent (object, on, t1, off, t3);
        this.da3 = new DiscreteActionOnOffDependent (object, on, t3, off, t2);
        this.da4 = new DiscreteActionOnOffDependent (object, on, t3, off, t3);
        
        TreeSet<Integer> ts1 = new TreeSet<>();
        ts1.add(5);
        TreeSet<Integer> ts2 = new TreeSet<>();
        ts2.add(1);
        ts2.add(8);
        TreeSet<Integer> ts3 = new TreeSet<>();
        
        
        this.da5 = new DiscreteActionOnOffDependent (object, on, ts1, off, ts2);
    }

	
	@Test
    void testHasNext() {
		// Test avec le premier constructeur
		assertTrue(this.da1.hasNext());
		assertTrue(this.da2.hasNext());
		assertTrue(this.da3.hasNext());
		assertFalse(this.da4.hasNext());
		
		// Test avec le second constructeur
		assertTrue(this.da5.hasNext());      
    }
	
	
	@Test
    void testGetMethod() {
		// Test avec le premier constructeur
		assertEquals("toLowerCase", this.da1.getMethod().getName());
		this.da1.next();
        assertEquals("toUpperCase", this.da1.getMethod().getName());
		
		// Test avec le second constructeur
        assertEquals("toLowerCase", this.da5.getMethod().getName());
        this.da5.next();
        assertEquals("toUpperCase", this.da5.getMethod().getName());
	}
	
	@Test
    void testSpendTime() {
		assertNull(this.da1.getCurrentLapsTime());
		this.da1.spendTime(1);
        this.da1.next();
        assertEquals(5, this.da1.getCurrentLapsTime());
        this.da1.next();
        this.da1.spendTime(1);
        assertEquals(3, this.da1.getCurrentLapsTime());
    }

	
	
}

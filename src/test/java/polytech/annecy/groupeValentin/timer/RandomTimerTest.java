package polytech.annecy.groupeValentin.timer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import polytech.annecy.groupeValentin.timer.RandomTimer;
import polytech.annecy.groupeValentin.timer.RandomTimer.randomDistribution;

import static org.junit.jupiter.api.Assertions.*;

public class RandomTimerTest {

    private randomDistribution distribution1;
    private randomDistribution distribution2;
    double param = 1.0;
    int lolim = 2;
    int hilim = 1;
    RandomTimer randomTimer1;
    RandomTimer randomTimer2;
    RandomTimer randomTimer3;

    @BeforeEach
    protected void setUp() throws Exception {
        this.distribution1 = randomDistribution.EXP;
        this.distribution2 = randomDistribution.GAUSSIAN;

        this.randomTimer1 = new RandomTimer(distribution1, param); // bon
        assertThrowsExactly(Exception.class, () -> {this.randomTimer2 = new RandomTimer(distribution1, lolim, hilim); }); // mauvais
        this.randomTimer3 = new RandomTimer(distribution2, lolim, hilim); // bon

        System.out.println(this.randomTimer3.toString());


    }

    @Test
    public void testNext(){
        assertNotSame(-1, this.randomTimer1.next());
    }
}

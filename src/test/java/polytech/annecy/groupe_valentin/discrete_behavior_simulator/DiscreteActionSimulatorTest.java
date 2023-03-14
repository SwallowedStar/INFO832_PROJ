package polytech.annecy.groupe_valentin.discrete_behavior_simulator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscreteActionSimulatorTest {
    @Test
    void setNbLoop() {
    }

    @Test
    void addAction() {
    }

    @Test
    void run() {
    }

    @Test
    void start() {
        DiscreteActionSimulator ds= new DiscreteActionSimulator();
        boolean bl=true;
        ds.start();
        assertEquals(bl,ds.getRunning());

    }

    @Test
    void stop() {

        DiscreteActionSimulator ds= new DiscreteActionSimulator();
        boolean bl=false;
        ds.stop();
        assertEquals(bl,ds.getRunning());
    }

    @Test
    void testToString() {
    }

    @Test
    void getRunning() {
        DiscreteActionSimulator ds= new DiscreteActionSimulator();
        boolean bl=false;

        assertEquals(bl,ds.getRunning());
    }
}

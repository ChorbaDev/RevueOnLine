package test.metier;

import metier.Periodicite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PeriodiciteTest {
    private Periodicite p1;
    private Periodicite p2;

    @Before
    public void setUp() throws Exception {
        p1=new Periodicite(1,"Mensuel");
        p2=new Periodicite(1,"Mensuel");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testHashCode() {
        assertEquals(p1.hashCode(),p2.hashCode());
    }

    @Test
    public void testEquals() {

        assertTrue(p1.equals(p2));
    }
}
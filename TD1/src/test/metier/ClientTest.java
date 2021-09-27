package test.metier;

import metier.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    private Client c1;
    private Client c2;

    @Before
    public void setUp() throws Exception {
        c1=new Client(1, "TEST", "Test", "99", "rue de test", "99999", "TestVille", "TestPays");
        c2=new Client(1, "TEST", "Test", "99", "rue de test", "99999", "TestVille", "TestPays");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testHashCode() {
        assertEquals(c1.hashCode(),c2.hashCode());
    }

    @Test
    public void testEquals() {
        assertTrue(c1.equals(c2));
    }
}
package test.metier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modele.metier.Adresse;
import modele.metier.Client;

public class ClientTest {
    private Client c1;
    private Client c2;

    @Before
    public void setUp() throws Exception {
        Adresse adresse = new Adresse("99", "rue de test", "99999", "TestVille", "TestPays");
        c1 = new Client(1, "TEST", "Test", adresse);
        c2 = new Client(1, "TEST", "Test", adresse);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testHashCode() {
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    public void testEquals() {
        assertTrue(c1.equals(c2));
    }
}
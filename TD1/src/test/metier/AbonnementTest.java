package test.metier;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import modele.metier.Abonnement;

public class AbonnementTest {
    private Abonnement ab1;
    private Abonnement ab2;

    @Before
    public void setUp() {
        ab1 = new Abonnement(1, "01/01/2001", "01/01/2002", 1, 1);
        ab2 = new Abonnement(1, "02/01/2001", "01/01/2002", 1, 1);
    }

    @Test
    public void testVerifEquals() {
        assertTrue(ab1.equals(ab2));
    }

    @Test
    public void testHashCode() {
        assertNotEquals(ab1.hashCode(), ab2.hashCode());
    }
}

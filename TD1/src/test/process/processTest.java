package test.process;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import metier.Adresse;
import process.ProcessAdresse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

<<<<<<< Updated upstream
public class processTest {
    private ProcessAdresse pr;
    private Adresse ad;
    private Adresse adVrai;

    @Before
    public void setUp() {
        ad = new Adresse("6", " av drogon  ", "L-5400", "  st julien aux metz sur  ", " belgium     ");
        adVrai = new Adresse("6, ", "avenue drogon", "05400", "Saint-Julien-aux-Metz-sur", "Belgique");
        pr = new ProcessAdresse();
    }

    @Test
    public void NormalisationTest() {
        pr.normalizeAdresse(ad);
        assertTrue(ad.equals(adVrai));
=======
	@Test
	public void NormalisationTest() {

	}

	@Test
	public void NormalisationPaysTest() {
		assertEquals("Belgique", pr.normalizePays(ad.getPays()));
	}
>>>>>>> Stashed changes

    }

    @Test
    public void NormalisationPaysTest() {
        String pays = ad.getPays();
        pays = pr.normalizePays(pays);
        assertEquals("Belgique", pays);
    }

<<<<<<< Updated upstream
    @Test
    public void NormalisationNomDeVoieTest() {
        String nomVoie = ad.getVoie();
        nomVoie = pr.normalizeNomDeVoie(nomVoie);
        assertEquals("avenue drogon", nomVoie);

    }
=======
	@Test
	public void NormalisationVilleTest() {
		System.out.println(pr.normalizeNomDeVoie(ad.getVoie()));
	}
>>>>>>> Stashed changes

    @Test
    public void NormalisationVilleTest() {
        String ville = ad.getVille();
        ville = pr.normalizeVille(ville);
        assertEquals("Saint-Julien-aux-Metz-sur", ville);

    }

    @Test
    public void NormalisationCodePostalTest() {
        String cp = ad.getCode_postal();
        cp = pr.normalizeCodePostal(cp);
        assertEquals("05400", cp);
    }

    @Test
    public void NormalisationNumDeVoieTest() {
        String numVoie = ad.getNo_rue();
        numVoie = pr.normalizeNumDeVoie(numVoie);
        assertEquals("6, ", numVoie);
    }
}

package test.process;

import org.junit.Before;
import org.junit.Test;

import metier.Adresse;
import process.ProcessAdresse;

import static org.junit.Assert.assertEquals;

public class processTest {
	private ProcessAdresse pr;
	private Adresse ad;

	@Before
	public void setUp() {
		ad = new Adresse("6", " av drogon  ", "L-5400", "  st julien aux metz sur  ", " belgium     ");
		pr = new ProcessAdresse();
	}

	@Test
	public void NormalisationTest() {
	}

	@Test
	public void NormalisationPaysTest() {

	}

	@Test
	public void NormalisationNomDeVoieTest() {

	}

	@Test
	public void NormalisationVilleTest() {
		String ville= ad.getVille();
		ville=pr.normalizeVille(ville);
		assertEquals("Saint-Julien-aux-Metz-sur",ville);

	}

	@Test
	public void NormalisationCodePostalTest() {
		String cp=ad.getCode_postal();
		cp=pr.normalizeCodePostal(cp);
		assertEquals("05400",cp);
	}

	@Test
	public void NormalisationNumDeVoieTest() {
		String numVoie=ad.getNo_rue();
		numVoie=pr.normalizeNumDeVoie(numVoie);
		assertEquals("6, ",numVoie);
	}
}

package test.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import modele.metier.Adresse;
import process.ProcessAdresse;

public class processTest {
	private ProcessAdresse pr;
	private Adresse ad,ad1,ad2,ad3;

	@Before
	public void setUp() {
		ad = new Adresse("6", " av drogon  ", "L-5400", "  st julien aux metz sur  ", " belgium     ");
		ad1 = new Adresse("A1", "  boul drogon av qs faub. as fg ", "qs1q2d3h4h5g6s7", "  st julien lès metz sous rien  ", "     LETZEBUERG     ");
		ad2 = new Adresse("2,", " av. kata pl. taka ", "TT1", " montigny lès metz  ", "   sChWeiz     ");
		ad3 = new Adresse("3Q", "  bd fg pl.    ", "2A01", " st test lès metz  ", "  Switzerland     ");
		pr = new ProcessAdresse();
		pr.normalizeAdresse(ad);
		pr.normalizeAdresse(ad1);
		pr.normalizeAdresse(ad2);
		pr.normalizeAdresse(ad3);
	}

	@Test
	public void NormalisationPaysTest() {
		assertEquals("Belgique", ad.getPays());
		assertEquals("Luxembourg",ad1.getPays());
		assertEquals("Suisse",ad2.getPays(),ad3.getPays());
	}

	@Test
	public void NormalisationNomDeVoieTest() {
		assertEquals("avenue drogon", ad.getVoie());
		assertEquals("boulevard drogon avenue qs faubourg as faubourg",ad1.getVoie());
		assertEquals("avenue kata place taka",ad2.getVoie());
		assertEquals("boulevard faubourg place",ad3.getVoie());
	}

	@Test
	public void NormalisationVilleTest() {
		assertEquals("Saint-Julien-aux-Metz-sur", ad.getVille());
		assertEquals("Saint-Julien-lès-Metz-sous-Rien", ad1.getVille());
		assertEquals("Montigny-lès-Metz", ad2.getVille());
		assertEquals("Saint-Test-lès-Metz", ad3.getVille());
	}

	@Test
	public void NormalisationCodePostalTest() {
		assertEquals("05400", ad.getCode_postal());
		assertEquals("1234567", ad1.getCode_postal());
		assertEquals("00001", ad2.getCode_postal());
		assertEquals("00201", ad3.getCode_postal());
	}

	@Test
	public void NormalisationNumDeVoieTest() {
		assertEquals("6, ", ad.getNo_rue());
		assertEquals("A1, ", ad1.getNo_rue());
		assertEquals("2, ", ad2.getNo_rue());
		assertEquals("3Q, ", ad3.getNo_rue());
	}
}

package test.listememoire;

import static dao.Persistance.ListeMemoire;
import static daofactory.DaoFactory.getDAOFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import daofactory.DaoFactory;
import modele.metier.Abonnement;

public class ListeMemoireAbonnementDaoTest {
	private DaoFactory daof;
	private Abonnement ab;

	@Before
	public void setUp() {
		daof = getDAOFactory(ListeMemoire);
		ab = new Abonnement(1, "01/01/2001", "02/02/2002", 1, 1);
	}

	@Test
	public void testAjoutAbonnement() throws SQLException {
		int initSize = daof.getAbonnementDAO().findAll().size();
		daof.getAbonnementDAO().create(ab);
		assertEquals(initSize + 1, daof.getAbonnementDAO().findAll().size());
	}

	@Test
	public void testSupprimerAbonnement() throws SQLException {
		daof.getAbonnementDAO().create(ab);
		int initSize = daof.getAbonnementDAO().findAll().size();
		daof.getAbonnementDAO().delete(ab);
		assertEquals(initSize - 1, daof.getAbonnementDAO().findAll().size());
	}

	@Test
	public void testExistanceClientAbonnement() throws SQLException {
		assertNotNull(daof.getAbonnementDAO().getByClient(ab.getId_client()));
	}
}

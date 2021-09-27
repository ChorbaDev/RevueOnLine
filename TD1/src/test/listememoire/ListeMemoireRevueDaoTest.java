package test.listememoire;

import static dao.Persistance.ListeMemoire;
import static daofactory.DaoFactory.getDAOFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import daofactory.DaoFactory;
import org.junit.Before;
import org.junit.Test;

import metier.Revue;

public class ListeMemoireRevueDaoTest {

	private DaoFactory daof;
	private Revue r;

	@Before
	public void setUp() {
		daof = getDAOFactory(ListeMemoire);
		r = new Revue(1, "test", "descp", 1.6, "visuel", 1);
	}

	@Test
	public void testAjoutRevue() throws SQLException {
		int initSize = daof.getRevueDAO().findAll().size();

		daof.getRevueDAO().create(r);
		assertEquals(initSize + 1, daof.getRevueDAO().findAll().size());
	}

	@Test
	public void testSupprimerRevue() throws SQLException {
		daof.getRevueDAO().create(r);
		int initSize = daof.getRevueDAO().findAll().size();
		daof.getRevueDAO().delete(r);
		assertEquals(initSize - 1, daof.getRevueDAO().findAll().size());
	}

	@Test
	public void testExistanceTitreRevue() throws SQLException {
		daof.getRevueDAO().create(r);
		assertNotNull(daof.getRevueDAO().getByTitre(r.getTitre()).size());
	}
}

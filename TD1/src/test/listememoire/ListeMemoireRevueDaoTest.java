package test.listememoire;

import static dao.Persistance.ListeMemoire;
import static daofactory.DAOFactory.getDAOFactory;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import daofactory.DAOFactory;
import metier.Revue;

public class ListeMemoireRevueDaoTest {

	private DAOFactory daof;

	@Before
	public void setUp() {
		daof = getDAOFactory(ListeMemoire);
	}

	@Test
	public void testAjoutRevue() throws SQLException {
		int initSize = daof.getRevueDAO().findAll().size();
		Revue r = new Revue(1, "test", "descp", 1.6, "visuel", 1);
		daof.getRevueDAO().create(r);
		assertEquals(initSize + 1, daof.getRevueDAO().findAll().size());
	}

	@Test
	public void testSupprimerRevue() throws SQLException {
		Revue r = new Revue(1, "test", "descp", 1.2, "visuel", 1);
		daof.getRevueDAO().create(r);
		int initSize = daof.getRevueDAO().findAll().size();
		daof.getRevueDAO().delete(r);
		assertEquals(initSize - 1, daof.getRevueDAO().findAll().size());
	}

	/*
	 * @Test public void testExistanceIDRevue() throws SQLException { Revue r = new
	 * Revue(1, "test", "descp", 1.2, "visuel", 1); daof =
	 * DAOFactory.getDAOFactory(Persistance.ListeMemoire);
	 * daof.getRevueDAO().create(r); int initSize =
	 * daof.getRevueDAO().findAll().size(); daof.getRevueDAO().delete(r);
	 * assertEquals(initSize - 1, daof.getRevueDAO().findAll().size()); }
	 */
}

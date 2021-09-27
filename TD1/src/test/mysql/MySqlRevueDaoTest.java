package test.mysql;

import static dao.Persistance.MYSQL;
import static daofactory.DaoFactory.getDAOFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import daofactory.DaoFactory;
import metier.Revue;

public class MySqlRevueDaoTest {

	private DaoFactory daof;
	private Revue r;

	@Before
	public void setUp() {
		daof = getDAOFactory(MYSQL);
		r = new Revue("test", "descp", 1.6, "visuel", 1);
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

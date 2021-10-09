package test.mysql;

import static dao.Persistance.MYSQL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import daofactory.DaoFactory;
import metier.Adresse;
import metier.Client;

public class MySqlClientDaoTest {
	private DaoFactory daof;
	private Client c;

	@Before
	public void setUp() throws Exception {
		daof = DaoFactory.getDAOFactory(MYSQL);
		Adresse adresse = new Adresse("99", "rue de test", "99999", "TestVille", "TestPays");
		c = new Client(1, "TEST", "Test", adresse);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void create() throws SQLException {
		int initSize = daof.getClientDAO().findAll().size();
		daof.getClientDAO().create(c);
		assertEquals(initSize + 1, daof.getClientDAO().findAll().size());
	}

	@Test
	public void delete() throws SQLException {
		daof.getClientDAO().create(c);
		int initSize = daof.getClientDAO().findAll().size();
		daof.getClientDAO().delete(c);
		assertEquals(initSize - 1, daof.getClientDAO().findAll().size());
	}

	@Test
	public void getById() throws SQLException {
		daof.getClientDAO().create(c);
		assertTrue(daof.getClientDAO().findAll().get(daof.getClientDAO().findAll().size() - 1).getCle() >= c.getCle());
	}

	@Test
	public void getByNomPrenom() throws SQLException {
		daof.getClientDAO().create(c);
		assertNotNull(daof.getClientDAO().getByNomPrenom(c.getNom(), c.getPrenom()).size());
	}
}
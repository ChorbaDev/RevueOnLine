package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import DAO.Persistance;
import DAOFactory.DAOFactory;
import Metier.Revue;

public class RevueTest {

	@Test
	public void testAjoutRevueLM() throws SQLException {
		Revue r = new Revue(1, "test", "descp", 1.2, "visuel", 1);
		DAOFactory daof = DAOFactory.getDAOFactory(Persistance.ListeMemoire);
		daof.getRevueDAO().create(r);
		assertEquals(1, daof.getRevueDAO().findAll().size());
	}
}

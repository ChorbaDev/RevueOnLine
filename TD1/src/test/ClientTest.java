import dao.Persistance;
import daofactory.DAOFactory;
import metier.Client;
import org.junit.Test;

import java.sql.SQLException;

import static dao.Persistance.ListeMemoire;
import static org.junit.Assert.assertEquals;

public class ClientTest {
    @Test
    public void testAjoutClient() throws SQLException {
        Client c = new Client(1, "TEST", "Test", "99", "rue de test", "99999", "TestVille", "TestPays");
        DAOFactory daof = DAOFactory.getDAOFactory(ListeMemoire);
        daof.getClientDAO().create(c);
        assertEquals(1, daof.getClientDAO().findAll().size());
        daof=DAOFactory.getDAOFactory(Persistance.MYSQL);
        daof.getClientDAO().create(c);
        assertEquals(1, daof.getClientDAO().findAll().size());
    }

    @Test
    public void testSupprimerClient() throws SQLException{
        Client c = new Client(1, "TEST", "Test", "99", "rue de test", "99999", "TestVille", "TestPays");
        DAOFactory daof = DAOFactory.getDAOFactory(ListeMemoire);
        daof.getClientDAO().create(c);
        daof=DAOFactory.getDAOFactory(Persistance.MYSQL);
        daof.getClientDAO().create(c);
    }
}

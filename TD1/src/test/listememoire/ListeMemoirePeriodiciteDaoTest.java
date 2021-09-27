package test.listememoire;

import daofactory.DaoFactory;
import metier.Periodicite;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static dao.Persistance.ListeMemoire;
import static org.junit.Assert.*;

public class ListeMemoirePeriodiciteDaoTest {
	private DaoFactory daof;
	private Periodicite p;

	@Before
	public void setUp() throws Exception {
		daof = DaoFactory.getDAOFactory(ListeMemoire);
		p= new Periodicite(1,"Mensuel");
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void getById() throws SQLException {
		daof.getPeriodiciteDAO().create(p);
		assertTrue(daof.getPeriodiciteDAO().findAll().get(daof.getPeriodiciteDAO().findAll().size()-1).getCle()>=p.getCle());
	}

	@Test
	public void create() throws SQLException {
		int initSize=daof.getPeriodiciteDAO().findAll().size();
		daof.getPeriodiciteDAO().create(p);
		assertEquals(initSize+1,daof.getPeriodiciteDAO().findAll().size());
	}

	@Test
	public void delete() throws SQLException {
		daof.getPeriodiciteDAO().create(p);
		int initSize=daof.getPeriodiciteDAO().findAll().size();
		daof.getPeriodiciteDAO().delete(p);
		assertEquals(initSize-1,daof.getPeriodiciteDAO().findAll().size());
	}

	@Test
	public void getByLibelle() throws SQLException {
		daof.getPeriodiciteDAO().create(p);
		assertNotNull(daof.getPeriodiciteDAO().getByLibelle(p.getLibelle()).size());
	}
}
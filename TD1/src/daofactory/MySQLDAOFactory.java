package daofactory;

import dao.AbonnementDAO;
import dao.ClientDAO;
import dao.PeriodiciteDAO;
import dao.RevueDAO;
import mysql.MySQLAbonnementDAO;
import mysql.MySQLClientDAO;
import mysql.MySQLPeriodiciteDAO;
import mysql.MySQLRevueDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public AbonnementDAO getAbonnementDAO() {
		return MySQLAbonnementDAO.getInstance();
	}

	@Override
	public RevueDAO getRevueDAO() {
		return MySQLRevueDAO.getInstance();
	}

	@Override
	public PeriodiciteDAO getPeriodiciteDAO() {
		return MySQLPeriodiciteDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() {
		return MySQLClientDAO.getInstance();
	}
}

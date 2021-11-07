package daofactory;

import dao.AbonnementDao;
import dao.ClientDao;
import dao.PeriodiciteDao;
import dao.RevueDao;
import mysql.MySqlAbonnementDao;
import mysql.MySqlClientDao;
import mysql.MySqlPeriodiciteDao;
import mysql.MySqlRevueDao;

public class MySqlDaoFactory extends DaoFactory {

    @Override
    public AbonnementDao getAbonnementDAO() {
        return MySqlAbonnementDao.getInstance();
    }

    @Override
    public RevueDao getRevueDAO() {
        return MySqlRevueDao.getInstance();
    }

    @Override
    public PeriodiciteDao getPeriodiciteDAO() {
        return MySqlPeriodiciteDao.getInstance();
    }

    @Override
    public ClientDao getClientDAO() {
        return MySqlClientDao.getInstance();
    }
}

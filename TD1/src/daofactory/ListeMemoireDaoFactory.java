package daofactory;

import dao.*;
import listememoire.*;

public class ListeMemoireDaoFactory extends DaoFactory {

    @Override
    public AbonnementDao getAbonnementDAO() {
        return ListeMemoireAbonnementDao.getInstance();
    }

    @Override
    public RevueDao getRevueDAO() {
        return ListeMemoireRevueDao.getInstance();
    }

    @Override
    public PeriodiciteDao getPeriodiciteDAO() {
        return ListeMemoirePeriodiciteDao.getInstance();
    }

    @Override
    public ClientDao getClientDAO() {
        return ListeMemoireClientDao.getInstance();
    }

}

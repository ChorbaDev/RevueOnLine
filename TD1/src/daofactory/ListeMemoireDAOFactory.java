package daofactory;

import dao.*;
import listememoire.*;

public class ListeMemoireDAOFactory extends DAOFactory {

    @Override
    public AbonnementDAO getAbonnementDAO() {
        return ListeMemoireAbonnementDao.getInstance();
    }

    @Override
    public RevueDAO getRevueDAO() {
        return ListeMemoireRevueDao.getInstance();
    }

    @Override
    public PeriodiciteDAO getPeriodiciteDAO() {
        return ListeMemoirePeriodiciteDao.getInstance();
        //ListeMemoirePeriodiciteDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO() {
        return ListeMemoireClientDao.getInstance();
        //ListeMemoireClientDAO.getInstance();
    }

}

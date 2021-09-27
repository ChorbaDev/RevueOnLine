package daofactory;

import dao.*;
import listememoire.*;

public class ListeMemoireDAOFactory extends DAOFactory {

    @Override
    public AbonnementDAO getAbonnementDAO() {
        return ListeMemoireAbonnementDAO.getInstance();
    }

    @Override
    public RevueDAO getRevueDAO() {
        return ListeMemoireRevueDAO.getInstance();
    }

    @Override
    public PeriodiciteDAO getPeriodiciteDAO() {
        return ListeMemoirePeriodiciteDAO.getInstance();
        //ListeMemoirePeriodiciteDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO() {
        return ListeMemoireClientDAO.getInstance();
        //ListeMemoireClientDAO.getInstance();
    }

}
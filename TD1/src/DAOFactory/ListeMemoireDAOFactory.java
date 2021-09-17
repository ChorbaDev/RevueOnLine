package DAOFactory;

import DAO.*;
import ListeMemoire.*;

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
        return null;
        //ListeMemoirePeriodiciteDAO.getInstance();
    }

    @Override
    public ClientDAO getClientDAO() {
        return null;
        //ListeMemoireClientDAO.getInstance();
    }

}

package daofactory;
import dao.*;

public abstract class DaoFactory {

    public static DaoFactory getDAOFactory(Persistance cible) {
        DaoFactory daoF = null;
        switch (cible) {
            case MYSQL:
                daoF = new MySqlDaoFactory();
                break;
            case ListeMemoire:
                daoF = new ListeMemoireDaoFactory();
                break;
        }
        return daoF;
    }

    public abstract AbonnementDao getAbonnementDAO();

    public abstract RevueDao getRevueDAO();

    public abstract PeriodiciteDao getPeriodiciteDAO();

    public abstract ClientDao getClientDAO();
}

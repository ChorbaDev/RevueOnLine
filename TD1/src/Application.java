import Metier.Abonnement;
import Metier.Client;
import Metier.Periodicite;
import Metier.Revue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DAO.Persistance;
import DAOFactory.*;

public class Application {
    static DAOFactory daos=null;

    public static void main(String[] args) throws SQLException {
        System.out.println("---------Bienvenue dans notre application---------");
        System.out.println("Quelle table vous voulez manipuler? \n"
                + "1-Abonnement\n"
                + "2-Client\n"
                + "3-Periodicite\n"
                + "4-Revue");
        Scanner sc = new Scanner(System.in);
        int choixTable;
        do {
            choixTable = sc.nextInt();
        } while (choixTable < 1 || choixTable > 4);

        switch (choixTable) {
            case 1:
                manipAbonnement(sc);
                break;
            case 2:
                manipClient(sc);
                break;
            case 3:
                manipPeriodicite(sc);
                break;
            case 4:
                manipRevue(sc);
                break;
            default:
                break;
        }
    }

    private static void manipRevue(Scanner sc) throws SQLException {
        afficheCRUD();
        int choixOperation;
        do {
            choixOperation = sc.nextInt();
        } while (choixOperation < 1 || choixOperation > 4);
        daos=choixPersistance(sc);
        switch (choixOperation) {
            case 1:
                ;
                break;
            case 2:
                requestRevue(sc);
                break;
            case 3:
                ;
                break;
            case 4:
                ;
                break;
            default:
                break;
        }
    }

    private static void requestRevue(Scanner sc) throws SQLException {
        System.out.println("Affichage par:\n"
                + "1-ID\n"
                + "2-Titre\n");
        int choix;
        do {
            choix = sc.nextInt();
        } while (choix < 1 || choix > 4);
        switch (choix) {
            case 1:
                reqIdRevue(sc);
                break;
            case 2:
                reqTitreRevue(sc);
                break;
            default:
                break;
        }

    }

    private static void reqTitreRevue(Scanner sc) throws SQLException {
        String titre = sc.nextLine();
        ArrayList<Revue> listR = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getRevueDAO().getByTitre(titre);
        for (Revue r : listR) {
            System.out.println(r.toString());
        }
    }

    private static void reqIdRevue(Scanner sc) throws SQLException {
        int id = sc.nextInt();
        Revue r = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getRevueDAO().getById(id);
        System.out.println(r.toString());
    }

    private static void manipPeriodicite(Scanner sc) throws SQLException {
        afficheCRUD();
        int choixOperation;
        do {
            choixOperation = sc.nextInt();
        } while (choixOperation < 1 || choixOperation > 4);
        daos=choixPersistance(sc);
        switch (choixOperation) {
            case 1:
                ;
                break;
            case 2:
                requestPeriodicite(sc);
                break;
            case 3:
                ;
                break;
            case 4:
                ;
                break;
            default:
                break;
        }
    }

    private static void requestPeriodicite(Scanner sc) throws SQLException {
        System.out.println("Affichage par:\n"
                + "1-ID\n"
                + "2-Libelle\n");
        int choix;
        do {
            choix = sc.nextInt();
        } while (choix < 1 || choix > 4);
        switch (choix) {
            case 1:
                reqIdPeriodicite(sc);
                break;
            case 2:
                reqLibellePeriodicite(sc);
                break;
            default:
                break;
        }
    }

    private static void reqIdPeriodicite(Scanner sc) throws SQLException {
        int id = sc.nextInt();
        Periodicite p = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getPeriodiciteDAO().getById(id);
        System.out.println(p.toString());
    }

    private static void reqLibellePeriodicite(Scanner sc) throws SQLException {
        String libelle = sc.nextLine();
        ArrayList<Periodicite> listP = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getPeriodiciteDAO().getByLibelle(libelle);
        for (Periodicite p : listP) {
            System.out.println(p.toString());
        }
    }

    private static void manipClient(Scanner sc) throws SQLException {
        afficheCRUD();
        int choixOperation;
        do {
            choixOperation = sc.nextInt();
        } while (choixOperation < 1 || choixOperation > 4);
        daos=choixPersistance(sc);
        switch (choixOperation) {
            case 1:
                ;
                break;
            case 2:
                requestClient(sc);
                break;
            case 3:
                ;
                break;
            case 4:
                ;
                break;
            default:
                break;
        }
    }

    private static void requestClient(Scanner sc) throws SQLException {
        System.out.println("Affichage par:\n"
                + "1-ID\n"
                + "2-Nom et Prenom\n");
        int choix;
        do {
            choix = sc.nextInt();
        } while (choix < 1 || choix > 4);
        switch (choix) {
            case 1:
                reqIdClient(sc);
                break;
            case 2:
                System.out.println("Entrez, dans l'ordre, le nom puis le prenom");
                reqNomPrenomClient(sc);
                break;
            default:
                break;
        }
    }

    private static void reqNomPrenomClient(Scanner sc) throws SQLException {
        String nom = sc.nextLine();
        String prenom = sc.nextLine();
        ArrayList<Client> listC = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO().getByNomPrenom(nom, prenom);
        for (Client c : listC) {
            System.out.println(c.toString());
        }
    }

    private static void reqIdClient(Scanner sc) throws SQLException {
        int id = sc.nextInt();
        Client c = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getClientDAO().getById(id);
        System.out.println(c.toString());
    }

    private static void manipAbonnement(Scanner sc) {
        afficheCRUD();
    }

    private static void afficheCRUD() {
        System.out.println("Choisissez votre operation:\n"
                + "1-Create\n"
                + "2-Request\n"
                + "3-Update\n"
                + "4-Delete\n");

    }

    private static DAOFactory choixPersistance(Scanner sc) {
        System.out.println("Choix de la solution de persistance:\n"
                + "1-MySQL\n"
                + "2-ListeMemoire\n");
        int choix;
        do {
            choix = sc.nextInt();
        } while (choix < 1 || choix > 2);
        switch (choix) {
            case 1:
                daos=DAOFactory.getDAOFactory(Persistance.MYSQL);
                break;
            case 2:
                daos=DAOFactory.getDAOFactory(Persistance.ListeMemoire);
                break;
            default:
                break;
        }
        return daos;
    }
}
/*  // TODO Auto-generated method stub
Scanner scanner = new Scanner(System.in);
Client c = new Client("gho", "you", "5", "fekoe", "5700", "yutz", "fr");
Periodicite p = new Periodicite("bonjour");
Revue r = new Revue("Sa majesté le roi des mouches", " le roi de mouches", 10.95, "beau", 5);
Abonnement a = new Abonnement("10/09/21", "11/10/21", 12, 1);
System.out.println("que souhaitez vous faire ? : 1- ajouter, 2-modifier, 3-supprimer");
int choixtravail = scanner.nextInt();
System.out.println("sur quelle table souhaitez vous travailler ? : 1- Periodicité, 2-Metier.Client, 3-Metier.Revue, 4-Metier.Abonnement");
int choixtable = scanner.nextInt();
switch (choixtable) {
    case 1:
        switch (choixtravail) {
            case 1:
                p.addPeriodicite();
                break;
            case 2:
                p.updatePeriodicite("aurevoir");
                break;
            case 3:
                p.deletePeriodicite();
                break;
        }
    case 2:
        switch (choixtravail) {
            case 1:
                c.addClient();
                break;
            case 2:
                c.updateClient("gholem", "youss", "12", "the voice", "57500", "metz", "marsa");
                break;
            case 3:
                c.deleteClient();
                break;
        }
    case 3:
        switch (choixtravail) {
            case 1:
                r.addRevue();
                break;
            case 2:
                r.updateRevue("Sa majesté le roi des mouches", " le roi de mouches", 10.95, "beau", 5);
                break;
            case 3:
                r.deleteRevue();
                break;
        }
    case 4:
        switch (choixtravail) {
            case 1:
                a.addAbonnement();
                break;
            case 2:
                a.updateAbonnement("10/09/21", "11/10/22", 12, 1);
                break;
            case 3:
                a.deleteAbonnement();
                break;
        }

}


}*/

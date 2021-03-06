package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import daofactory.DaoFactory;
import daofactory.ListeMemoireDaoFactory;
import modele.metier.Adresse;
import modele.metier.Client;

public abstract class AppClient {

    public static void manipClient(Scanner sc, DaoFactory daof, int choixOperation) throws SQLException, IOException {
        switch (choixOperation) {
            case 1:
                createClient(sc, daof);
                ;
                break;
            case 2:
                requestClient(sc, daof);
                break;
            case 3:
                updateClient(sc, daof);
                ;
                break;
            case 4:
                deleteClient(sc, daof);
                ;
                break;
            default:
                break;
        }
    }

    private static void deleteClient(Scanner sc, DaoFactory daof) throws SQLException {
        System.out.print("ID Client :");
        int id = Application.verifID(sc);
        Client cl = new Client(id);
        daof.getClientDAO().delete(cl);
    }

    private static void updateClient(Scanner sc, DaoFactory daof) throws SQLException, IOException {
        int id;
        String nom, prenom, no_rue, voie, code_postal, ville, pays;
        System.out.print("ID Client :");
        id = Application.verifID(sc);
        System.out.print("Nom :");
        nom = sc.nextLine();
        System.out.print("Prenom :");
        prenom = sc.nextLine();
        System.out.print("No rue:");
        no_rue = sc.nextLine();
        System.out.print("Voie :");
        voie = sc.nextLine();
        System.out.print("Code postal :");
        code_postal = sc.nextLine();
        System.out.print("Ville :");
        ville = sc.nextLine();
        System.out.print("Pays :");
        pays = sc.nextLine();
        Adresse adresse = new Adresse(no_rue, voie, code_postal, ville, pays);
        Client cl = new Client(id, nom, prenom, adresse);
        daof.getClientDAO().update(cl);

    }

    private static void requestClient(Scanner sc, DaoFactory daof) throws SQLException, IOException {
        System.out.println("Affichage par:\n1-ID\n2-Nom & Prenom\n3-Tout\n");
        int choix = Application.verifChoix(sc, 1, 3);
        switch (choix) {
            case 1:
                reqIdClient(sc, daof);
                break;
            case 2:
                reqNPClient(sc, daof);
                break;
            case 3:
                reqAllClient(sc, daof);
                break;
            default:
                break;
        }

    }

    private static void reqAllClient(Scanner sc, DaoFactory daof) throws SQLException {
        ArrayList<Client> listeC = daof.getClientDAO().findAll();
        for (Client c : listeC)
            System.out.println(c.toString());

    }

    private static void reqNPClient(Scanner sc, DaoFactory daof) throws SQLException {
        String nom, prenom;
        System.out.print("Nom :");
        nom = sc.nextLine();
        System.out.print("Prenom :");
        prenom = sc.nextLine();
        ArrayList<Client> listeCL = daof.getClientDAO().getByNomPrenom(nom, prenom);
        for (Client cl : listeCL)
            System.out.println(cl.toString());

    }

    private static void reqIdClient(Scanner sc, DaoFactory daof) throws SQLException, IOException {
        int id = Application.verifID(sc);
        Client cl = daof.getClientDAO().getById(id);
        System.out.println(cl.toString());
    }

    private static void createClient(Scanner sc, DaoFactory daof) throws SQLException, IOException {
        String nom, prenom, no_rue, voie, code_postal, ville, pays;
        boolean isLM = daof instanceof ListeMemoireDaoFactory;
        Client cl;
        int id = 0;
        if (isLM) {
            System.out.print("ID Client :");
            id = Application.verifID(sc);
        }
        System.out.print("Nom :");
        nom = sc.nextLine();
        System.out.print("Prenom :");
        prenom = sc.nextLine();
        System.out.print("No rue:");
        no_rue = sc.nextLine();
        System.out.print("Voie :");
        voie = sc.nextLine();
        System.out.print("Code postal :");
        code_postal = sc.nextLine();
        System.out.print("Ville :");
        ville = sc.nextLine();
        System.out.print("Pays :");
        pays = sc.nextLine();
        Adresse adresse = new Adresse(no_rue, voie, code_postal, ville, pays);
        cl = new Client(id, nom, prenom, adresse);
        daof.getClientDAO().create(cl);

    }

}

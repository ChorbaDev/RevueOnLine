package Application;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import DAOFactory.DAOFactory;
import Metier.Abonnement;

public abstract class AppAbonnement {
    public static void manipAbonnement(Scanner sc, DAOFactory daof) throws SQLException, ParseException {
        afficheCRUD();
        int choixOperation;
        do {
            choixOperation = Integer.parseInt(sc.nextLine());
        } while (choixOperation < 1 || choixOperation > 4);
        switch (choixOperation) {
            case 1:
                createAbonnement(sc, daof);
                ;
                break;
            case 2:
                requestAbonnement(sc, daof);
                break;
            case 3:
                updateAbonnement(sc, daof);
                ;
                break;
            case 4:
                deleteAbonnement(sc, daof);
                ;
                break;
            default:
                break;
        }
    }

    private static void deleteAbonnement(Scanner sc, DAOFactory daof) throws SQLException, ParseException {
        boolean valid=false;
        String date_deb, date_fin;
        int id_c, id_r, id;
        System.out.print("ID Abonnement :");
        id = Integer.parseInt(sc.nextLine());
        do {
            System.out.print("Date debut jj/mm/yyyy :");
            date_deb = sc.nextLine();
            valid=validationDate(date_deb);
        }while(!valid);
        valid=false;
        do{
            System.out.print("Date fin jj/mm/yyyy :");
            date_fin = sc.nextLine();
            if (validationDate(date_fin) && compareDate(date_deb,date_fin) ) {
                valid = true;
            }
        } while (!valid);
        System.out.print("ID Client :");
        id_c = Integer.parseInt(sc.nextLine());
        System.out.print("ID Revue :");
        id_r = Integer.parseInt(sc.nextLine());
        Abonnement abonnement = new Abonnement(id, date_deb, date_fin, id_r, id_c);
        daof.getAbonnementDAO().delete(abonnement);
    }

    private static void afficheCRUD() {
        System.out
                .println("Choisissez votre operation:\n" + "1-Create\n" + "2-Request\n" + "3-Update\n" + "4-Delete\n");

    }

    private static void updateAbonnement(Scanner sc, DAOFactory daof) throws SQLException, ParseException {
        boolean valid=false;
        String date_deb, date_fin;
        int id_c, id_r, id;
        System.out.print("ID Abonnement :");
        id = Integer.parseInt(sc.nextLine());
        do {
            System.out.print("Date debut jj/mm/yyyy :");
            date_deb = sc.nextLine();
            valid=validationDate(date_deb);
        }while(!valid);
        valid=false;
        do{
            System.out.print("Date fin jj/mm/yyyy :");
            date_fin = sc.nextLine();
            if (validationDate(date_fin) && compareDate(date_deb,date_fin) ) {
                valid = true;
            }
        } while (!valid);
        System.out.print("ID Client :");
        id_c = Integer.parseInt(sc.nextLine());
        System.out.print("ID Revue :");
        id_r = Integer.parseInt(sc.nextLine());
        Abonnement abonnement = new Abonnement(id, date_deb, date_fin, id_r, id_c);
        daof.getAbonnementDAO().update(abonnement);
    }

    private static void requestAbonnement(Scanner sc, DAOFactory daof) throws SQLException {
        System.out.println("Affichage par:\n" + "1-ID\n" + "2-Client\n");
        int choix;
        do {
            choix = Integer.parseInt(sc.nextLine());
        } while (choix < 1 || choix > 4);
        switch (choix) {
            case 1:
                reqIdAbonnement(sc, daof);
                break;
            case 2:
                reqClientAbonnement(sc, daof);
                break;
            default:
                break;
        }

    }

    private static void createAbonnement(Scanner sc, DAOFactory daof) throws SQLException, ParseException {
        String date_deb, date_fin, jour, mois, annee;
        boolean valid = false;
        int id_c, id_r;
        do {
            System.out.print("Date debut jj/mm/yyyy :");
            date_deb = sc.nextLine();
            valid=validationDate(date_deb);
        }while(!valid);
        valid=false;
        do{
            System.out.print("Date fin jj/mm/yyyy :");
            date_fin = sc.nextLine();
            if (validationDate(date_fin) && compareDate(date_deb,date_fin) ) {
                valid = true;
            }
        } while (!valid);
        System.out.print("ID Client :");
        id_c = Integer.parseInt(sc.nextLine());
        System.out.print("ID Revue :");
        id_r = Integer.parseInt(sc.nextLine());
        Abonnement abonnement = new Abonnement(date_deb, date_fin, id_r, id_c);
        daof.getAbonnementDAO().create(abonnement);
    }

    private static void reqClientAbonnement(Scanner sc, DAOFactory daof) throws SQLException {
        int id_c = Integer.parseInt(sc.nextLine());
        ArrayList<Abonnement> listeAb = daof.getAbonnementDAO().getByClient(id_c);
        for (Abonnement ab : listeAb) {
            System.out.println(ab.toString());
        }

    }

    private static void reqIdAbonnement(Scanner sc, DAOFactory daof) throws SQLException {
        int id = Integer.parseInt(sc.nextLine());
        Abonnement ab = daof.getAbonnementDAO().getById(id);
        System.out.println(ab.toString());

    }

    private static boolean validationDate(String date) {
        boolean status = false;
        if (verifDate(date)) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                status = true;
            } catch (Exception e) {
                status = false;
            }
        }
        return status;
    }

    static boolean verifDate(String date) {
        String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean verif = false;
        if (date.matches(pattern)) {
            verif = true;
        }
        return verif;
    }

    static boolean compareDate(String date_deb, String date_fin) throws ParseException {
        boolean verif=false;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.parse(date_deb);
        if (dateFormat.parse(date_deb).before(dateFormat.parse(date_fin))){
            return true;
        }
        return false;
    }

}

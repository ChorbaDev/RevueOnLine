import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DAO.Persistance;
import DAOFactory.DAOFactory;
import Metier.Abonnement;
import Metier.Revue;

public class Application {
    static DAOFactory daos=null;

	public static void main(String[] args) throws SQLException {
		String reponse;
		System.out.println("---------Bienvenue dans notre application---------");
		do {
			System.out.println("Quelle table vous voulez manipuler? \n" + "1-Abonnement\n" + "2-Client\n"
					+ "3-Periodicite\n" + "4-Revue");
			Scanner sc = new Scanner(System.in);
			int choixTable;
			do {
				choixTable = Integer.parseInt(sc.nextLine());
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
			System.out.println("Voulez-vous continuer? O pour oui");
			reponse = sc.nextLine();
		} while (reponse.toUpperCase().equals("O"));
		System.out.println("Au revoir!");
	}

	private static void manipRevue(Scanner sc) throws SQLException {
		afficheCRUD();
		int choixOperation;
		do {
			choixOperation = Integer.parseInt(sc.nextLine());
		} while (choixOperation < 1 || choixOperation > 4);
		daos=choixPersistance(sc);
		switch (choixOperation) {
		case 1:
			createRevue(sc);
			;
			break;
		case 2:
			requestRevue(sc);
			break;
		case 3:
			updateRevue(sc);
			;
			break;
		case 4:
			deleteRevue(sc);
			;
			break;
		default:
			break;
		}
	}

	private static void deleteRevue(Scanner sc) throws SQLException {
		String titre, description, visuel;
		double tarif;
		int id_p, id;
		System.out.print("ID Revue:");
		id = Integer.parseInt(sc.nextLine());
		System.out.print("Titre :");
		titre = sc.nextLine();
		System.out.print("Description :");
		description = sc.nextLine();
		System.out.print("Visuel :");
		visuel = sc.nextLine();
		System.out.print("Tarif :");
		tarif = Double.parseDouble(sc.nextLine());
		System.out.print("Periodicite (ID) :");
		id_p = Integer.parseInt(sc.nextLine());
		Revue revue = new Revue(id, titre, description, tarif, visuel, id_p);
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getRevueDAO().delete(revue);
	}

	private static void updateRevue(Scanner sc) throws SQLException {
		String titre, description, visuel;
		double tarif;
		int id_p, id;
		System.out.print("ID Revue:");
		id = Integer.parseInt(sc.nextLine());
		System.out.print("Titre :");
		titre = sc.nextLine();
		System.out.print("Description :");
		description = sc.nextLine();
		System.out.print("Visuel :");
		visuel = sc.nextLine();
		System.out.print("Tarif :");
		tarif = Double.parseDouble(sc.nextLine());
		System.out.print("Periodicite (ID) :");
		id_p = Integer.parseInt(sc.nextLine());
		Revue revue = new Revue(id, titre, description, tarif, visuel, id_p);
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getRevueDAO().update(revue);
	}

	private static void createRevue(Scanner sc) throws SQLException {
		String titre, description, visuel;
		double tarif;
		int id_p;
		System.out.print("Titre :");
		titre = sc.nextLine();
		System.out.print("Description :");
		description = sc.nextLine();
		System.out.print("Visuel :");
		visuel = sc.nextLine();
		System.out.print("Tarif :");
		tarif = Double.parseDouble(sc.nextLine());
		System.out.print("Periodicite (ID) :");
		id_p = Integer.parseInt(sc.nextLine());
		Revue revue = new Revue(titre, description, tarif, visuel, id_p);
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getRevueDAO().create(revue);
	}

	private static void requestRevue(Scanner sc) throws SQLException {
		System.out.println("Affichage par:\n" + "1-ID\n" + "2-Titre\n");
		int choix;
		do {
			choix = Integer.parseInt(sc.nextLine());
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

	private static void manipPeriodicite(Scanner sc) {
		afficheCRUD();
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

	private static void manipAbonnement(Scanner sc) throws SQLException {
		afficheCRUD();
		int choixOperation;
		do {
			choixOperation = Integer.parseInt(sc.nextLine());
		} while (choixOperation < 1 || choixOperation > 4);
		switch (choixOperation) {
		case 1:
			createAbonnement(sc);
			;
			break;
		case 2:
			requestAbonnement(sc);
			break;
		case 3:
			updateAbonnement(sc);
			;
			break;
		case 4:
			deleteAbonnement(sc);
			;
			break;
		default:
			break;
		}
	}

	private static void deleteAbonnement(Scanner sc) throws SQLException {
		String date_deb, date_fin;
		int id_c, id_r, id;
		System.out.print("ID Abonnement :");
		id = Integer.parseInt(sc.nextLine());
		System.out.print("Date debut jj/mm/yyyy :");
		date_deb = sc.nextLine();
		System.out.print("Date fin jj/mm/yyyy :");
		date_fin = sc.nextLine();
		System.out.print("ID Client :");
		id_c = Integer.parseInt(sc.nextLine());
		System.out.print("ID Revue :");
		id_r = Integer.parseInt(sc.nextLine());
		Abonnement abonnement = new Abonnement(id, date_deb, date_fin, id_r, id_c);
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getAbonnementDAO().delete(abonnement);
	}

	private static void updateAbonnement(Scanner sc) throws SQLException {
		String date_deb, date_fin;
		int id_c, id_r, id;
		System.out.print("ID Abonnement :");
		id = Integer.parseInt(sc.nextLine());
		System.out.print("Date debut jj/mm/yyyy :");
		date_deb = sc.nextLine();
		System.out.print("Date fin jj/mm/yyyy :");
		date_fin = sc.nextLine();
		System.out.print("ID Client :");
		id_c = Integer.parseInt(sc.nextLine());
		System.out.print("ID Revue :");
		id_r = Integer.parseInt(sc.nextLine());
		Abonnement abonnement = new Abonnement(id, date_deb, date_fin, id_r, id_c);
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getAbonnementDAO().update(abonnement);
	}

	private static void requestAbonnement(Scanner sc) throws SQLException {
		System.out.println("Affichage par:\n" + "1-ID\n" + "2-Client\n");
		int choix;
		do {
			choix = Integer.parseInt(sc.nextLine());
		} while (choix < 1 || choix > 4);
		switch (choix) {
		case 1:
			reqIdAbonnement(sc);
			break;
		case 2:
			reqClientAbonnement(sc);
			break;
		default:
			break;
		}

	}

	private static void createAbonnement(Scanner sc) throws SQLException {
		String date_deb, date_fin;
		int id_c, id_r;
		System.out.print("Date debut jj/mm/yyyy :");
		date_deb = sc.nextLine();
		System.out.print("Date fin jj/mm/yyyy :");
		date_fin = sc.nextLine();
		System.out.print("ID Client :");
		id_c = Integer.parseInt(sc.nextLine());
		System.out.print("ID Revue :");
		id_r = Integer.parseInt(sc.nextLine());
		Abonnement abonnement = new Abonnement(date_deb, date_fin, id_r, id_c);
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getAbonnementDAO().create(abonnement);
	}
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

	private static void reqClientAbonnement(Scanner sc) throws SQLException {
		int id_c = Integer.parseInt(sc.nextLine());
		ArrayList<Abonnement> listeAb = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getAbonnementDAO()
				.getByClient(id_c);
		for (Abonnement ab : listeAb) {
			System.out.println(ab.toString());
		}

	}

	private static void reqIdAbonnement(Scanner sc) throws SQLException {
		int id = sc.nextInt();
		Abonnement ab = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getAbonnementDAO().getById(id);
		System.out.println(ab.toString());

	}

	private static void afficheCRUD() {
		System.out
				.println("Choisissez votre operation:\n" + "1-Create\n" + "2-Request\n" + "3-Update\n" + "4-Delete\n");

	}
}
/*
 * // TODO Auto-generated method stub Scanner scanner = new Scanner(System.in);
 * Client c = new Client("gho", "you", "5", "fekoe", "5700", "yutz", "fr");
 * Periodicite p = new Periodicite("bonjour"); Revue r = new
 * Revue("Sa majesté le roi des mouches", " le roi de mouches", 10.95, "beau",
 * 5); Abonnement a = new Abonnement("10/09/21", "11/10/21", 12, 1); System.out.
 * println("que souhaitez vous faire ? : 1- ajouter, 2-modifier, 3-supprimer");
 * int choixtravail = scanner.nextInt(); System.out.
 * println("sur quelle table souhaitez vous travailler ? : 1- Periodicité, 2-Metier.Client, 3-Metier.Revue, 4-Metier.Abonnement"
 * ); int choixtable = scanner.nextInt(); switch (choixtable) { case 1: switch
 * (choixtravail) { case 1: p.addPeriodicite(); break; case 2:
 * p.updatePeriodicite("aurevoir"); break; case 3: p.deletePeriodicite(); break;
 * } case 2: switch (choixtravail) { case 1: c.addClient(); break; case 2:
 * c.updateClient("gholem", "youss", "12", "the voice", "57500", "metz",
 * "marsa"); break; case 3: c.deleteClient(); break; } case 3: switch
 * (choixtravail) { case 1: r.addRevue(); break; case 2:
 * r.updateRevue("Sa majesté le roi des mouches", " le roi de mouches", 10.95,
 * "beau", 5); break; case 3: r.deleteRevue(); break; } case 4: switch
 * (choixtravail) { case 1: a.addAbonnement(); break; case 2:
 * a.updateAbonnement("10/09/21", "11/10/22", 12, 1); break; case 3:
 * a.deleteAbonnement(); break; }
 *
 * }
 *
 *
 * }
 */

package Application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DAOFactory.DAOFactory;
import Metier.Abonnement;

public abstract class AppAbonnement {
	public static void manipAbonnement(Scanner sc, DAOFactory daof) throws SQLException {
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

	private static void deleteAbonnement(Scanner sc, DAOFactory daof) throws SQLException {
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
		daof.getAbonnementDAO().delete(abonnement);
	}

	private static void afficheCRUD() {
		System.out
				.println("Choisissez votre operation:\n" + "1-Create\n" + "2-Request\n" + "3-Update\n" + "4-Delete\n");

	}

	private static void updateAbonnement(Scanner sc, DAOFactory daof) throws SQLException {
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

	private static void createAbonnement(Scanner sc, DAOFactory daof) throws SQLException {
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
}

package Application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DAO.Persistance;
import DAOFactory.DAOFactory;
import Metier.Periodicite;

public abstract class AppPeriodicite {

	public static void manipPeriodicite(Scanner sc) throws SQLException {
		afficheCRUD();
		int choixOperation;
		do {
			choixOperation = Integer.parseInt(sc.nextLine());
		} while (choixOperation < 1 || choixOperation > 4);
		switch (choixOperation) {
		case 1:
			createPeriodicite(sc);
			;
			break;
		case 2:
			requestPeriodicite(sc);
			break;
		case 3:
			updatePeriodicite(sc);
			;
			break;
		case 4:
			deletePeriodicite(sc);
			;
			break;
		default:
			break;
		}
	}

	private static void deletePeriodicite(Scanner sc) throws SQLException {
		String libelle;
		int id;
		System.out.print("ID Periodicite :");
		id = Integer.parseInt(sc.nextLine());
		System.out.print("Libelle :");
		libelle = sc.nextLine();
		Periodicite p = new Periodicite(id, libelle);
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getPeriodiciteDAO().delete(p);
	}

	private static void updatePeriodicite(Scanner sc) throws SQLException {
		String libelle;
		int id;
		System.out.print("ID Periodicite :");
		id = Integer.parseInt(sc.nextLine());
		System.out.print("Libelle :");
		libelle = sc.nextLine();
		Periodicite p = new Periodicite(id, libelle);
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getPeriodiciteDAO().update(p);

	}

	private static void afficheCRUD() {
		System.out
				.println("Choisissez votre operation:\n" + "1-Create\n" + "2-Request\n" + "3-Update\n" + "4-Delete\n");

	}

	private static void requestPeriodicite(Scanner sc) throws SQLException {
		System.out.println("Affichage par:\n" + "1-ID\n" + "2-Libelle\n");
		int choix;
		do {
			choix = Integer.parseInt(sc.nextLine());
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

	private static void reqLibellePeriodicite(Scanner sc) throws SQLException {
		String lib = sc.nextLine();
		ArrayList<Periodicite> listeP = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getPeriodiciteDAO()
				.getByLibelle(lib);
		for (Periodicite p : listeP)
			System.out.println(p.toString());

	}

	private static void reqIdPeriodicite(Scanner sc) throws SQLException {
		int id = Integer.parseInt(sc.nextLine());
		Periodicite ab = DAOFactory.getDAOFactory(Persistance.ListeMemoire).getPeriodiciteDAO().getById(id);
		System.out.println(ab.toString());

	}

	private static void createPeriodicite(Scanner sc) throws SQLException {
		String libelle;
		System.out.print("Libelle :");
		libelle = sc.nextLine();
		Periodicite p = new Periodicite(libelle);
		DAOFactory.getDAOFactory(Persistance.ListeMemoire).getPeriodiciteDAO().create(p);

	}

}

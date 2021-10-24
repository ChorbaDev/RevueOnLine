package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import daofactory.DaoFactory;
import daofactory.ListeMemoireDaoFactory;
import modele.metier.Periodicite;
import modele.metier.Revue;

public abstract class AppRevue {

	public static void manipRevue(Scanner sc, DaoFactory daof, int choixCRUD) throws SQLException, IOException, ClassNotFoundException {

		switch (choixCRUD) {
		case 1:
			createRevue(sc, daof);
			;
			break;
		case 2:
			requestRevue(sc, daof);
			break;
		case 3:
			updateRevue(sc, daof);
			;
			break;
		case 4:
			deleteRevue(sc, daof);
			;
			break;
		default:
			break;
		}
	}

	private static void deleteRevue(Scanner sc, DaoFactory daof) throws SQLException, ClassNotFoundException {
		int id;
		System.out.print("ID Revue:");
		id = Application.verifID(sc);
		Revue revue = new Revue(id);
		daof.getRevueDAO().delete(revue);
	}

	private static void updateRevue(Scanner sc, DaoFactory daof) throws SQLException, IOException, ClassNotFoundException {
		String titre, description, visuel;
		double tarif;
		int id_p, id;
		System.out.print("ID Revue:");
		id = Application.verifID(sc);
		System.out.print("Titre :");
		titre = sc.nextLine();
		System.out.print("Description :");
		description = sc.nextLine();
		System.out.print("Visuel :");
		visuel = sc.nextLine();
		System.out.print("Tarif :");
		tarif = Application.verifPrix(sc);
		do {
			System.out.print("Periodicite (ID) :");
			id_p = Application.verifID(sc);
		} while (!existancePeriodicite(id_p, daof));
		Revue revue = new Revue(id, titre, description, tarif, id_p);
		daof.getRevueDAO().update(revue);
	}

	private static void createRevue(Scanner sc, DaoFactory daof) throws SQLException, IOException, ClassNotFoundException {
		String titre, description, visuel;
		double tarif;
		int id_p, id = 0;
		boolean isLM = daof instanceof ListeMemoireDaoFactory;
		if (isLM) {
			System.out.print("ID Revue:");
			id = Application.verifID(sc);
		}
		System.out.print("Titre :");
		titre = sc.nextLine();
		System.out.print("Description :");
		description = sc.nextLine();
		System.out.print("Visuel :");
		visuel = sc.nextLine();
		System.out.print("Tarif :");
		tarif = Application.verifPrix(sc);
		do {
			System.out.print("Periodicite (ID) :");
			id_p = Application.verifID(sc);
		} while (!existancePeriodicite(id_p, daof));
		Revue revue = new Revue(id, titre, description, tarif, id_p);
		daof.getRevueDAO().create(revue);
	}

	private static boolean existancePeriodicite(int id_p, DaoFactory daof) throws SQLException {
		Periodicite p;
		try {
			p = daof.getPeriodiciteDAO().getById(id_p);
			String ch = p.toString();
		} catch (Exception e) {
			p = null;
			return false;
		}
		return true;
	}

	private static void requestRevue(Scanner sc, DaoFactory daof) throws SQLException, IOException, ClassNotFoundException {
		System.out.println("Affichage par:\n1-ID\n2-Titre\n3-Tout\n");
		int choix;
		do {
			choix = Integer.parseInt(sc.nextLine());
		} while (choix < 1 || choix > 4);
		switch (choix) {
		case 1:
			reqIdRevue(sc, daof);
			break;
		case 2:
			reqTitreRevue(sc, daof);
			break;
		case 3:
			reqAllRevue(sc, daof);
			break;
		default:
			break;
		}

	}

	private static void reqAllRevue(Scanner sc, DaoFactory daof) throws SQLException, IOException, ClassNotFoundException {
		ArrayList<Revue> listeR = daof.getRevueDAO().findAll();
		for (Revue r : listeR)
			System.out.println(r.toString());

	}

	private static void reqTitreRevue(Scanner sc, DaoFactory daof) throws SQLException, IOException, ClassNotFoundException {
		String titre = sc.nextLine();
		ArrayList<Revue> listR = daof.getRevueDAO().getByTitre(titre);
		for (Revue r : listR) {
			System.out.println(r.toString());
		}
	}

	private static void reqIdRevue(Scanner sc, DaoFactory daof) throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(sc.nextLine());
		Revue r = daof.getRevueDAO().getById(id);
		System.out.println(r.toString());
	}

}

package application;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import daofactory.DAOFactory;
import daofactory.ListeMemoireDAOFactory;
import metier.Abonnement;
import metier.Client;
import metier.Revue;

public abstract class AppAbonnement {
	public static void manipAbonnement(Scanner sc, DAOFactory daof, int choixCRUD) throws SQLException, ParseException {
		switch (choixCRUD) {
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

	private static void createAbonnement(Scanner sc, DAOFactory daof) throws SQLException, ParseException {
		String date_deb, date_fin;
		boolean valid = false;
		int id_c, id_r, id = 0;
		boolean isLM = daof instanceof ListeMemoireDAOFactory;
		if (isLM) {
			System.out.print("ID Abonnement :");
			id_c = Integer.parseInt(sc.nextLine());
		}
		do {
			System.out.print("Date debut jj/mm/yyyy :");
			date_deb = sc.nextLine();
			valid = validationDate(date_deb);
		} while (!valid);
		valid = false;
		do {
			System.out.print("Date fin jj/mm/yyyy :");
			date_fin = sc.nextLine();
			if (validationDate(date_fin) && compareDate(date_deb, date_fin)) {
				valid = true;
			}
		} while (!valid);
		do {
			System.out.print("ID Client :");
			id_c = Application.verifID(sc);
		} while (!existanceClient(id_c, daof));
		do {
			System.out.print("ID Revue :");
			id_r = Application.verifID(sc);
		} while (!existanceRevue(id_r, daof));
		Abonnement abonnement = new Abonnement(id, date_deb, date_fin, id_r, id_c);
		daof.getAbonnementDAO().create(abonnement);
	}

	private static void deleteAbonnement(Scanner sc, DAOFactory daof) throws SQLException, ParseException {
		int id;
		System.out.print("ID Abonnement :");
		id = Application.verifID(sc);
		Abonnement abonnement = new Abonnement(id);
		daof.getAbonnementDAO().delete(abonnement);
	}

	private static void updateAbonnement(Scanner sc, DAOFactory daof) throws SQLException, ParseException {
		boolean valid = false;
		String date_deb, date_fin;
		int id_c, id_r, id;
		System.out.print("ID Abonnement :");
		id = Application.verifID(sc);
		do {
			System.out.print("Date debut jj/mm/yyyy :");
			date_deb = sc.nextLine();
			valid = validationDate(date_deb);
		} while (!valid);
		valid = false;
		do {
			System.out.print("Date fin jj/mm/yyyy :");
			date_fin = sc.nextLine();
			if (validationDate(date_fin) && compareDate(date_deb, date_fin)) {
				valid = true;
			}
		} while (!valid);

		do {
			System.out.print("ID Client :");
			id_c = Application.verifID(sc);
		} while (!existanceClient(id_c, daof));
		do {
			System.out.print("ID Revue :");
			id_r = Application.verifID(sc);
		} while (existanceRevue(id_r, daof));

		Abonnement abonnement = new Abonnement(id, date_deb, date_fin, id_c, id_r);
		daof.getAbonnementDAO().update(abonnement);
	}

	private static boolean existanceClient(int id_c, DAOFactory daof) throws SQLException {
		Client cl;
		try {
			cl = daof.getClientDAO().getById(id_c);
			String ch = cl.toString();
		} catch (Exception e) {
			cl = null;
			return false;
		}
		return true;
	}

	private static boolean existanceRevue(int id_r, DAOFactory daof) {
		Revue r;
		try {
			r = daof.getRevueDAO().getById(id_r);
			String ch = r.toString();
		} catch (Exception e) {
			r = null;
			return false;
		}
		return true;
	}

	private static void requestAbonnement(Scanner sc, DAOFactory daof) throws SQLException {
		System.out.println("Affichage par:\n1-ID\n2-Client\n-3Abonemment");
		int choix = Application.verifChoix(sc, 1, 3);
		switch (choix) {
		case 1:
			reqIdAbonnement(sc, daof);
			break;
		case 2:
			reqClientAbonnement(sc, daof);
			break;
		case 3:
			reqAllAbonnement(sc, daof);
			break;
		default:
			break;
		}

	}

	private static void reqAllAbonnement(Scanner sc, DAOFactory daof) throws SQLException {
		ArrayList<Abonnement> listeAb = daof.getAbonnementDAO().findAll();
		for (Abonnement ab : listeAb)
			System.out.println(ab.toString());
	}

	private static void reqClientAbonnement(Scanner sc, DAOFactory daof) throws SQLException {
		int id_c = Application.verifID(sc);
		ArrayList<Abonnement> listeAb = daof.getAbonnementDAO().getByClient(id_c);
		for (Abonnement ab : listeAb) {
			System.out.println(ab.toString());
		}

	}

	private static void reqIdAbonnement(Scanner sc, DAOFactory daof) throws SQLException {
		int id = Application.verifID(sc);
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
		if (date.matches(pattern)) {
			return true;
		}
		return false;
	}

	static boolean compareDate(String date_deb, String date_fin) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.parse(date_deb);
		if (dateFormat.parse(date_deb).before(dateFormat.parse(date_fin))) {
			return true;
		}
		return false;
	}

}
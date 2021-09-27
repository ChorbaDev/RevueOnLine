package Application;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import DAO.Persistance;
import DAOFactory.DAOFactory;

public class Application {

	public static void main(String[] args) throws SQLException, ParseException {
		String reponse;
		int choixCRUD;
		int choixTable;
		System.out.println("---------Bienvenue dans notre application---------");
		Scanner sc = new Scanner(System.in);
		System.out.println("Choissisez entre:\n1-My SQL\n2-Liste Memoire");
		int idxPer = verifChoix(sc, 1, 2);
		Persistance persistance;
		if (idxPer == 1)
			persistance = Persistance.MYSQL;
		else
			persistance = Persistance.ListeMemoire;
		do {
			System.out.println("Quelle table vous voulez manipuler? \n" + "1-Abonnement\n" + "2-Client\n"
					+ "3-Periodicite\n" + "4-Revue");
			DAOFactory daof = DAOFactory.getDAOFactory(persistance);
			choixTable = verifChoix(sc, 1, 4);
			afficheCRUD();
			choixCRUD = verifChoix(sc, 1, 4);
			switch (choixTable) {
			case 1:
				AppAbonnement.manipAbonnement(sc, daof, choixCRUD);
				break;
			case 2:
				AppClient.manipClient(sc, daof, choixCRUD);
				break;
			case 3:
				AppPeriodicite.manipPeriodicite(sc, daof, choixCRUD);
				break;
			case 4:
				AppRevue.manipRevue(sc, daof, choixCRUD);
				break;
			default:
				break;
			}
			do{
				System.out.println("Voulez-vous continuer? O pour oui");
				reponse = sc.nextLine();
			}while (reponse.isEmpty());
		} while (reponse.equalsIgnoreCase("O"));
		System.out.println("Au revoir!");
	}

	public static int verifChoix(Scanner sc, int binf, int bsup) {
		int choixOperation;
		do {
			String chChoix = sc.nextLine();
			choixOperation = isNumeric(chChoix) ? Integer.parseInt(chChoix) : 0;
		} while (choixOperation < binf || choixOperation > bsup);
		return choixOperation;
	}

	public static int verifID(Scanner sc) {
		int id;
		do {
			String chID = sc.nextLine();
			id = isNumeric(chID) ? Integer.parseInt(chID) : 0;
		} while (id <= 0);
		return id;
	}

	public static double verifPrix(Scanner sc) {
		double Prix;
		do {
			String chPrix = sc.nextLine();
			Prix = isNumeric(chPrix) ? Double.parseDouble(chPrix) : 0;
		} while (Prix <= 0);
		return Prix;
	}

	private static void afficheCRUD() {
		System.out
				.println("Choisissez votre operation:\n" + "1-Create\n" + "2-Request\n" + "3-Update\n" + "4-Delete\n");
	}

	private static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}

package Application;

import java.sql.SQLException;
import java.util.Scanner;

import DAOFactory.DAOFactory;

public class Application {
	static DAOFactory daos = null;

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
				AppAbonnement.manipAbonnement(sc);
				break;
			case 2:
				AppClient.manipClient(sc);
				break;
			case 3:
				AppPeriodicite.manipPeriodicite(sc);
				break;
			case 4:
				AppRevue.manipRevue(sc);
				break;
			default:
				break;
			}
			System.out.println("Voulez-vous continuer? O pour oui");
			reponse = sc.nextLine();
		} while (reponse.toUpperCase().equals("O"));
		System.out.println("Au revoir!");
	}

}

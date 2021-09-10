import java.sql.SQLException;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		Client c=new Client("gho","you","5","fekoe","5700","yutz","fr");
		Periodicite p= new Periodicite("bonjour");
		System.out.println("que souhaitez vous faire ? : 1- ajouter, 2-modifier, 3-supprimer");
		int choixtravail=scanner.nextInt();
		System.out.println("sur quelle table souhaitez vous travailler ? : 1- Periodicité, 2-Client, 3-Revue, 4-Abonnement");
		int choixtable= scanner.nextInt();
		switch (choixtable){
			case 1:
				switch (choixtravail){
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
				switch (choixtravail){
					case 1:
						c.ajoutClient();
						break;
					case 2:
						c.updateClient("gholem","youss","12","the voice","57500","metz","marsa");
						break;
					case 3:
						c.deleteClient();
						break;
				}
			case 3:
				switch (choixtravail){
					case 1:
						c.ajoutClient();
						break;
					case 2:
						c.updateClient("gholem","youss","12","the voice","57500","metz","marsa");
						break;
					case 3:
						c.deleteClient();
						break;
				}
			case 4:
				switch (choixtravail){
					case 1:
						c.ajoutClient();
						break;
					case 2:
						c.updateClient("gholem","youss","12","the voice","57500","metz","marsa");
						break;
					case 3:
						c.deleteClient();
						break;
				}

		}






	}


}

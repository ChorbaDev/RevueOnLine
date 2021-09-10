import java.sql.SQLException;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		Client c=new Client("gho","you","5","fekoe","5700","yutz","fr");
		System.out.println("que souhaitez vous faire ? : 1- ajouter, 2-modifier, 3-supprimer");
		int choixtravail=scanner.nextInt();
		System.out.println("sur quelle table souhaitez vous travailler ? : 1- client, 2-periodicité, 3-revue, 4-abonnement");
		int choixtable=scanner.nextInt();




	}

}

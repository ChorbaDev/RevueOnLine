import java.sql.SQLException;

public class Application {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Abonnement a=new Abonnement("02/02/2020","02/02/2021",1,4);
		a.addAbonnement();
		a.updateAbonnement("03/04/2021","03/04/2024",2,2);
	}

}

package DAO;
import java.util.ArrayList;
import Metier.*;
public interface AbonnementDAO extends DAO<Abonnement>{
	public ArrayList<Abonnement> getByClient (Client cl);
}

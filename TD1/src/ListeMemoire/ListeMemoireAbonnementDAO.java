package ListeMemoire;

import DAO.AbonnementDAO;
import Metier.*;
import java.sql.*;
import java.util.*;
import java.time.*;

public class ListeMemoireAbonnementDAO implements AbonnementDAO{
	private ListeMemoireAbonnementDAO instance;
	private List<Abonnement> donnees;
	private ListeMemoireAbonnementDAO() {
		donnees=new ArrayList<>();
	}
	public ListeMemoireAbonnementDAO getInstance() {
		if(instance==null)
			instance=new ListeMemoireAbonnementDAO();
			return instance;
	}
	@Override
	public Abonnement getById(int id) throws SQLException {
		return null;
	}
	@Override
	public boolean create(Abonnement object) throws SQLException {
		
		return false;
	}
	@Override
	public boolean update(Abonnement object) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Abonnement object) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public ArrayList<Abonnement> getByClient(Client cl) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
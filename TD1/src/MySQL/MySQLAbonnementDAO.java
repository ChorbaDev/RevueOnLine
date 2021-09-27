package MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.AbonnementDAO;
import Metier.Abonnement;
import Metier.Connexion;

public class MySQLAbonnementDAO implements AbonnementDAO {
	private static MySQLAbonnementDAO instance;

	private MySQLAbonnementDAO() {
	}

	public static MySQLAbonnementDAO getInstance() {
		if (instance == null)
			instance = new MySQLAbonnementDAO();
		return instance;
	}

	@Override
	public Abonnement getById(int id) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		String sql = "select date_debut, date_fin, id_client, id_revue from Abonnement where id_abonnement=?";
		PreparedStatement req = connect.prepareStatement(sql);
		ResultSet res = req.executeQuery();
		Abonnement abonnement = null;
		if (res.next()) {
			LocalDate dateDebut = res.getDate(1).toLocalDate();
			LocalDate dateFin = res.getDate(2).toLocalDate();
			abonnement = new Abonnement(id, dateDebut, dateFin, res.getInt(3), res.getInt(4));
		}

		if (res != null)
			res.close();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return abonnement;
	}

	@Override
	public boolean create(Abonnement a) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		String sql = "insert into Abonnement(date_debut,date_fin,id_client,id_revue) values (?,?,?,?)";
		PreparedStatement req = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		req.setDate(1, java.sql.Date.valueOf(a.getDate_debut()));
		req.setDate(2, java.sql.Date.valueOf(a.getDate_fin()));
		req.setInt(3, a.getId_client());
		req.setInt(4, a.getId_revue());
		int nbLignes = req.executeUpdate();
		ResultSet res = req.getGeneratedKeys();

		if (res.next())
			a.setId(res.getInt(1));
		if (res != null)
			res.close();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return nbLignes == 1;
	}

	@Override
	public boolean update(Abonnement a) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		String sql = "update Abonnement set id_client=? , id_revue=? , date_debut=? , date_fin=? where id_abonnement=? ";
		PreparedStatement req = connect.prepareStatement(sql);
		req.setInt(1, a.getId_client());
		req.setInt(2, a.getId_revue());
		req.setDate(3, java.sql.Date.valueOf(a.getDate_debut()));
		req.setDate(4, java.sql.Date.valueOf(a.getDate_fin()));
		req.setInt(5, a.getId());
		int nbLignes = req.executeUpdate();

		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return nbLignes == 1;
	}

	@Override
	public boolean delete(Abonnement a) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		String sql = "delete from Abonnement where id_abonnement=? ";
		PreparedStatement req = connect.prepareStatement(sql);
		req.setInt(1, a.getId());
		int nbLignes = req.executeUpdate();

		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return nbLignes == 1;
	}

	@Override
	public ArrayList<Abonnement> getByClient(int cl) throws SQLException {
		ArrayList<Abonnement> list = new ArrayList<>();
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		String sql = "select * from Abonnement where id_client=?";
		PreparedStatement req = connect.prepareStatement(sql);
		req.setInt(1, cl);
		ResultSet res = req.executeQuery();
		while (res.next()) {
			list.add(new Abonnement(res.getInt(1), res.getDate(2).toLocalDate(), res.getDate(3).toLocalDate(), cl,
					res.getInt(5)));
		}
		return list;
	}

	@Override
	public ArrayList<Abonnement> findAll() throws SQLException {
		ArrayList<Abonnement> list = new ArrayList<>();
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		String sql = "select * from Abonnement where id_client=?";
		PreparedStatement req = connect.prepareStatement(sql);
		ResultSet res = req.executeQuery();
		while (res.next()) {
			list.add(new Abonnement(res.getInt(1), res.getDate(2).toLocalDate(), res.getDate(3).toLocalDate(),
					res.getInt(4), res.getInt(5)));
		}
		return list;
	}
}

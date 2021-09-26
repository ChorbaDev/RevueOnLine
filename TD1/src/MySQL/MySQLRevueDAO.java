package MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.RevueDAO;
import Metier.Connexion;
import Metier.Revue;

public class MySQLRevueDAO implements RevueDAO {
	private static MySQLRevueDAO instance;

	private MySQLRevueDAO() {
	}

	public static MySQLRevueDAO getInstance() {
		if (instance == null)
			instance = new MySQLRevueDAO();
		return instance;
	}

	@Override
	public Revue getById(int id) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		String sql = "select titre,description,tarif_numero,visuel,id_periodicite from Revue where id_revue=?";
		PreparedStatement req = connect.prepareStatement(sql);
		req.setInt(1, id);
		ResultSet res = req.executeQuery();
		Revue revue = null;
		if (res.next())
			revue = new Revue(id, res.getString(1), res.getString(2), res.getDouble(3), res.getString(4),
					res.getInt(5));

		if (res != null)
			res.close();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return revue;

	}

	@Override
	public boolean create(Revue r) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();

		String sql = "insert into Revue(titre,description,tarif_numero,visuel,id_periodicite) values (?,?,?,?,?)";
		PreparedStatement req = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		req.setString(1, r.getTitre());
		req.setString(2, r.getDescription());
		req.setDouble(3, r.getTarif_numero());
		req.setString(4, r.getVisuel());
		req.setInt(5, r.getId_p());

		int nbLignes = req.executeUpdate();
		ResultSet res = req.getGeneratedKeys();

		if (res.next())
			r.setId(res.getInt(1));
		if (res != null)
			res.close();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return nbLignes == 1;
	}

	@Override
	public boolean update(Revue r) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		String sql = "update Revue set titre=? , description=? , tarif_numero=? , visuel=? , id_periodicite=? where id_revue=?";
		PreparedStatement req = connect.prepareStatement(sql);
		req.setString(1, r.getTitre());
		req.setString(2, r.getDescription());
		req.setDouble(3, r.getTarif_numero());
		req.setString(4, r.getVisuel());
		req.setInt(5, r.getId_p());
		req.setInt(6, r.getId());
		int nbLignes = req.executeUpdate();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return nbLignes == 1;
	}

	@Override
	public boolean delete(Revue r) throws SQLException {
		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		String sql = "delete from Revue where id_revue=?";
		PreparedStatement req = connect.prepareStatement(sql);
		req.setInt(1, r.getId());
		int nbLignes = req.executeUpdate();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return nbLignes == 1;
	}

	@Override
	public ArrayList<Revue> getByTitre(String titre) throws SQLException {
		ArrayList<Revue> list = new ArrayList<>();

		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		String sql = "select * from Revue where titre=?";
		PreparedStatement req = connect.prepareStatement(sql);
		req.setString(1, titre);
		ResultSet res = req.executeQuery();

		while (res.next()) {
			list.add(new Revue(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getString(5),
					res.getInt(6)));
		}

		if (res != null)
			res.close();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return list;
	}

	@Override
	public ArrayList<Revue> findAll() throws SQLException {
		ArrayList<Revue> list = new ArrayList<>();

		Connexion maConnexion = new Connexion();
		Connection connect = maConnexion.creeConnexion();
		String sql = "select * from Revue";
		PreparedStatement req = connect.prepareStatement(sql);
		ResultSet res = req.executeQuery();

		while (res.next()) {
			list.add(new Revue(res.getInt(1), res.getString(2), res.getString(3), res.getDouble(4), res.getString(5),
					res.getInt(6)));
		}

		if (res != null)
			res.close();
		if (req != null)
			req.close();
		if (connect != null)
			connect.close();

		return list;
	}

}

package DAO;

import Metier.Periodicite;

import java.util.ArrayList;

public interface PeriodiciteDAO extends DAO<Periodicite> {
    public ArrayList<Periodicite> getByLibelle(String libelle);
}

package dao;

/**
 * permet d'alterner entre MySQL et la ListeMémoire
 * peut être étoffée en ajoutant d'autres solutions de persistance
 */
public enum Persistance {
    MYSQL, ListeMemoire
}

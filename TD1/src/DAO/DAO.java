package DAO;

import java.sql.SQLException;

public interface DAO<T> {
    public abstract T getById(int id) throws SQLException;

    public abstract boolean create(T object) throws SQLException;

    public abstract boolean update(T object) throws SQLException;

    public abstract boolean delete(T object) throws SQLException;
}

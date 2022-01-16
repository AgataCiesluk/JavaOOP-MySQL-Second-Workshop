package pl.coderslab.entity;

import java.sql.SQLException;

public class DaoException extends RuntimeException {
    public DaoException(String message, SQLException ex){
        super(message,ex);
    }
}

package mvc.dao.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import mvc.dao.DAOFactory;

public class SqliteDAOFactory extends DAOFactory {
	private static final Logger log = Logger.getLogger(SqliteDAOFactory.class.getName());
    private static final String URL = "jdbc:sqlite:sqlite.db";
    
    public static Connection getConnection() {
    	Connection connection = null;
    	
    	try {
    		connection = DriverManager.getConnection(URL);
    	}
    	catch(SQLException ex) {
    		log.warning(ex.getMessage());
    		log.warning(ex.getSQLState());
    	}
    	
    	return connection;
    }
}

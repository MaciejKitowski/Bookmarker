package mvc.dao.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import mvc.dao.DAOFactory;
import mvc.dao.sqlite.SqliteDAOFactory;

public final class PostgresDAOFactory extends DAOFactory {
	private static final Logger log = Logger.getLogger(SqliteDAOFactory.class.getName());
	
	private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost/bookmarker";
    private static final String USERNAME = "bookmarker";
    private static final String USERPASSWORD = "admin1";
    
    public static Connection getConnection() {
    	log.info("Get connection");
    	Connection connection = null;
    	
    	try {
    		Class.forName(DRIVER);
    		connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
    	}
    	catch(SQLException ex) {
    		log.warning(ex.getMessage());
    	} catch (ClassNotFoundException ex) {
			log.warning(ex.getMessage());
		}
    	
    	return connection;
    }
}

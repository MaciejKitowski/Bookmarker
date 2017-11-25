package mvc.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import mvc.dao.sqlite.SqliteDAOFactory;

public class MysqlDAOFactory {
	private static final Logger log = Logger.getLogger(SqliteDAOFactory.class.getName());
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/bookmarker";
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
    		log.warning(ex.getSQLState());
    	} catch (ClassNotFoundException ex) {
			log.warning(ex.getMessage());
		}
    	
    	return connection;
    }
}

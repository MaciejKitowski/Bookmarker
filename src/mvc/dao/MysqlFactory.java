package mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public final class MysqlFactory extends DAOFactory {
	private static final Logger log = Logger.getLogger(MysqlFactory.class.getName());
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/bookmarker?autoReconnect=true&useSSL=false";
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

	@Override
	public String getName() {
		return "MYSQL";
	}
}

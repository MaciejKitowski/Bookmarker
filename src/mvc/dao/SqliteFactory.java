package mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import mvc.dao.Category.CategoryDAO;
import mvc.dao.Category.ICategoryDAO;
import mvc.dao.MainCategory.IMainCategoryDAO;
import mvc.dao.MainCategory.MainCategoryDAO;

public final class SqliteFactory extends DAOFactory {
	private static final Logger log = Logger.getLogger(SqliteFactory.class.getName());
	
	private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:sqlite.db";
    
    @Deprecated
    public static Connection getConnection() {
    	log.info("Get connection");
    	Connection connection = null;
    	
    	try {
    		Class.forName(DRIVER);
    		connection = DriverManager.getConnection(URL);
    	}
    	catch(SQLException ex) {
    		log.warning(ex.getMessage());
    	} catch (ClassNotFoundException ex) {
			log.warning(ex.getMessage());
		}
    	
    	return connection;
    }
    
    @Override
	public IMainCategoryDAO getMainCategory() {
		return new MainCategoryDAO(SQLITE);
	}
    
    @Override
	public ICategoryDAO getCategory() {
		return new CategoryDAO(SQLITE);
	}

	@Override
	public String getName() {
		return "SQLITE";
	}

	@Override
	public Connection createConnection() {
		return SqliteFactory.getConnection();
	}
}

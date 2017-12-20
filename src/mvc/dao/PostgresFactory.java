package mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import mvc.dao.model.CategoryDAO;
import mvc.dao.model.ICategoryDAO;
import mvc.dao.model.IMainCategoryDAO;
import mvc.dao.model.MainCategoryDAO;

public final class PostgresFactory extends DAOFactory {
	private static final Logger log = Logger.getLogger(PostgresFactory.class.getName());
	
	private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost/bookmarker";
    private static final String USERNAME = "bookmarker";
    private static final String USERPASSWORD = "admin1";
        
    @Override
	public IMainCategoryDAO getMainCategory() {
		return new MainCategoryDAO(POSTGRES);
	}
    
    @Override
	public ICategoryDAO getCategory() {
		return new CategoryDAO(POSTGRES);
	}

	@Override
	public String getName() {
		return "POSTGRES";
	}

	@Override
	public Connection getConnection() {
		log.info("Get connection");
		
		Connection connection = null;
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
		
		return connection;
	}
}

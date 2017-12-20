package mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.model.CategoryDAO;
import mvc.dao.model.ICategoryDAO;
import mvc.dao.model.IMainCategoryDAO;
import mvc.dao.model.MainCategoryDAO;

public final class PostgresFactory extends DAOFactory {
	private static final Logger log = LoggerFactory.getLogger(PostgresFactory.class);
	
	private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost/bookmarker";
    private static final String USERNAME = "bookmarker";
    private static final String USERPASSWORD = "admin1";
        
    @Override
	public IMainCategoryDAO getMainCategory() {
    	log.trace("Get MainCategoryDAO");
		return new MainCategoryDAO(POSTGRES);
	}
    
    @Override
	public ICategoryDAO getCategory() {
    	log.trace("Get CategoryDAO");
		return new CategoryDAO(POSTGRES);
	}

	@Override
	public String getName() {
		log.trace("Get name");
		return "POSTGRES";
	}

	@Override
	public Connection getConnection() {
		log.debug("Get connection");
		
		Connection connection = null;
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, USERPASSWORD);
		}
		catch(Exception ex) {
			log.error("Get connection error", ex);
		}
		
		return connection;
	}
}

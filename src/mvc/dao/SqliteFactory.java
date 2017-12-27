package mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.model.SubcategoryDAO;
import mvc.dao.model.ISubcategoryDAO;
import mvc.dao.model.IMainCategoryDAO;
import mvc.dao.model.IUrlDAO;
import mvc.dao.model.MainCategoryDAO;
import mvc.dao.model.UrlDAO;

public final class SqliteFactory extends DAOFactory {
	private static final Logger log = LoggerFactory.getLogger(SqliteFactory.class);
	
	private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:sqlite.db";
        
    @Override
	public IMainCategoryDAO getMainCategory() {
    	log.trace("Get MainCategoryDAO");
		return new MainCategoryDAO(SQLITE);
	}
    
    @Override
	public ISubcategoryDAO getCategory() {
    	log.trace("Get SubcategoryDAO");
		return new SubcategoryDAO(SQLITE);
	}
    
    @Override
	public IUrlDAO getUrl() {
    	log.trace("Get UrlDAO");
		return new UrlDAO(SQLITE);
	}

	@Override
	public String getName() {
		log.trace("Get name");
		return "SQLITE";
	}

	@Override
	public Connection getConnection() {
		log.debug("Get connection");
		
		Connection connection = null;
		
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL);
		}
		catch(Exception ex) {
			log.error("Get connection error", ex);
		}
		
		return connection;
	}
}

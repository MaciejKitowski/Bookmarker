package mvc.dao;

import java.sql.Connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.model.ICategoryDAO;
import mvc.dao.model.IMainCategoryDAO;
import mvc.dao.model.IUrlDAO;

public abstract class DAOFactory {
	private static final Logger log = LoggerFactory.getLogger(DAOFactory.class);
	
	public static final int SQLITE = 1;
	public static final int MYSQL = 2;
	public static final int POSTGRES = 3;
	
	public abstract IMainCategoryDAO getMainCategory();
	public abstract ICategoryDAO getCategory();
	public abstract IUrlDAO getUrl();
	
	public abstract String getName();
	public abstract Connection getConnection();

	public static DAOFactory get(int database) {
		if(database == SQLITE) {
			log.debug("Get SQLite database factory");
			return new SqliteFactory();
		}
		else if(database == MYSQL) {
			log.debug("Get MySql database factory");
			return new MysqlFactory();
		}
		else if(database == POSTGRES) {
			log.debug("Get PostgreSql database factory");
			return new PostgresFactory();
		}
		else {
			log.warn("Wrong database selection");
			return null;
		}
	}
}

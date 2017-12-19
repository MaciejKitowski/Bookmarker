package mvc.dao;

import java.sql.Connection;
import java.util.logging.Logger;

import mvc.dao.MainCategory.IMainCategoryDAO;

public abstract class DAOFactory {
	private static final Logger log = Logger.getLogger(DAOFactory.class.getName());
	
	public static final int SQLITE = 1;
	public static final int MYSQL = 2;
	public static final int POSTGRES = 3;
	
	abstract IMainCategoryDAO getMainCategory();
	
	abstract public String getName();
	abstract public Connection createConnection(); //TODO Replace with static class getConnection()

	public static DAOFactory get(int database) {
		if(database == SQLITE) {
			log.info("Get SQLite database factory");
			return new SqliteFactory();
		}
		else if(database == MYSQL) {
			log.info("Get SQLite database factory");
			return new MysqlFactory();
		}
		else if(database == POSTGRES) {
			log.info("Get PostgreSQL database factory");
			return new PostgresFactory();
		}
		else {
			log.warning("Wrong database selection");
			return null;
		}
	}
}

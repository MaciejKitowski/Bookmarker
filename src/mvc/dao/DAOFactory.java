package mvc.dao;

import java.util.logging.Logger;

import mvc.dao.sqlite.SqliteDAOFactory;

public abstract class DAOFactory {
	private static final Logger log = Logger.getLogger(DAOFactory.class.getName());
	
	public static final int SQLITE = 1;

	public static DAOFactory get(int database) {
		if(database == SQLITE) {
			log.info("Get SQLite database factory");
			return new SqliteDAOFactory();
		}
		else {
			log.warning("Wrong database selection");
			return null;
		}
	}
}
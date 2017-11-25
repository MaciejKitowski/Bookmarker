package mvc.dao.mysql;

import java.util.logging.Logger;

import mvc.dao.sqlite.SqliteDAOFactory;

public class MysqlDAOFactory {
	private static final Logger log = Logger.getLogger(SqliteDAOFactory.class.getName());
	
	private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:sqlite.db";
    private static final String USERNAME = "bookmarker";
    private static final String USERPASSWORD = "admin1";
}

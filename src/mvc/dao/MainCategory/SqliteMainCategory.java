package mvc.dao.MainCategory;

import java.util.logging.Logger;

public final class SqliteMainCategory {
	private static final Logger log = Logger.getLogger(SqliteMainCategory.class.getName());
	
	private static final String CREATE_TABLE =
		"CREATE TABLE MainCategory( \n" +
				"ID 	INTEGER 	PRIMARY KEY AUTOINCREMENT, \n" +
				"name 	VARCHAR(255) 	NOT NULL \n" + 
		")";
	
	private static final String INSERT = "INSERT INTO MainCategory(name) VALUES(?)";
	
	private static final String GET = "SELECT ID, name FROM MainCategory WHERE id = ?";
	
	private static final String GET_ALL = "SELECT ID, name FROM MainCategory";
	
	private static final String UPDATE = "UPDATE MainCategory SET name=? WHERE id = ?";
	
	private static final String DELETE = "DELETE FROM MainCategory WHERE id = ?";
}

package mvc.dao.Category;

import java.util.logging.Logger;

public final class SqliteCategory {
	private static final Logger log = Logger.getLogger(SqliteCategory.class.getName());
	
	private static final String CREATE_TABLE =
		"CREATE TABLE Category( \n" +
				"ID 	INTEGER 	PRIMARY KEY AUTOINCREMENT, \n" +
				"parent_ID 	INTEGER, \n" +
				"name 	VARCHAR(255) 	NOT NULL, \n" + 
				"FOREIGN KEY(parent_ID) REFERENCES MainCategory(ID) \n" +
					"ON UPDATE CASCADE \n" +
					"ON DELETE SET NULL \n" +
		")";
	
	private static final String INSERT = "INSERT INTO Category(name, parent_ID) VALUES(?, ?)";
	
	private static final String GET = "SELECT ID, name, parent_ID FROM Category WHERE ID = ?";
	
	private static final String GET_PARENT = "SELECT ID, name, parent_ID FROM Category WHERE parent_ID = ?";
	
	private static final String GET_ALL = "SELECT ID, name, parent_ID name FROM Category";
	
	private static final String UPDATE = "UPDATE Category SET name=?, parent_ID=? WHERE id = ?";
	
	private static final String DELETE = "DELETE FROM Category WHERE id = ?";
}

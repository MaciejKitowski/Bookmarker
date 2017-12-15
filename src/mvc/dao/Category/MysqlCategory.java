package mvc.dao.Category;

import java.util.logging.Logger;

public final class MysqlCategory {
	private static final Logger log = Logger.getLogger(MysqlCategory.class.getName());
	
	public static int INSERT_FAIL = -1;
	
	private static final String CREATE_TABLE = 
		"CREATE TABLE MainCategory( \n" +
				"ID 	int 	NOT NULL	AUTO_INCREMENT, \n" +
				"parent_ID 	int, \n" +
				"name 	VARCHAR(255) 	NOT NULL \n," +
				"PRIMARY KEY(ID), \n" +
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

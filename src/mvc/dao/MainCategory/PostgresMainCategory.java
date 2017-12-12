package mvc.dao.MainCategory;

import java.util.logging.Logger;

public final class PostgresMainCategory {
	private static final Logger log = Logger.getLogger(PostgresMainCategory.class.getName());
	
	private static final String CREATE_TABLE = 
		"CREATE TABLE profile( \n" +
				"ID 	SERIAL 	PRIMARY KEY," +
				"name 	VARCHAR(255) 	NOT NULL \n" +
		")";
	
	private static final String INSERT = "INSERT INTO profile(name) VALUES(?)";
	
	private static final String GET = "SELECT ID, name FROM profile WHERE id = ?";
	
	private static final String GET_ALL = "SELECT ID, name FROM profile";
	
	private static final String UPDATE = "UPDATE profile SET name=? WHERE id = ?";
	
	private static final String DELETE = "DELETE FROM profile WHERE id = ?";
}

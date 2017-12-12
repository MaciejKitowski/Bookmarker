package mvc.dao.MainCategory;

import java.util.logging.Logger;

import mvc.dao.mysql.MysqlProfile;

public final class MysqlMainCategory {
	private static final Logger log = Logger.getLogger(MysqlMainCategory.class.getName());
	
	private static final String CREATE_TABLE = 
		"CREATE TABLE MainCategory( \n" +
				"ID 	int 	NOT NULL	AUTO_INCREMENT, \n" +
				"name 	VARCHAR(255) 	NOT NULL \n," +
				"PRIMARY KEY(ID)" +
		")";
		
		private static final String INSERT = "INSERT INTO MainCategory(name) VALUES(?)";
		
		private static final String GET = "SELECT ID, name FROM MainCategory WHERE id = ?";
		
		private static final String GET_ALL = "SELECT ID, name FROM MainCategory";
		
		private static final String UPDATE = "UPDATE MainCategory SET name=? WHERE id = ?";
		
		private static final String DELETE = "DELETE FROM MainCategory WHERE id = ?";
}
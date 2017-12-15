package mvc.dao.Url;

import java.sql.Connection;
import java.sql.Statement;
import java.util.logging.Logger;

import mvc.dao.MysqlFactory;
import mvc.dao.SqliteFactory;

public final class MysqlUrl {
	private static final Logger log = Logger.getLogger(MysqlUrl.class.getName());
	
	public static int INSERT_FAIL = -1;
		
	private static final String CREATE_TABLE =
		"CREATE TABLE Url( \n" +
				"ID 	int 	NOT NULL	AUTO_INCREMENT, \n" +
				"cat_ID 	int, \n" +
				"title 	VARCHAR(255) 	NOT NULL, \n" +
				"url 	VARCHAR(255) 	NOT NULL, \n" +
				"description 	VARCHAR(255), \n" +
				"PRIMARY KEY(ID), \n" +
				"FOREIGN KEY(cat_ID) REFERENCES Category(ID) \n" +
					"ON UPDATE CASCADE \n" +
					"ON DELETE SET NULL \n" +
		")";
	
	private static final String INSERT = "INSERT INTO Url(title, url, description, cat_ID) VALUES(?, ?, ?, ?)";
	
	private static final String GET = "SELECT ID, title, url, description, cat_ID FROM Url WHERE ID = ?";
	
	private static final String GET_CATEGORY = "SELECT ID, title, url, description, cat_ID FROM Url WHERE cat_ID = ?";
	
	private static final String GET_ALL = "SELECT ID, title, url, description, cat_ID FROM Url";
	
	private static final String UPDATE = "UPDATE Url SET title=?, url=?, description=?, cat_ID=? WHERE ID = ?";
	
	private static final String DELETE = "DELETE FROM Url WHERE id = ?";
	
	@Override
	public void createTable() {
		log.info("Create new table");
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = MysqlFactory.getConnection();
			statement = connection.createStatement();
			
			statement.execute(CREATE_TABLE);
			
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
	}
}

package mvc.dao.Url;

import java.util.logging.Logger;

import mvc.dao.Category.SqliteCategory;

public final class SqliteUrl {
	private static final Logger log = Logger.getLogger(SqliteUrl.class.getName());
	
	public static int INSERT_FAIL = -1;
	
	private static final String CREATE_TABLE =
		"CREATE TABLE Url( \n" +
				"ID 	INTEGER 	PRIMARY KEY AUTOINCREMENT, \n" +
				"cat_ID 	INTEGER, \n" +
				"title 	VARCHAR(255) 	NOT NULL, \n" +
				"url 	VARCHAR(255) 	NOT NULL, \n" +
				"description 	VARCHAR(255), \n" +
				"FOREIGN KEY(cat_ID) REFERENCES Category(ID) \n" +
					"ON UPDATE CASCADE \n" +
					"ON DELETE SET NULL \n" +
		")";
	
	private static final String INSERT = "INSERT INTO Url(title, url, description, cat_ID) VALUES(?, ?, ?, ?)";
	
	private static final String GET = "SELECT ID, title, url, description, cat_ID FROM Url WHERE ID = ?";
	
	private static final String GET_CATEGORY = "SELECT ID, title, url, description, cat_ID FROM Url WHERE cat_ID = ?";
	
	private static final String UPDATE = "UPDATE Url SET title=?, url=?, title=?, description=?, cat_ID=? WHERE ID = ?";
	
	private static final String DELETE = "DELETE FROM Url WHERE id = ?";
}

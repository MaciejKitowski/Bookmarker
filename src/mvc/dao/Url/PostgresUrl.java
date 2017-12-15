package mvc.dao.Url;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import mvc.dao.PostgresFactory;
import mvc.dao.SqliteFactory;
import mvc.model.Url;

public final class PostgresUrl implements IUrlDAO {
	private static final Logger log = Logger.getLogger(PostgresUrl.class.getName());
	
	public static int INSERT_FAIL = -1;
		
	private static final String CREATE_TABLE =
		"CREATE TABLE Url( \n" +
				"ID 	SERIAL 	PRIMARY KEY, \n" +
				"cat_ID 	int, \n" +
				"title 	VARCHAR(255) 	NOT NULL, \n" +
				"url 	VARCHAR(255) 	NOT NULL, \n" +
				"description 	VARCHAR(255), \n" +
				"CONSTRAINT mCat_fk FOREIGN KEY(cat_ID) REFERENCES Category(ID) \n" +
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
			connection = PostgresFactory.getConnection();
			statement = connection.createStatement();
			
			statement.execute(CREATE_TABLE);
			
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
	}
	
	@Override
	public int insert(Url url) {
		log.info(String.format("Insert url: ID=%d, title=%s, url=%s", url.getID(), url.getTitle(), url.getTitle()));
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		int resultBuffer = 0;
			
		try {
			connection = PostgresFactory.getConnection();
			statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, url.getTitle());
			statement.setString(2, url.getUrl());
			statement.setString(3, url.getDescription());
			statement.setInt(4, url.getCategory().getID());
			statement.execute();
			
			result = statement.getGeneratedKeys();
			if(result != null && result.next()) resultBuffer = result.getInt(1);
			else resultBuffer = INSERT_FAIL;
			
			result.close();
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
			resultBuffer = INSERT_FAIL;
		}

		return resultBuffer;
	}
}

package mvc.dao.Url;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import mvc.dao.SqliteFactory;
import mvc.dao.Category.SqliteCategory;
import mvc.model.Category;
import mvc.model.Url;

public final class SqliteUrl implements IUrlDAO {
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

	@Override
	public void createTable() {
		log.info("Create new table");
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = SqliteFactory.getConnection();
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
		
		return 0;
	}

	@Override
	public Url get(int ID) {
		log.info(String.format("Get url: ID=%d", ID));
		
		return null;
	}

	@Override
	public List<Url> getAllParent(Category category) {
		log.info(String.format("Get all urls with category: ID=%d, name=%s", category.getID(), category.getName()));
		
		return null;
	}

	@Override
	public List<Url> getAll() {
		log.info("Get all urls");
		
		return null;
	}

	@Override
	public boolean update(Url url) {
		log.info(String.format("Update url: ID=%d, url=%s", url.getID(), url.getUrl()));
		
		return false;
	}

	@Override
	public boolean delete(int ID) {
		log.info(String.format("Delete url: ID=%d", ID));
		
		return false;
	}
}

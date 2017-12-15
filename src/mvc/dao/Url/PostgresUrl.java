package mvc.dao.Url;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import mvc.dao.PostgresFactory;
import mvc.dao.Category.ICategoryDAO;
import mvc.dao.Category.SqliteCategory;
import mvc.model.Category;
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
	
	@Override
	public Url get(int ID) {
		log.info(String.format("Get url: ID=%d", ID));
		
		Url url = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			connection = PostgresFactory.getConnection();
			statement = connection.prepareStatement(GET);
			
			statement.setInt(1, ID);
			statement.execute();
			
			result = statement.getResultSet();
			
			if(result != null && result.next()) {
				int foundID = result.getInt(1);
				String foundTitle = result.getString(2);
				String foundUrl = result.getString(3);
				String foundDescription = result.getString(4);
				int foundCatID = result.getInt(5);
				
				ICategoryDAO category = new SqliteCategory();
				url = new Url(foundID, foundUrl, foundTitle, foundDescription, category.get(foundCatID));
			}
			
			result.close();
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
		
		return url;
	}
	
	@Override
	public List<Url> getAllParent(Category category) {
		log.info(String.format("Get all urls with category: ID=%d, name=%s", category.getID(), category.getName()));
		
		List<Url> urls = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			connection = PostgresFactory.getConnection();
			statement = connection.prepareStatement(GET_CATEGORY);
			
			statement.setInt(1, category.getID());
			
			result = statement.executeQuery();
			if(result != null) {
				while(result.next()) {
					int foundID = result.getInt(1);
					String foundTitle = result.getString(2);
					String foundUrl = result.getString(3);
					String foundDescription = result.getString(4);
					
					urls.add(new Url(foundID, foundUrl, foundTitle, foundDescription, category));
				}
			}
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
		
		return urls;
	}
	
	@Override
	public List<Url> getAll() {
		log.info("Get all urls");
		
		List<Url> urls = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			connection = PostgresFactory.getConnection();
			statement = connection.createStatement();
			
			result = statement.executeQuery(GET_ALL);
			if(result != null) {
				while(result.next()) {
					int foundID = result.getInt(1);
					String foundTitle = result.getString(2);
					String foundUrl = result.getString(3);
					String foundDescription = result.getString(4);
					int foundCatID = result.getInt(5);
					
					ICategoryDAO category = new SqliteCategory();
					urls.add(new Url(foundID, foundUrl, foundTitle, foundDescription, category.get(foundCatID)));
				}
			}
			
			result.close();
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
		
		return urls;
	}
	
	@Override
	public boolean update(Url url) {
		log.info(String.format("Update url: ID=%d, url=%s", url.getID(), url.getUrl()));
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = PostgresFactory.getConnection();
			statement = connection.prepareStatement(UPDATE);
			
			statement.setString(1, url.getTitle());
			statement.setString(2, url.getUrl());
			statement.setString(3, url.getDescription());
			statement.setInt(4, url.getCategory().getID());
			statement.setInt(5, url.getID());
			statement.execute();
			
			statement.close();
			connection.close();
			
			return true;
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
			return false;
		}
	}
	
	@Override
	public boolean delete(int ID) {
		log.info(String.format("Delete url: ID=%d", ID));
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = PostgresFactory.getConnection();
			statement = connection.prepareStatement(DELETE);
			
			statement.setInt(1, ID);
			statement.execute();
			
			statement.close();
			connection.close();
			
			return true;
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
			return false;
		}
	}
}

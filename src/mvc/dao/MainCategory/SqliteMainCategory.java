package mvc.dao.MainCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import mvc.dao.SqliteFactory;
import mvc.model.MainCategory;

@Deprecated
public final class SqliteMainCategory implements IMainCategoryDAO {
	private static final Logger log = Logger.getLogger(SqliteMainCategory.class.getName());
	
	public static int INSERT_FAIL = -1;
	
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
	public int insert(MainCategory category) {
		log.info(String.format("Insert category: ID=%d, name=%s", category.getID(), category.getName()));
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		int resultBuffer = 0;
		
		try {
			connection = SqliteFactory.getConnection();
			statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, category.getName());
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
	public MainCategory get(int ID) {
		log.info(String.format("Get category: ID=%d", ID));
		
		MainCategory category = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			connection = SqliteFactory.getConnection();
			statement = connection.prepareStatement(GET);
			
			statement.setInt(1, ID);
			statement.execute();
			
			result = statement.getResultSet();
			if(result != null && result.next()) {
				int foundID = result.getInt(1);
				String foundName = result.getString(2);
				
				category = new MainCategory(foundID, foundName);
			}
			
			result.close();
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}

		return category;
	}

	@Override
	public List<MainCategory> getAll() {
		log.info("Get all categories");
		
		List<MainCategory> categories = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			connection = SqliteFactory.getConnection();
			statement = connection.createStatement();
			
			result = statement.executeQuery(GET_ALL);
			if(result != null) {
				while(result.next()) {
					int foundID = result.getInt(1);
					String foundName = result.getString(2);
					
					categories.add(new MainCategory(foundID, foundName));
				}
			}
			
			result.close();
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
		
		return categories;
	}

	@Override
	public boolean update(MainCategory category) {
		log.info(String.format("Update category: ID=%d, name=%s", category.getID(), category.getName()));
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = SqliteFactory.getConnection();
			statement = connection.prepareStatement(UPDATE);
			
			statement.setString(1, category.getName());
			statement.setInt(2, category.getID());
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
		log.info(String.format("Delete category: ID=%d", ID));
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = SqliteFactory.getConnection();
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

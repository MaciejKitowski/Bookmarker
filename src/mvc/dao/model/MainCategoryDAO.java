package mvc.dao.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.json.JSONObject;

import mvc.dao.DAOFactory;
import mvc.model.MainCategory;

public final class MainCategoryDAO implements IMainCategoryDAO {
	private static final Logger log = Logger.getLogger(MainCategoryDAO.class.getName());
	private static final String queryFilename = "MainCategory.json";
		
	private DAOFactory database = null;
	private String CREATE_TABLE = null;
	private String INSERT = null;
	private String GET = null;
	private String GET_ALL = null;
	private String UPDATE = null;
	private String DELETE = null;
	
	public MainCategoryDAO(int databaseType) {
		database = DAOFactory.get(databaseType);
		
		try {
			JSONObject obj = JsonLoader.getJson(queryFilename, database.getName());
			
			CREATE_TABLE = JsonLoader.joinStringArray(obj.getJSONArray("CREATE_TABLE"));
			INSERT = obj.getString("INSERT");
			GET = obj.getString("GET");
			GET_ALL = obj.getString("GET_ALL");
			UPDATE = obj.getString("UPDATE");
			DELETE = obj.getString("DELETE");
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
	}
	
	@Override
	public void createTable() {
		log.info("Create new table");
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			
			statement.execute(CREATE_TABLE);
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
		finally {
			try {
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.warning(ex.getMessage());
			}
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
			connection = database.getConnection();
			statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, category.getName());
			statement.execute();
			
			result = statement.getGeneratedKeys();
			if(result != null && result.next()) resultBuffer = result.getInt(1);
			else resultBuffer = INSERT_FAIL;
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
			resultBuffer = INSERT_FAIL;
		}
		finally {
			try {
				if(result != null && !result.isClosed()) result.close();
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.warning(ex.getMessage());
			}
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
			connection = database.getConnection();
			statement = connection.prepareStatement(GET);
			
			statement.setInt(1, ID);
			statement.execute();
			
			result = statement.getResultSet();
			if(result != null && result.next()) {
				int foundID = result.getInt(1);
				String foundName = result.getString(2);
				
				category = new MainCategory(foundID, foundName);
			}
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
		finally {
			try {
				if(result != null && !result.isClosed()) result.close();
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.warning(ex.getMessage());
			}
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
			connection = database.getConnection();
			statement = connection.createStatement();
			
			result = statement.executeQuery(GET_ALL);
			if(result != null) {
				while(result.next()) {
					int foundID = result.getInt(1);
					String foundName = result.getString(2);
					
					categories.add(new MainCategory(foundID, foundName));
				}
			}
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
		finally {
			try {
				if(result != null && !result.isClosed()) result.close();
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.warning(ex.getMessage());
			}
		}
		
		return categories;
	}
	
	@Override
	public boolean update(MainCategory category) {
		log.info(String.format("Update category: ID=%d, name=%s", category.getID(), category.getName()));
		
		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;
		
		try {
			connection = database.getConnection();
			statement = connection.prepareStatement(UPDATE);
			
			statement.setString(1, category.getName());
			statement.setInt(2, category.getID());
			statement.execute();
			
			result = true;
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
			result = false;
		}
		finally {
			try {
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.warning(ex.getMessage());
			}
		}
		
		return result;
	}
	
	@Override
	public boolean delete(int ID) {
		log.info(String.format("Delete category: ID=%d", ID));
		
		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;
		
		try {
			connection = database.getConnection();
			statement = connection.prepareStatement(DELETE);
			
			statement.setInt(1, ID);
			statement.execute();
			
			result = true;
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
			result = false;
		}
		finally {
			try {
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.warning(ex.getMessage());
			}
		}
		
		return result;
	}
}

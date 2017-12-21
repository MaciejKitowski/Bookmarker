package mvc.dao.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.DAOFactory;
import mvc.model.MainCategory;

public final class MainCategoryDAO implements IMainCategoryDAO {
	private static final Logger log = LoggerFactory.getLogger(MainCategoryDAO.class);
	
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
		log.debug("Create MainCategoryDAO with database: {}", database.getName());
		
		try {
			JSONObject obj = JsonLoader.getJson(queryFilename, database.getName());
			
			CREATE_TABLE = JsonLoader.joinStringArray(obj.getJSONArray("CREATE_TABLE"));
			INSERT = obj.getString("INSERT");
			GET = obj.getString("GET");
			GET_ALL = obj.getString("GET_ALL");
			UPDATE = obj.getString("UPDATE");
			DELETE = obj.getString("DELETE");
			
			log.debug("CREATE_TABLE: {}", CREATE_TABLE);
			log.debug("INSERT: {}", INSERT);
			log.debug("GET: {}", GET);
			log.debug("GET_ALL: {}", GET_ALL);
			log.debug("UPDATE: {}", UPDATE);
			log.debug("DELETE: {}", DELETE);
		}
		catch(Exception ex) {
			log.error("Load JSON file failed", ex);
		}
	}
	
	@Override
	public void createTable() {
		log.debug("Create table");
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = database.getConnection();
			statement = connection.createStatement();
			
			statement.execute(CREATE_TABLE);
		}
		catch(Exception ex) {
			log.error("Create new table failed", ex);
		}
		finally {
			try {
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.error("Close connection failed", ex);
			}
		}
	}
	
	@Override
	public int insert(MainCategory category) {
		log.debug("Insert category: ID={} name={}", category.getID(), category.getName());
		
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
			log.error("Insert failed", ex);
			resultBuffer = INSERT_FAIL;
		}
		finally {
			try {
				if(result != null && !result.isClosed()) result.close();
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.error("Close connection failed", ex);
			}
		}

		return resultBuffer;
	}
	
	@Override
	public MainCategory get(int ID) {
		log.debug("Get category: ID={}", ID);
		
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
			log.error("Get failed", ex);
		}
		finally {
			try {
				if(result != null && !result.isClosed()) result.close();
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.error("Close connection failed", ex);
			}
		}

		return category;
	}
	
	@Override
	public List<MainCategory> getAll() {
		log.debug("Get all categories");
		
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
			log.error("Get all categories failed", ex);
		}
		finally {
			try {
				if(result != null && !result.isClosed()) result.close();
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.error("Close connection failed", ex);
			}
		}
		
		return categories;
	}
	
	@Override
	public boolean update(MainCategory category) {
		log.debug("Update category: ID={} name={}", category.getID(), category.getName());
		
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
			log.error("Update failed", ex);
			result = false;
		}
		finally {
			try {
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.error("Close connection failed", ex);
			}
		}
		
		return result;
	}
	
	@Override
	public boolean delete(int ID) {
		log.debug("Delete category: ID={}", ID);
		
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
			log.error("Delete failed", ex);
			result = false;
		}
		finally {
			try {
				if(statement != null && !statement.isClosed()) statement.close();
				if(connection != null && !connection.isClosed()) connection.close();
			}
			catch(Exception ex) {
				log.error("Close connection failed", ex);
			}
		}
		
		return result;
	}
}

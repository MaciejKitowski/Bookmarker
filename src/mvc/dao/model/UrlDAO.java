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
import mvc.model.Category;
import mvc.model.Url;

public final class UrlDAO implements IUrlDAO {
	private static final Logger log = Logger.getLogger(UrlDAO.class.getName());
	private static final String queryFilename = "Url.json";
	
	private DAOFactory database = null;
	private String CREATE_TABLE = null;
	private String INSERT = null;
	private String GET = null;
	private String GET_CATEGORY = null;
	private String GET_ALL = null;
	private String UPDATE = null;
	private String DELETE = null;
	
	public UrlDAO(int databaseType) {
		database = DAOFactory.get(databaseType);
		
		try {
			JSONObject obj = JsonLoader.getJson(queryFilename, database.getName());
			
			CREATE_TABLE = JsonLoader.joinStringArray(obj.getJSONArray("CREATE_TABLE"));
			INSERT = obj.getString("INSERT");
			GET = obj.getString("GET");
			GET_ALL = obj.getString("GET_ALL");
			GET_CATEGORY = obj.getString("GET_CATEGORY");
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
			log.info(ex.getMessage());
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
	public int insert(Url url) {
		log.info(String.format("Insert url: ID=%d, title=%s, url=%s", url.getID(), url.getTitle(), url.getTitle()));
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		int resultBuffer = 0;
			
		try {
			connection = database.getConnection();
			statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, url.getTitle());
			statement.setString(2, url.getUrl());
			statement.setString(3, url.getDescription());
			statement.setInt(4, url.getCategory().getID());
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
	public Url get(int ID) {
		log.info(String.format("Get url: ID=%d", ID));
		
		Url url = null;
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
				String foundTitle = result.getString(2);
				String foundUrl = result.getString(3);
				String foundDescription = result.getString(4);
				int foundCatID = result.getInt(5);
				
				ICategoryDAO category = database.getCategory();
				url = new Url(foundID, foundUrl, foundTitle, foundDescription, category.get(foundCatID));
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
		
		return url;
	}

	@Override
	public List<Url> getAllWithCategory(Category category) {
		log.info(String.format("Get all urls with category: ID=%d, name=%s", category.getID(), category.getName()));
		
		List<Url> urls = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			connection = database.getConnection();
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
			connection = database.getConnection();
			statement = connection.createStatement();
			
			result = statement.executeQuery(GET_ALL);
			if(result != null) {
				while(result.next()) {
					int foundID = result.getInt(1);
					String foundTitle = result.getString(2);
					String foundUrl = result.getString(3);
					String foundDescription = result.getString(4);
					int foundCatID = result.getInt(5);
					
					ICategoryDAO category = database.getCategory();
					urls.add(new Url(foundID, foundUrl, foundTitle, foundDescription, category.get(foundCatID)));
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
		
		return urls;
	}

	@Override
	public boolean update(Url url) {
		log.info(String.format("Update url: ID=%d, url=%s", url.getID(), url.getUrl()));
		
		Connection connection = null;
		PreparedStatement statement = null;
		boolean result = false;
		
		try {
			connection = database.getConnection();
			statement = connection.prepareStatement(UPDATE);
			
			statement.setString(1, url.getTitle());
			statement.setString(2, url.getUrl());
			statement.setString(3, url.getDescription());
			statement.setInt(4, url.getCategory().getID());
			statement.setInt(5, url.getID());
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
		log.info(String.format("Delete url: ID=%d", ID));
		
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

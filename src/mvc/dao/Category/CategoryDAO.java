package mvc.dao.Category;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import mvc.dao.DAOFactory;
import mvc.dao.SqliteFactory;
import mvc.model.Category;
import mvc.model.MainCategory;

public final class CategoryDAO implements ICategoryDAO {
	private static final Logger log = Logger.getLogger(CategoryDAO.class.getName());
	private static final String queryFilename = "Category.json";
	
	public static int INSERT_FAIL = -1;
	
	private DAOFactory database = null;
	private String CREATE_TABLE = null;
	private String INSERT = null;
	private String GET = null;
	private String GET_ALL = null;
	private String GET_MAINCAT = null;
	private String UPDATE = null;
	private String DELETE = null;
	
	public CategoryDAO(int databaseType) {
		database = DAOFactory.get(databaseType);
		
		loadQueriesFromFile();
	}
	
	@Override
	public void createTable() {
		log.info("Create new table");
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = database.createConnection();
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
	public int insert(Category category) {
		log.info(String.format("Insert category: ID=%d, name=%s, parent: ID=%d, name=%s", category.getID(), category.getName(), category.getParent().getID(), category.getParent().getName()));

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		int resultBuffer = 0;
		
		try {
			connection = database.createConnection();
			statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, category.getName());
			statement.setInt(2, category.getParent().getID());
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
	public Category get(int ID) {
		log.info(String.format("Get category: ID=%d", ID));
		
		return null;
	}
	
	@Override
	public List<Category> getAllParent(MainCategory mainCategory) {
		log.info(String.format("Get all categories with parent: ID=%d, name=%s", mainCategory.getID(), mainCategory.getName()));

		return null;
	}
	
	@Override
	public List<Category> getWithMainCategory(MainCategory category) {
		log.info(String.format("Get all categories with main category: ID=%d, name=%s", category.getID(), category.getName()));

		
		return null;
	}
	
	@Override
	public List<Category> getAll() {
		log.info("Get all categories");
		
		return null;
	}
	
	@Override
	public boolean update(Category category) {
		log.info(String.format("Update category: ID=%d, name=%s", category.getID(), category.getName()));

		return false;
	}
	
	@Override
	public boolean delete(int ID) {
		log.info(String.format("Delete category: ID=%d", ID));
		return false;
	}
	
	private void loadQueriesFromFile() {
		log.info("Load SQL queries from: " + queryFilename);
		
		FileInputStream jsonFile = null;
		
		try {
			jsonFile = new FileInputStream(new File(queryFilename));
			String rawJson = IOUtils.toString(jsonFile);
			JSONObject obj = new JSONObject(rawJson).getJSONObject(database.getName());
			
			CREATE_TABLE = getCreateTable(obj.getJSONArray("CREATE_TABLE"));
			INSERT = obj.getString("INSERT");
			GET = obj.getString("GET");
			GET_ALL = obj.getString("GET_ALL");
			GET_MAINCAT = obj.getString("GET_MAINCAT");
			UPDATE = obj.getString("UPDATE");
			DELETE = obj.getString("DELETE");
			
			jsonFile.close();
		}
		catch (Exception ex) {
			log.warning(ex.getMessage());
		}
	}
	
	private String getCreateTable(JSONArray array) {
		String[] raw = array.toList().toArray(new String[array.length()]);
		return String.join("\n", raw);
	}
}

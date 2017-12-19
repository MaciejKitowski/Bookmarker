package mvc.dao.Url;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import mvc.dao.DAOFactory;
import mvc.model.Category;
import mvc.model.Url;

public final class UrlDAO implements IUrlDAO {
	private static final Logger log = Logger.getLogger(UrlDAO.class.getName());
	private static final String queryFilename = "Url.json";
	
	public static int INSERT_FAIL = -1;
	
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
			log.info(ex.getMessage());
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
	public List<Url> getAllWithCategory(Category category) {
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
			GET_CATEGORY = obj.getString("GET_CATEGORY");
			UPDATE = obj.getString("UPDATE");
			DELETE = obj.getString("DELETE");
			
			jsonFile.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
	}
	
	private String getCreateTable(JSONArray array) {
		String[] raw = array.toList().toArray(new String[array.length()]);
		return String.join("\n", raw);
	}
}

package mvc.dao.MainCategory;

import java.util.logging.Logger;

import org.json.JSONObject;

public final class MainCategoryDAO {
	private static final Logger log = Logger.getLogger(MainCategoryDAO.class.getName());
	private static final String queryFilename = "MainCategory.dao";
	
	public static int INSERT_FAIL = -1;
	
	private String CREATE_TABLE = null;
	private String INSERT = null;
	private String GET = null;
	private String GET_ALL = null;
	private String UPDATE = null;
	private String DELETE = null;
	
	private void loadQueriesFromFile(String dbName) {
		log.info("Load SQL queries from: " + queryFilename);
		
		JSONObject parent = new JSONObject(queryFilename);
		
		log.info(parent.toString());
	}
}

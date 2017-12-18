package mvc.dao.MainCategory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import mvc.dao.DAOFactory;

public final class MainCategoryDAO {
	private static final Logger log = Logger.getLogger(MainCategoryDAO.class.getName());
	private static final String queryFilename = "MainCategory.json";
	
	public static int INSERT_FAIL = -1;
	
	private DAOFactory database = null;
	private String CREATE_TABLE = null;
	private String INSERT = null;
	private String GET = null;
	private String GET_ALL = null;
	private String UPDATE = null;
	private String DELETE = null;
	
	public MainCategoryDAO(int databaseType) {
		database = DAOFactory.get(databaseType);
		
		loadQueriesFromFile();
	}
	
	private void loadQueriesFromFile() {
		log.info("Load SQL queries from: " + queryFilename);
		
		try {
			FileInputStream jsonFile = new FileInputStream(new File(queryFilename));
			String rawJson = IOUtils.toString(jsonFile);
			
			JSONObject parent = new JSONObject(rawJson);
			JSONObject target = parent.getJSONObject(database.getName());
			
			JSONArray createTableArray = target.getJSONArray("CREATE_TABLE");
			System.out.println(createTableArray);
			
			CREATE_TABLE = String.join("\n", Arrays.copyOf(createTableArray.toList().toArray(), createTableArray.length(), String[].class));
			
			jsonFile.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
		
		/*JSONObject parent = new JSONObject();
		
		System.out.println("XX");
		System.out.println(parent.toString());*/
	}
}

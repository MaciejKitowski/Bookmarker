package mvc.dao.model;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

class JsonLoader {
	private static final Logger log = Logger.getLogger(JsonLoader.class.getName());
	
	private JsonLoader() {}
	
	public static JSONObject getJson(String filename, String databaseName) throws Exception {
		log.info(String.format("Load JSON from: %s for database: %s", filename, databaseName));
		
		JSONObject obj = null;
		
		FileInputStream file = new FileInputStream(new File(filename));
		String raw = IOUtils.toString(file);
		
		obj = new JSONObject(raw).getJSONObject(databaseName);
		file.close();
		
		return obj;
	}
}

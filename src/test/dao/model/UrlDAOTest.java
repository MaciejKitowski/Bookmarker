package test.dao.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import mvc.dao.DAOFactory;
import mvc.dao.Category.CategoryDAO;
import mvc.dao.Url.UrlDAO;
import test.dao.DAOutils;

@RunWith(Parameterized.class)
public class UrlDAOTest {
	private UrlDAO dao = null;
	private int databaseType;
	
	@Parameters
    public static List<Object> data() {
        return Arrays.asList(new Object[] {     
                 DAOFactory.SQLITE, DAOFactory.MYSQL, DAOFactory.POSTGRES  
           });
    }
	
	public UrlDAOTest(int database) {
		databaseType = database;
	}
	
	@Before
	public void initialize() {
		dao = new UrlDAO(databaseType);
	}
	
	@Test
	public void loadQueriesTest() {
		String[] fieldNames = {"CREATE_TABLE", "INSERT", "GET", "GET_CATEGORY", "GET_ALL", "UPDATE", "DELETE"};
		
		try {
			JSONObject json = DAOutils.getJsonQuery("Url.json", databaseType);
			Class<?> cls = dao.getClass();
			
			for(String name : fieldNames) {
				String sql = null;
				Field field = cls.getDeclaredField(name);
				field.setAccessible(true);
				
				if(name == "CREATE_TABLE") sql = DAOutils.getCreateTable(json.getJSONArray(name));
				else sql = json.getString(name);
				
				assertNotNull(field.get(dao));
				assertEquals(field.get(dao), sql);
			}
		}
		catch(Exception ex) {
			fail(ex.getMessage());
		}
	}
}

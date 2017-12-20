package test.dao.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import mvc.dao.model.CategoryDAO;
import mvc.dao.model.MainCategoryDAO;
import mvc.model.Category;
import mvc.model.MainCategory;

@RunWith(Parameterized.class)
public class CategoryDAOTest {
	private CategoryDAO dao = null;
	private int databaseType;
	
	@Parameters
    public static List<Object> data() {
        return Arrays.asList(new Object[] {     
                 DAOFactory.SQLITE, DAOFactory.MYSQL, DAOFactory.POSTGRES  
           });
    }
	
	public CategoryDAOTest(int database) {
		databaseType = database;
	}
	
	@Before
	public void initialize() {
		dao = new CategoryDAO(databaseType);
	}
	
	@Test
	public void loadQueriesTest() {
		String[] fieldNames = {"CREATE_TABLE", "INSERT", "GET", "GET_MAINCAT", "GET_ALL", "UPDATE", "DELETE"};
		
		try {
			JSONObject json = Utilities.getJsonQuery("Category.json", databaseType);
			Class<?> cls = dao.getClass();
			
			for(String name : fieldNames) {
				String sql = null;
				Field field = cls.getDeclaredField(name);
				field.setAccessible(true);
				
				if(name == "CREATE_TABLE") sql = Utilities.getCreateTable(json.getJSONArray(name));
				else sql = json.getString(name);
				
				assertNotNull(field.get(dao));
				assertEquals(field.get(dao), sql);
			}
		}
		catch(Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void createTableTest() {
		try {
			dao.createTable();
			List<String> tableList = Utilities.getTableNames(databaseType);
			
			assertTrue(tableList.contains("Category") || tableList.contains("category"));
		}
		catch(Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void insertTest() {
		int mainCategoryID = 4;
		MainCategory main = DAOFactory.get(databaseType).getMainCategory().get(mainCategoryID);
		Category category = new Category("SingleInsertTest", main);
		
		int result = dao.insert(category);
		
		assertNotEquals(CategoryDAO.INSERT_FAIL, result);
	}
	
	@Test
	public void insertNullTest() {
		Category category = new Category("SingleInsertNullTest");

		int result = dao.insert(category);
		
		assertNotEquals(CategoryDAO.INSERT_FAIL, result);
	}
	
	@Test
	public void insertMultipleTest() {
		String pattern = "MultipleInsertTest_%d";
		int insertCount = 20;
		int mainCategoryID = 4;
		MainCategory main = DAOFactory.get(databaseType).getMainCategory().get(mainCategoryID);
		
		for(int i = 0; i < insertCount; ++i) {
			Category category = new Category(String.format(pattern, i + 1), main);
			
			int result = dao.insert(category);
			
			assertNotEquals(MainCategoryDAO.INSERT_FAIL, result);
		}
	}
	
	@Test
	public void insertMultipleNullTest() {
		String pattern = "MultipleInsertNullTest_%d";
		int insertCount = 20;
		
		for(int i = 0; i < insertCount; ++i) {
			Category category = new Category(String.format(pattern, i + 1));
			
			int result = dao.insert(category);
			
			assertNotEquals(MainCategoryDAO.INSERT_FAIL, result);
		}
	}
	
	@Test
	public void getSingleTest() {
		int ID = 1;
		
		Category result = dao.get(ID);
		
		assertTrue(result != null && result.getID() == ID);
	}
	
	@Test
	public void getWithParentTest() {
		int mainCategoryID = 4;
		MainCategory main = DAOFactory.get(databaseType).getMainCategory().get(mainCategoryID);
		
		List<Category> result = dao.getWithMainCategory(main);
		
		assertTrue(result != null);
	}
	
	@Test
	public void getAllTest() {
		try {
			List<Category> result = dao.getAll();
			
			assertTrue(result != null && result.size() == Utilities.count("Category", databaseType));
		}
		catch(Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void updateTest() {
		int ID = 1;
		Category toUpdate = dao.get(ID);

		toUpdate.setName(toUpdate.getName() + "-UPDATE");
		boolean result = dao.update(toUpdate);
		
		assertTrue(result);
	}
	
	@Test
	public void deleteTest() {
		int ID = 10;
		
		boolean result = dao.delete(ID);
		
		assertTrue(result);
	}
}

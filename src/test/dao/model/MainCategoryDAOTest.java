package test.dao.model;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import mvc.dao.DAOFactory;
import mvc.dao.MainCategory.MainCategoryDAO;
import mvc.model.MainCategory;
import test.dao.DAOutils;

@RunWith(Parameterized.class)
public class MainCategoryDAOTest {
	private MainCategoryDAO dao = null;
	private int databaseType;
	
	@Parameters
    public static List<Object> data() {
        return Arrays.asList(new Object[] {     
                 DAOFactory.SQLITE, DAOFactory.MYSQL, DAOFactory.POSTGRES  
           });
    }
	
	public MainCategoryDAOTest(int database) {
		databaseType = database;
	}
	
	@Before
	public void initialize() {
		dao = new MainCategoryDAO(databaseType);
	}
	
	@Test
	public void loadQueriesTest() {
		String[] fieldNames = {"CREATE_TABLE", "INSERT", "GET", "GET_ALL", "UPDATE", "DELETE"};
		
		try {
			Class<?> cls = dao.getClass();
			
			for(String name : fieldNames) {
				Field field = cls.getDeclaredField(name);
				field.setAccessible(true);
				
				System.out.println(field.get(dao));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void createTableTest() {
		try {
			dao.createTable();
			List<String> tableList = DAOutils.getTableNames(databaseType);
			
			assertTrue(tableList.contains("MainCategory") || tableList.contains("maincategory"));
		}
		catch(Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void insertTest() {
		MainCategory category = new MainCategory("SingleInsertTest");
		
		int result = dao.insert(category);
		
		assertNotEquals(MainCategoryDAO.INSERT_FAIL, result);
	}
	
	@Test
	public void insertMultipleTest() {
		String pattern = "MultipleInsertTest_%d";
		int insertCount = 20;
		
		for(int i = 0; i < insertCount; ++i) {
			MainCategory category = new MainCategory(String.format(pattern, i + 1));
			
			int result = dao.insert(category);
			
			assertNotEquals(MainCategoryDAO.INSERT_FAIL, result);
		}
	}
	
	public void getSingleTest() {
		int ID = 1;
		
		MainCategory result = dao.get(ID);
		
		assertTrue(result != null && result.getID() == ID);
	}
	
	public void getAllTest() {
		try {
			List<MainCategory> result = dao.getAll();
			
			assertTrue(result != null && result.size() == DAOutils.count("MainCategory", databaseType));
		}
		catch(Exception ex) {
			fail(ex.getMessage());
		}
	}
	
	@Test
	public void updateTest() {
		int ID = 1;
		MainCategory toUpdate = dao.get(ID);

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

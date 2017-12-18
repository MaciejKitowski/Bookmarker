package test.dao.MainCategory;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.omg.CORBA.PUBLIC_MEMBER;

import mvc.dao.DAOFactory;
import mvc.dao.MainCategory.IMainCategoryDAO;
import mvc.dao.MainCategory.MainCategoryDAO;
import mvc.dao.MainCategory.SqliteMainCategory;
import mvc.model.MainCategory;
import test.dao.DAOutils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainCategoryDAOTest {
	MainCategoryDAO dao = null;
	
	@Before
	public void initialize() {
		dao = new MainCategoryDAO(DAOFactory.SQLITE);
	}
	
	@Test
	public void createTableTest() {
		try {
			dao.createTable();
			List<String> tableList = DAOutils.getTableNames(DAOFactory.SQLITE);
			
			assertTrue(tableList.contains("MainCategory"));
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
		int insertCount = 100;
		
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
			
			assertTrue(result != null && result.size() == DAOutils.count("MainCategory", DAOFactory.SQLITE));
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
}

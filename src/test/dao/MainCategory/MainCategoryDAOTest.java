package test.dao.MainCategory;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.runners.MethodSorters;

import mvc.dao.DAOFactory;
import mvc.dao.MainCategory.MainCategoryDAO;
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
}

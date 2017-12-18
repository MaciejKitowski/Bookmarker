package test.dao.MainCategory;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import mvc.dao.DAOFactory;
import mvc.dao.MainCategory.MainCategoryDAO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainCategoryDAOTest {
	MainCategoryDAO dao = null;
	
	@Before
	public void initialize() {
		dao = new MainCategoryDAO(DAOFactory.SQLITE);
	}
	
	@Test
	public void createTableTest() {
		dao.createTable();
	}
}

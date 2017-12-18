package test.dao.MainCategory;

import org.junit.Test;

import mvc.dao.DAOFactory;
import mvc.dao.MainCategory.MainCategoryDAO;

public class MainCategoryCreateTableTest {
	MainCategoryDAO dao = null;
	
	@Test
	public void sqliteTest() {
		dao = new MainCategoryDAO(DAOFactory.SQLITE);
		dao.createTable();
	}
}

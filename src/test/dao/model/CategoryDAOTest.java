package test.dao.model;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import mvc.dao.DAOFactory;
import mvc.dao.Category.CategoryDAO;
import mvc.dao.MainCategory.MainCategoryDAO;

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
}

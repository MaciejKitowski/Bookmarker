package test.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import mvc.dao.DAOFactory;
import mvc.dao.model.ICategoryDAO;
import mvc.dao.model.IMainCategoryDAO;

@RunWith(Parameterized.class)
public class DatabaseFactoryTest {
	private int databaseType;
	
	@Parameters
    public static List<Object> data() {
        return Arrays.asList(new Object[] {     
                 DAOFactory.SQLITE, DAOFactory.MYSQL, DAOFactory.POSTGRES  
           });
    }
	
	public DatabaseFactoryTest(int database) {
		databaseType = database;
	}
	
	@Test
	public void getConnectionTest() throws Exception {
		Connection connection = DAOFactory.get(databaseType).getConnection();
		
		assertNotNull(connection);
		assertFalse(connection.isClosed());
		
		connection.close();
		assertTrue(connection.isClosed());
	}
	
	@Test
	public void getMainCategoryTest() {
		IMainCategoryDAO dao = DAOFactory.get(databaseType).getMainCategory();
		
		assertNotNull(dao);
	}
	
	@Test
	public void getCategoryTest() {
		ICategoryDAO dao = DAOFactory.get(databaseType).getCategory();
		
		assertNotNull(dao);
	}
}

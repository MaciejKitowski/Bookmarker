package test.dao.MainCategory;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mvc.dao.MysqlFactory;
import mvc.dao.MainCategory.IMainCategoryDAO;
import mvc.dao.MainCategory.MysqlMainCategory;
import mvc.model.MainCategory;

public class MysqlMainCategoryTest {
	private List<String> getTableNames() throws Exception {
		Connection connection = MysqlFactory.getConnection();
		DatabaseMetaData meta = connection.getMetaData();
		List<String> buffer = new ArrayList<>();
		
		ResultSet rs = meta.getTables(null, null, "%", null);
		while(rs.next()) buffer.add(rs.getString(3));
		
		rs.close();
		connection.close();
		
		return buffer;
	}
	
	private int getProfileCount() throws Exception {
		String sql = "SELECT COUNT(*) FROM MainCategory";
		int count = 0;
		Connection connection = MysqlFactory.getConnection();
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery(sql);
		if(rs != null && rs.next()) count = rs.getInt(1);
		
		rs.close();
		statement.close();
		connection.close();
		
		return count;
	}
	
	@Test
	public void createTableTest() throws Exception {
		IMainCategoryDAO catDAO = new MysqlMainCategory();
		catDAO.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("MainCategory"));
	}
	
	@Test
	public void insertTest() {
		MainCategory category = new MainCategory("SingleInsertTest");
		IMainCategoryDAO catDAO = new MysqlMainCategory();
		
		int result = catDAO.insert(category);
		
		assertNotEquals(result, MysqlMainCategory.INSERT_FAIL);
	}
	
	@Test
	public void insertMultipleTest() {
		String pattern = "MultipleInsertTest_%d";
		int insertCount = 10;
		IMainCategoryDAO catDAO = new MysqlMainCategory();
		
		for(int i = 0; i < insertCount; ++i)  {
			MainCategory category = new MainCategory(String.format(pattern, i + 1));
			
			int result = catDAO.insert(category);
			
			assertNotEquals(result, MysqlMainCategory.INSERT_FAIL);
		}
	}
	
	@Test
	public void getSingleTest() {
		int ID = 1;
		IMainCategoryDAO catDAO = new MysqlMainCategory();
		
		MainCategory result = catDAO.get(ID);
		
		assertTrue((result == null || result.getID() == ID)); 
	}
	
	@Test
	public void getAllTest() throws Exception {
		IMainCategoryDAO catDAO = new MysqlMainCategory();
		
		List<MainCategory> result = catDAO.getAll();
		
		assertTrue((result != null && result.size() == getProfileCount()));
	}
}

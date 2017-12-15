package test.dao.Category;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mvc.dao.MysqlFactory;
import mvc.dao.PostgresFactory;
import mvc.dao.Category.ICategoryDAO;
import mvc.dao.Category.MysqlCategory;
import mvc.dao.Category.PostgresCategory;
import mvc.dao.MainCategory.MysqlMainCategory;
import mvc.dao.MainCategory.PostgresMainCategory;
import mvc.model.Category;
import mvc.model.MainCategory;

public class PostgresCategoryTest {
	private List<String> getTableNames() throws Exception {
		Connection connection = PostgresFactory.getConnection();
		DatabaseMetaData meta = connection.getMetaData();
		List<String> buffer = new ArrayList<>();
		
		ResultSet rs = meta.getTables(null, null, "%", null);
		while(rs.next()) buffer.add(rs.getString(3));
		
		rs.close();
		connection.close();
		
		return buffer;
	}
	
	private int getProfileCount() throws Exception {
		String sql = "SELECT COUNT(*) FROM Category";
		int count = 0;
		Connection connection = PostgresFactory.getConnection();
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
		ICategoryDAO dao = new PostgresCategory();
		dao.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("category"));
	}
	
	@Test
	public void insertTest() {
		MainCategory parent = new PostgresMainCategory().get(3);
		Category category = new Category("SingleInsertTest");
		category.setParent(parent);
		ICategoryDAO dao = new PostgresCategory();
		
		int result = dao.insert(category);
		
		assertNotEquals(result, PostgresCategory.INSERT_FAIL);
	}
	
	@Test
	public void insertMultipleTest() {
		String pattern = "MultipleInsertTest_(%d)%s_%d";
		int insertCount = 10;
		MainCategory parent = new PostgresMainCategory().get(4);
		ICategoryDAO dao = new PostgresCategory();
		
		for(int i = 0; i < insertCount; ++i) {
			Category category = new Category(String.format(pattern, parent.getID(), parent.getName(), i+1));
			category.setParent(parent);
			
			int result = dao.insert(category);
			
			assertNotEquals(result, PostgresCategory.INSERT_FAIL);
		}
	}
	
	@Test
	public void getSingleTest() {
		int ID = 1;
		ICategoryDAO dao = new PostgresCategory();
		
		Category result = dao.get(ID);
		
		assertTrue((result == null || result.getID() == ID));
	}
}

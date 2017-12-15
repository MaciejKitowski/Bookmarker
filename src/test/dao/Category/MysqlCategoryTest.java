package test.dao.Category;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mvc.dao.MysqlFactory;
import mvc.dao.SqliteFactory;
import mvc.dao.Category.ICategoryDAO;
import mvc.dao.Category.MysqlCategory;
import mvc.dao.Category.SqliteCategory;
import mvc.dao.MainCategory.MysqlMainCategory;
import mvc.dao.MainCategory.SqliteMainCategory;
import mvc.model.Category;
import mvc.model.MainCategory;

public class MysqlCategoryTest {
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
	
	@Test
	public void createTableTest() throws Exception {
		ICategoryDAO dao = new MysqlCategory();
		dao.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("Category"));
	}
	
	@Test
	public void insertTest() {
		MainCategory parent = new MysqlMainCategory().get(3);
		Category category = new Category("SingleInsertTest");
		category.setParent(parent);
		ICategoryDAO dao = new MysqlCategory();
		
		int result = dao.insert(category);
		
		assertNotEquals(result, MysqlCategory.INSERT_FAIL);
	}
	
	@Test
	public void insertMultipleTest() {
		String pattern = "MultipleInsertTest_(%d)%s_%d";
		int insertCount = 10;
		MainCategory parent = new MysqlMainCategory().get(4);
		ICategoryDAO dao = new MysqlCategory();
		
		for(int i = 0; i < insertCount; ++i) {
			Category category = new Category(String.format(pattern, parent.getID(), parent.getName(), i+1));
			category.setParent(parent);
			
			int result = dao.insert(category);
			
			assertNotEquals(result, MysqlCategory.INSERT_FAIL);
		}
	}
}

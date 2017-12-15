package test.dao.Url;

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
import mvc.dao.SqliteFactory;
import mvc.dao.Category.MysqlCategory;
import mvc.dao.Category.SqliteCategory;
import mvc.dao.Url.IUrlDAO;
import mvc.dao.Url.MysqlUrl;
import mvc.dao.Url.SqliteUrl;
import mvc.model.Category;
import mvc.model.Url;

public class MysqlUrlTest {
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
		String sql = "SELECT COUNT(*) FROM Url";
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
		IUrlDAO dao = new MysqlUrl();
		dao.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("Url"));
	}
	
	@Test
	public void insertTest() {
		Url url = new Url("http://test", "SingleinsertTest", "Single");
		Category category = new MysqlCategory().get(3);
		url.setCategory(category);
		IUrlDAO dao = new MysqlUrl();
		
		int result = dao.insert(url);
		
		assertNotEquals(result, MysqlUrl.INSERT_FAIL);
	}
	
	@Test
	public void insertMultipleTest() {
		String pattern = "MultipleInsertTest_%d";
		int insertCount = 10;
		Category category = new MysqlCategory().get(4);
		IUrlDAO dao = new MysqlUrl();
		
		for(int i = 0; i < insertCount; ++i) {
			Url url = new Url("http://test", String.format(pattern, i + 1), "Single");
			url.setCategory(category);
			
			int result = dao.insert(url);
			
			assertNotEquals(result, SqliteUrl.INSERT_FAIL);
		}
	}
	
	@Test
	public void getSingleTest() {
		int ID = 1;
		IUrlDAO dao = new MysqlUrl();
		
		Url result = dao.get(ID);
		
		assertTrue((result == null || result.getID() == ID));
	}
	
	@Test
	public void getAllTest() throws Exception {
		IUrlDAO dao = new MysqlUrl();
		
		List<Url> result = dao.getAll();
		
		assertTrue((result != null && result.size() == getProfileCount()));
	}
	
	@Test
	public void getCategoryTest() {
		Category category = new MysqlCategory().get(4);
		IUrlDAO dao = new MysqlUrl();
		
		List<Url> result = dao.getAllParent(category);
		
		assertTrue(result != null);
	}
	
	@Test
	public void updateTest() {
		IUrlDAO dao = new MysqlUrl();
		Url toUpdate = dao.get(1);
		toUpdate.setTitle(toUpdate.getTitle() + "-UPDATED");
		
		boolean result = dao.update(toUpdate);
		
		assertTrue(result);
	}
	
	@Test
	public void deleteTest() {
		int ID = 10;
		IUrlDAO dao = new MysqlUrl();
		
		boolean result = dao.delete(ID);
		
		assertTrue(result);
	}
}

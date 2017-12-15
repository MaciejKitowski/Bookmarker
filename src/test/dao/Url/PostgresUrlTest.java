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

import mvc.dao.PostgresFactory;
import mvc.dao.Category.PostgresCategory;
import mvc.dao.Url.IUrlDAO;
import mvc.dao.Url.PostgresUrl;
import mvc.model.Category;
import mvc.model.Url;

public class PostgresUrlTest {
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
		String sql = "SELECT COUNT(*) FROM Url";
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
		IUrlDAO dao = new PostgresUrl();
		dao.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("url"));
	}
	
	@Test
	public void insertTest() {
		Url url = new Url("http://test", "SingleinsertTest", "Single");
		Category category = new PostgresCategory().get(3);
		url.setCategory(category);
		IUrlDAO dao = new PostgresUrl();
		
		int result = dao.insert(url);
		
		assertNotEquals(result, PostgresUrl.INSERT_FAIL);
	}
	
	@Test
	public void insertMultipleTest() {
		String pattern = "MultipleInsertTest_%d";
		int insertCount = 10;
		Category category = new PostgresCategory().get(4);
		IUrlDAO dao = new PostgresUrl();
		
		for(int i = 0; i < insertCount; ++i) {
			Url url = new Url("http://test", String.format(pattern, i + 1), "Single");
			url.setCategory(category);
			
			int result = dao.insert(url);
			
			assertNotEquals(result, PostgresUrl.INSERT_FAIL);
		}
	}
	
	@Test
	public void getSingleTest() {
		int ID = 1;
		IUrlDAO dao = new PostgresUrl();
		
		Url result = dao.get(ID);
		
		assertTrue((result == null || result.getID() == ID));
	}
	
	@Test
	public void getAllTest() throws Exception {
		IUrlDAO dao = new PostgresUrl();
		
		List<Url> result = dao.getAll();
		
		assertTrue((result != null && result.size() == getProfileCount()));
	}
	
	@Test
	public void getCategoryTest() {
		Category category = new PostgresCategory().get(4);
		IUrlDAO dao = new PostgresUrl();
		
		List<Url> result = dao.getAllParent(category);
		
		assertTrue(result != null);
	}
	
	@Test
	public void updateTest() {
		IUrlDAO dao = new PostgresUrl();
		Url toUpdate = dao.get(1);
		toUpdate.setTitle(toUpdate.getTitle() + "-UPDATED");
		
		boolean result = dao.update(toUpdate);
		
		assertTrue(result);
	}
	
	@Test
	public void deleteTest() {
		int ID = 10;
		IUrlDAO dao = new PostgresUrl();
		
		boolean result = dao.delete(ID);
		
		assertTrue(result);
	}
}

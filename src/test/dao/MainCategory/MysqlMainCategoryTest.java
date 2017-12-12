package test.dao.MainCategory;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mvc.dao.MysqlFactory;
import mvc.dao.MainCategory.IMainCategoryDAO;
import mvc.dao.MainCategory.MysqlMainCategory;

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
	
	@Test
	public void createTableTest() throws Exception {
		IMainCategoryDAO catDAO = new MysqlMainCategory();
		catDAO.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("MainCategory"));
	}
}

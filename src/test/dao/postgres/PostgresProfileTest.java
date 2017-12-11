package test.dao.postgres;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mvc.dao.model_interfaces.IProfileDAO;
import mvc.dao.postgres.PostgresFactory;
import mvc.dao.postgres.PostgresProfile;
import mvc.dao.sqlite.SqliteProfile;

public final class PostgresProfileTest {
	
	private List<String> getTableNames() throws Exception {
		Connection connection = PostgresFactory.getConnection();
		DatabaseMetaData meta = connection.getMetaData();
		List<String> buffer = new ArrayList<>();
		
		ResultSet rs = meta.getTables(null, null, "%", new String[] { "TABLE" });
		while(rs.next()) buffer.add(rs.getString(3));
		
		rs.close();
		connection.close();
		
		return buffer;
	}
	
	@Test
	public void createTableTest() throws Exception {
		IProfileDAO profileDAO = new PostgresProfile();
		profileDAO.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("profile"));
	}
	
	/*
	 * @Test
	public void createTableTest() throws Exception {
		IProfileDAO profileDAO = new SqliteProfile();
		profileDAO.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("profile"));
	}
	 */
}

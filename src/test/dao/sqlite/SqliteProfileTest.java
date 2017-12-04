package test.dao.sqlite;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mvc.dao.model_interfaces.IProfileDAO;
import mvc.dao.sqlite.SqliteFactory;
import mvc.dao.sqlite.SqliteProfile;
import mvc.model.Profile;

public final class SqliteProfileTest {
	
	private List<String> getTableNames() throws Exception {
		Connection connection = SqliteFactory.getConnection();
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
		IProfileDAO profileDAO = new SqliteProfile();
		profileDAO.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("profile"));
	}
	
	@Test
	public void insertTest() {
		Profile profile = new Profile("singleInsertTest");
		IProfileDAO profileDAO = new SqliteProfile();
		
		int result = profileDAO.insert(profile);
		
		assertNotEquals(result, SqliteProfile.INSERT_FAIL);
	}
	
	@Test
	public void insertMultipleTest() {
		String namePatternt = "multipleInsertTest_%d";
		int insertCount = 10;
		IProfileDAO profileDAO = new SqliteProfile();
		
		for(int i = 0; i < insertCount; ++i) {
			Profile profile = new Profile(String.format(namePatternt, i + 1));
			
			int result = profileDAO.insert(profile);
			
			assertNotEquals(result, SqliteProfile.INSERT_FAIL);
		}
	}
}

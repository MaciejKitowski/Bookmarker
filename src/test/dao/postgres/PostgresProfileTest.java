package test.dao.postgres;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import mvc.dao.model_interfaces.IProfileDAO;
import mvc.dao.postgres.PostgresFactory;
import mvc.dao.postgres.PostgresProfile;
import mvc.dao.sqlite.SqliteProfile;
import mvc.model.Profile;

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
	
	private int getProfileCount() throws Exception {
		String sql = "SELECT COUNT(*) FROM profile";
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
		IProfileDAO profileDAO = new PostgresProfile();
		profileDAO.createTable();
		
		List<String> tables = getTableNames();
		
		assertTrue(tables.contains("profile"));
	}
	
	@Test
	public void insertTest() {
		Profile profile = new Profile("singleInsertTest");
		IProfileDAO profileDAO = new PostgresProfile();
		
		int result = profileDAO.insert(profile);
		
		assertNotEquals(result, SqliteProfile.INSERT_FAIL);
	}
	
	@Test
	public void insertMultipleTest() {
		String namePatternt = "multipleInsertTest_%d";
		int insertCount = 10;
		IProfileDAO profileDAO = new PostgresProfile();
		
		for(int i = 0; i < insertCount; ++i) {
			Profile profile = new Profile(String.format(namePatternt, i + 1));
			
			int result = profileDAO.insert(profile);
			
			assertNotEquals(result, SqliteProfile.INSERT_FAIL);
		}
	}
	
	@Test
	public void getSingleTest() {
		int ID = 1;
		IProfileDAO profileDAO = new PostgresProfile();
		
		Profile result = profileDAO.get(ID);
		
		assertTrue((result == null || result.getID() == ID)); 
	}
	
	@Test
	public void getAllTest() throws Exception {
		IProfileDAO profileDAO = new PostgresProfile();
		
		List<Profile> result = profileDAO.getAll();
		
		assertTrue((result != null && result.size() == getProfileCount()));
	}
	
	@Test
	public void updateTest() {
		IProfileDAO profileDAO = new PostgresProfile();
		Profile toUpdate = profileDAO.get(1);
		toUpdate.setName(toUpdate.getName() + "-UPDATED");
		
		boolean result = profileDAO.update(toUpdate);
		
		assertTrue(result);
	}
	
	@Test
	public void deleteTest() {
		int ID = 10;
		IProfileDAO profileDAO = new PostgresProfile();
		
		boolean result = profileDAO.delete(ID);
		
		assertTrue(result);
	}
}

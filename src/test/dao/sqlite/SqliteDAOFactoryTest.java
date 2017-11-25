package test.dao.sqlite;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import mvc.dao.sqlite.SqliteDAOFactory;

public class SqliteDAOFactoryTest {

	@Test
	public void getConnection() throws SQLException {
		Connection connection = SqliteDAOFactory.getConnection();
		
		assertNotNull(connection);
		connection.close();
	}
}

package test.dao.postgres;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import mvc.dao.postgres.PostgresFactory;

public class PostgresDAOFactoryTest {

	@Test
	public void getConnection() throws SQLException {
		Connection connection = PostgresFactory.getConnection();
		
		assertNotNull(connection);
		connection.close();
	}

}

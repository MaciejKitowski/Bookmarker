package test.dao.postgres;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import mvc.dao.postgres.PostgresDAOFactory;

public class PostgresDAOFactoryTest {

	@Test
	public void getConnection() throws SQLException {
		Connection connection = PostgresDAOFactory.getConnection();
		
		assertNotNull(connection);
		connection.close();
	}

}

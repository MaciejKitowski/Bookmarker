package test.dao.mysql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import mvc.dao.mysql.MysqlFactory;

public class MysqlDAOFactoryTest {

	@Test
	public void getConnection() throws SQLException {
		Connection connection = MysqlFactory.getConnection();
				
		assertNotNull(connection);
		connection.close();
	}

}

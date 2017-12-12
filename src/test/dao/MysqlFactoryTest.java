package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import mvc.dao.MysqlFactory;

public class MysqlFactoryTest {

	@Test
	public void getConnection() throws SQLException {
		Connection connection = MysqlFactory.getConnection();
				
		assertNotNull(connection);
		connection.close();
	}

}

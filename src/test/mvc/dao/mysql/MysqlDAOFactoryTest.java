package test.mvc.dao.mysql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import mvc.dao.mysql.MysqlDAOFactory;

public class MysqlDAOFactoryTest {

	@Test
	public void getConnection() throws SQLException {
		Connection connection = MysqlDAOFactory.getConnection();
				
		assertNotNull(connection);
		connection.close();
	}

}

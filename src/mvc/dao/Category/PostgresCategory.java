package mvc.dao.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import mvc.dao.MysqlFactory;
import mvc.model.Category;
import mvc.model.MainCategory;

public final class PostgresCategory implements ICategoryDAO {
	private static final Logger log = Logger.getLogger(PostgresCategory.class.getName());
	
	private static final String CREATE_TABLE = 
		"CREATE TABLE Category( \n" +
				"ID 	SERIAL 	PRIMARY KEY," +
				"parent_ID 	int, \n" +
				"name 	VARCHAR(255) 	NOT NULL, \n" +
				"CONSTRAINT mCat_fk FOREIGN KEY(parent_ID) REFERENCES MainCategory(ID) \n" +
				"ON UPDATE CASCADE \n" +
				"ON DELETE SET NULL \n" +
		")";
	
	private static final String INSERT = "INSERT INTO Category(name, parent_ID) VALUES(?, ?)";
	
	private static final String GET = "SELECT ID, name, parent_ID FROM Category WHERE ID = ?";
	
	private static final String GET_PARENT = "SELECT ID, name, parent_ID FROM Category WHERE parent_ID = ?";
	
	private static final String GET_ALL = "SELECT ID, name, parent_ID name FROM Category";
	
	private static final String UPDATE = "UPDATE Category SET name=?, parent_ID=? WHERE id = ?";
	
	private static final String DELETE = "DELETE FROM Category WHERE id = ?";

	@Override
	public void createTable() {
		log.info("Create new table");
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = MysqlFactory.getConnection();
			statement = connection.createStatement();
			
			statement.execute(CREATE_TABLE);
			
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
	}

	@Override
	public int insert(Category category) {
		log.info(String.format("Insert category: ID=%d, name=%s, parent: ID=%d, name=%s", category.getID(), category.getName(), category.getParent().getID(), category.getParent().getName()));
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		int resultBuffer = 0;
		
		try {
			connection = MysqlFactory.getConnection();
			statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, category.getName());
			statement.setInt(2, category.getParent().getID());
			statement.execute();
			
			result = statement.getGeneratedKeys();
			if(result != null && result.next()) resultBuffer = result.getInt(1);
			else resultBuffer = INSERT_FAIL;
			
			result.close();
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
			resultBuffer = INSERT_FAIL;
		}

		return resultBuffer;
	}

	@Override
	public Category get(int ID) {
		log.info(String.format("Get category: ID=%d", ID));
		
		return null;
	}

	@Override
	public List<Category> getAllParent(MainCategory mainCategory) {
		log.info(String.format("Get all categories with parent: ID=%d, name=%s", mainCategory.getID(), mainCategory.getName()));
		
		return null;
	}

	@Override
	public List<Category> getAll() {
		log.info("Get all categories");
		
		return null;
	}

	@Override
	public boolean update(Category category) {
		log.info(String.format("Update category: ID=%d, name=%s", category.getID(), category.getName()));
		
		return false;
	}

	@Override
	public boolean delete(int ID) {
		log.info(String.format("Delete category: ID=%d", ID));
		
		return false;
	}
}

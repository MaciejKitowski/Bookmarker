package mvc.dao.Category;

import java.util.List;
import java.util.logging.Logger;

import mvc.model.Category;
import mvc.model.MainCategory;

public final class MysqlCategory implements ICategoryDAO {
	private static final Logger log = Logger.getLogger(MysqlCategory.class.getName());
	
	public static int INSERT_FAIL = -1;
	
	private static final String CREATE_TABLE = 
		"CREATE TABLE MainCategory( \n" +
				"ID 	int 	NOT NULL	AUTO_INCREMENT, \n" +
				"parent_ID 	int, \n" +
				"name 	VARCHAR(255) 	NOT NULL \n," +
				"PRIMARY KEY(ID), \n" +
				"FOREIGN KEY(parent_ID) REFERENCES MainCategory(ID) \n" +
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
		
	}

	@Override
	public int insert(Category category) {
		log.info(String.format("Insert category: ID=%d, name=%s, parent: ID=%d, name=%s", category.getID(), category.getName(), category.getParent().getID(), category.getParent().getName()));

		return 0;
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

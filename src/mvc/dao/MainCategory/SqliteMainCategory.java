package mvc.dao.MainCategory;

import java.util.List;
import java.util.logging.Logger;

import mvc.model.MainCategory;

public final class SqliteMainCategory implements IMainCategoryDAO {
	private static final Logger log = Logger.getLogger(SqliteMainCategory.class.getName());
	
	private static final String CREATE_TABLE =
		"CREATE TABLE MainCategory( \n" +
				"ID 	INTEGER 	PRIMARY KEY AUTOINCREMENT, \n" +
				"name 	VARCHAR(255) 	NOT NULL \n" + 
		")";
	
	private static final String INSERT = "INSERT INTO MainCategory(name) VALUES(?)";
	
	private static final String GET = "SELECT ID, name FROM MainCategory WHERE id = ?";
	
	private static final String GET_ALL = "SELECT ID, name FROM MainCategory";
	
	private static final String UPDATE = "UPDATE MainCategory SET name=? WHERE id = ?";
	
	private static final String DELETE = "DELETE FROM MainCategory WHERE id = ?";

	@Override
	public void createTable() {
		log.info("Create new table");
		
	}

	@Override
	public int insert(MainCategory category) {
		log.info(String.format("Insert category: ID=%d, name=%s"));

		return 0;
	}

	@Override
	public MainCategory get(int ID) {
		log.info(String.format("Get category: ID=%d", ID));

		return null;
	}

	@Override
	public List<MainCategory> getAll() {
		log.info("Get all categories");
		
		return null;
	}

	@Override
	public boolean update(MainCategory category) {
		log.info(String.format("Update category(ID=%d, name=%s): ID=%d, name=%s"));

		return false;
	}

	@Override
	public boolean delete(int ID) {
		log.info(String.format("Delete category: ID=%d", ID));

		return false;
	}
}

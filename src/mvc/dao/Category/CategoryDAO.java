package mvc.dao.Category;

import java.util.logging.Logger;

import mvc.dao.DAOFactory;

public final class CategoryDAO {
	private static final Logger log = Logger.getLogger(CategoryDAO.class.getName());
	private static final String queryFilename = "Category.json";
	
	public static int INSERT_FAIL = -1;
	
	private DAOFactory database = null;
	private String CREATE_TABLE = null;
	private String INSERT = null;
	private String GET = null;
	private String GET_ALL = null;
	private String GET_MAINCAT = null;
	private String UPDATE = null;
	private String DELETE = null;
}

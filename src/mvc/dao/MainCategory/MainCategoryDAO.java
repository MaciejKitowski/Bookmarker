package mvc.dao.MainCategory;

import java.util.logging.Logger;

public final class MainCategoryDAO {
	private static final Logger log = Logger.getLogger(MainCategoryDAO.class.getName());
	
	public static int INSERT_FAIL = -1;
	
	private String CREATE_TABLE;
	private String INSERT;
	private String GET;
	private String GET_ALL;
	private String UPDATE;
	private String DELETE;
}

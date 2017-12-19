package mvc.dao.Url;

import java.util.List;
import java.util.logging.Logger;

import mvc.model.Category;
import mvc.model.Url;

public final class UrlDAO implements IUrlDAO {
	private static final Logger log = Logger.getLogger(UrlDAO.class.getName());
	
	public static int INSERT_FAIL = -1;

	@Override
	public void createTable() {
		log.info("Create new table");
		
	}

	@Override
	public int insert(Url url) {
		log.info(String.format("Insert url: ID=%d, title=%s, url=%s", url.getID(), url.getTitle(), url.getTitle()));
		
		return 0;
	}

	@Override
	public Url get(int ID) {
		log.info(String.format("Get url: ID=%d", ID));
		
		return null;
	}

	@Override
	public List<Url> getAllWithCategory(Category category) {
		log.info(String.format("Get all urls with category: ID=%d, name=%s", category.getID(), category.getName()));
		return null;
	}

	@Override
	public List<Url> getAll() {
		log.info("Get all urls");
		return null;
	}

	@Override
	public boolean update(Url url) {
		log.info(String.format("Update url: ID=%d, url=%s", url.getID(), url.getUrl()));
		return false;
	}

	@Override
	public boolean delete(int ID) {
		log.info(String.format("Delete url: ID=%d", ID));
		return false;
	}
}

package mvc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.DAOFactory;
import mvc.dao.model.IUrlDAO;
import mvc.model.Category;
import mvc.model.MainCategory;
import mvc.view.observer.category.CategorySelectedListener;

public final class UrlController implements CategorySelectedListener {
	private static final Logger log = LoggerFactory.getLogger(UrlController.class);
	
	private IUrlDAO urlDao = null;
	
	public UrlController() {
		log.info("Initialize url controller");
		
		//TODO add posibility to get dao factory without parameter (load selected database index from file)
		urlDao = DAOFactory.get(DAOFactory.SQLITE).getUrl();
	}

	@Override
	public void onMainCategorySelect(List<MainCategory> categories) {
		log.debug("Main categories selected");
		
		for(MainCategory cat : categories) log.warn(cat.toString());
	}

	@Override
	public void onCategorySelect(List<Category> categories) {
		log.debug("Main categories selected");
		
		for(Category cat : categories) log.warn(cat.toString());
	}
}

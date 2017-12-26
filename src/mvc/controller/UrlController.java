package mvc.controller;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.controller.observer.category.CategoryChangedListener;
import mvc.controller.observer.url.UrlChangedCaller;
import mvc.controller.observer.url.UrlChangedListener;
import mvc.dao.DAOFactory;
import mvc.dao.model.ICategoryDAO;
import mvc.dao.model.IUrlDAO;
import mvc.model.Category;
import mvc.model.MainCategory;
import mvc.model.Url;
import mvc.view.observer.category.CategorySelectedListener;

public final class UrlController implements CategorySelectedListener, UrlChangedCaller {
	private static final Logger log = LoggerFactory.getLogger(UrlController.class);
	
	private IUrlDAO urlDao = null;
	private ICategoryDAO catDao = null;
	
	public UrlController() {
		log.info("Initialize url controller");
		
		//TODO add posibility to get dao factory without parameter (load selected database index from file)
		urlDao = DAOFactory.get(DAOFactory.SQLITE).getUrl();
		catDao = DAOFactory.get(DAOFactory.SQLITE).getCategory();
	}

	@Override
	public void onMainCategorySelect(List<MainCategory> categories) {
		log.debug("Main categories selected");
		
		List<Url> urls = new LinkedList<>();
		
		for(MainCategory cat : categories) {
			log.debug("Get all subcategories from {} category", cat.toString());
			List<Category> subcategories = catDao.getWithMainCategory(cat);
			log.debug("Found {} subcategories", subcategories.size());
			
			for(Category subcat : subcategories) {
				log.debug("Get all urls from {} subcategory", subcat.toString());
				
				urls.addAll(urlDao.getAllWithCategory(subcat));
			}
		}
		
		log.debug("Found {} urls", urls.size());
	}

	@Override
	public void onCategorySelect(List<Category> categories) {
		log.debug("Main categories selected");
		
		List<Url> urls = new LinkedList<>();
		
		for(Category subcat : categories) {
			log.debug("Get all urls from {} subcategory", subcat.toString());
			
			urls.addAll(urlDao.getAllWithCategory(subcat));
		}
		
		log.debug("Found {} urls", urls.size());
	}
	
	private List<UrlChangedListener> listeners;

	@Override
	public void addListener(UrlChangedListener listener) {
		log.debug("Add new listener");
		listeners.add(listener);
	}

	@Override
	public void removeListener(UrlChangedListener listener) {
		log.debug("Remove listener");
		listeners.remove(listener);
	}

	@Override
	public void callListeners(List<Url> urls) {
		log.debug("Call {} url changed listeners", listeners.size());
		for(UrlChangedListener listener : listeners) listener.onUrlChanged(urls);
	}
}

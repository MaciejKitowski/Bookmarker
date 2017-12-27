package mvc.controller;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.DAOFactory;
import mvc.dao.model.ISubcategoryDAO;
import mvc.dao.model.IUrlDAO;
import mvc.model.Subcategory;
import mvc.model.MainCategory;
import mvc.model.Url;
import mvc.observer.category.CategorySelectListener;
import mvc.observer.url.UrlUpdateListener;
import mvc.observer.url.UrlUpdateSubject;

public final class UrlController implements CategorySelectListener, UrlUpdateSubject {
	private static final Logger log = LoggerFactory.getLogger(UrlController.class);
	
	private List<UrlUpdateListener> urlUpdateListeners = new LinkedList<>();
	private IUrlDAO urlDao = null;
	private ISubcategoryDAO catDao = null;
	
	public UrlController() {
		log.info("Initialize url controller");
		
		//TODO add posibility to get dao factory without parameter (load selected database index from file)
		urlDao = DAOFactory.get(DAOFactory.SQLITE).getUrl();
		catDao = DAOFactory.get(DAOFactory.SQLITE).getCategory();
	}

	@Override
	public void onSelectMainCategory(List<MainCategory> categories) {
		log.debug("Main categories selected");
		
		List<Url> urls = new LinkedList<>();
		
		for(MainCategory cat : categories) {
			log.debug("Get all subcategories from {} category", cat.toString());
			
			List<Subcategory> subcategories = catDao.getWithMainCategory(cat);
			log.debug("Found {} subcategories", subcategories.size());
			
			for(Subcategory subcat : subcategories) {
				log.debug("Get all urls from {} subcategory", subcat.toString());
				
				urls.addAll(urlDao.getAllWithCategory(subcat));
			}
		}
		
		log.debug("Found {} urls", urls.size());
		updateUrls(urls);
	}

	@Override
	public void onSelectCategory(List<Subcategory> subcategories) {
		log.debug("Main categories selected");
		
		List<Url> urls = new LinkedList<>();
		
		for(Subcategory subcat : subcategories) {
			log.debug("Get all urls from {} subcategory", subcat.toString());
			
			urls.addAll(urlDao.getAllWithCategory(subcat));
		}
		
		log.debug("Found {} urls", urls.size());
		updateUrls(urls);
	}
	
	@Override
	public void addUrlUpdateListener(UrlUpdateListener listener) {
		log.debug("Add new listener");
		urlUpdateListeners.add(listener);
	}

	@Override
	public void removeUrlUpdateListener(UrlUpdateListener listener) {
		log.debug("Remove listener");
		urlUpdateListeners.remove(listener);
	}

	@Override
	public void updateUrls(List<Url> urls) {
		log.debug("Call {} url update listeners", urlUpdateListeners.size());
		for(UrlUpdateListener listener : urlUpdateListeners) listener.onUrlUpdate(urls);
	}
}

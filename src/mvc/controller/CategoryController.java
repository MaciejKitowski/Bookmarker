package mvc.controller;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.controller.observer.category.CategoryChangedCaller;
import mvc.controller.observer.category.CategoryChangedListener;
import mvc.dao.DAOFactory;
import mvc.dao.model.ICategoryDAO;
import mvc.dao.model.IMainCategoryDAO;
import mvc.model.Category;
import mvc.model.MainCategory;

public final class CategoryController implements CategoryChangedCaller {
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	private List<CategoryChangedListener> listeners = new LinkedList<>();
	private IMainCategoryDAO mainDao = null;
	private ICategoryDAO catDao = null;
	
	public CategoryController() {
		log.info("Initialize category controller");
		
		//TODO add posibility to get dao factory without parameter (load selected database index from file)
		mainDao = DAOFactory.get(DAOFactory.SQLITE).getMainCategory();
		catDao = DAOFactory.get(DAOFactory.SQLITE).getCategory();
	}

	@Override
	public void addListener(CategoryChangedListener listener) {
		log.debug("Add new listener");
		listeners.add(listener);
	}

	@Override
	public void removeListener(CategoryChangedListener listener) {
		log.debug("Remove listener");
		listeners.remove(listener);
	}
	
	private void callCategoryChangedListeners() {
		log.debug("Call {} category changed listeners", listeners.size());
		for(CategoryChangedListener listener : listeners) listener.onCategoryChanged(getCategories());
	}
	
	private Map<MainCategory, List<Category>> getCategories() {
		log.debug("Load categories to map");
		
		Map<MainCategory, List<Category>> categories = new HashMap<>();
		List<MainCategory> mainCategories = mainDao.getAll();
		
		for(MainCategory category : mainCategories) {
			log.debug("Create list for main category(ID={} name={})", category.getID(), category.getName());
			
			List<Category> subcategories = catDao.getWithMainCategory(category);
			categories.put(category, subcategories);
			
			log.debug("Added {} subcategories", subcategories.size());
		}
		
		return categories;
	}
}

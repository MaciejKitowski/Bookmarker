package mvc.controller;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.DAOFactory;
import mvc.dao.model.ICategoryDAO;
import mvc.dao.model.IMainCategoryDAO;
import mvc.model.Category;
import mvc.model.MainCategory;
import mvc.observer.category.CategoryUpdateListener;
import mvc.observer.category.CategoryUpdateSubject;

public final class CategoryController implements CategoryUpdateSubject {
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	private List<CategoryUpdateListener> catUpdateListeners = new LinkedList<>();
	private IMainCategoryDAO mainDao = null;
	private ICategoryDAO catDao = null;
	
	public CategoryController() {
		log.info("Initialize category controller");
		
		//TODO add posibility to get dao factory without parameter (load selected database index from file)
		mainDao = DAOFactory.get(DAOFactory.SQLITE).getMainCategory();
		catDao = DAOFactory.get(DAOFactory.SQLITE).getCategory();
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

	@Override
	public void addCategoryUpdateListener(CategoryUpdateListener listener) {
		log.debug("Add new listener");
		catUpdateListeners.add(listener);
	}

	@Override
	public void removeCategoryUpdateListener(CategoryUpdateListener listener) {
		log.debug("Remove listener");
		catUpdateListeners.remove(listener);
	}

	@Override
	public void updateCategories() {
		log.debug("Call {} category updated listeners", catUpdateListeners.size());
		for(CategoryUpdateListener listener : catUpdateListeners) listener.onCategoryUpdate(getCategories());
	}
}

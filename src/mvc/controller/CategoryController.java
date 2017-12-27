package mvc.controller;


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.dao.DAOFactory;
import mvc.dao.model.ISubcategoryDAO;
import mvc.dao.model.ICategoryDAO;
import mvc.model.Subcategory;
import mvc.model.Category;
import mvc.observer.category.CategoryUpdateListener;
import mvc.observer.category.CategoryUpdateSubject;

public final class CategoryController implements CategoryUpdateSubject {
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	private List<CategoryUpdateListener> catUpdateListeners = new LinkedList<>();
	private ICategoryDAO mainDao = null;
	private ISubcategoryDAO catDao = null;
	
	public CategoryController() {
		log.info("Initialize subcategory controller");
		
		//TODO add posibility to get dao factory without parameter (load selected database index from file)
		mainDao = DAOFactory.get(DAOFactory.SQLITE).getMainCategory();
		catDao = DAOFactory.get(DAOFactory.SQLITE).getCategory();
	}
			
	private Map<Category, List<Subcategory>> getCategories() {
		log.debug("Load categories to map");
		
		Map<Category, List<Subcategory>> categories = new LinkedHashMap<>();
		List<Category> mainCategories = mainDao.getAll();
		
		for(Category category : mainCategories) {
			log.debug("Create list for main category(ID={} name={})", category.getID(), category.getName());
			
			List<Subcategory> subcategories = catDao.getWithCategory(category);
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
		
		Map<Category, List<Subcategory>> categories = getCategories();
		for(CategoryUpdateListener listener : catUpdateListeners) listener.onCategoryUpdate(categories);
	}
}

package mvc.controller;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mvc.controller.observer.category.CategoryChangedCaller;
import mvc.controller.observer.category.CategoryChangedListener;
import mvc.model.Category;
import mvc.model.MainCategory;

public final class CategoryController implements CategoryChangedCaller {
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
	
	private List<CategoryChangedListener> listeners = new LinkedList<>();

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
		
		Map<MainCategory, List<Category>> categories = new HashMap<>();
		
		for(CategoryChangedListener listener : listeners) listener.onCategoryChange(categories);
	}
}

package mvc.controller.observer.category;

import java.util.List;
import java.util.Map;

import mvc.model.MainCategory;
import mvc.model.Category;

public interface CategoryChangedListener {
	public void onCategoryChanged(Map<MainCategory, List<Category>> categories);
}

package mvc.controller.observer.category;

import java.util.ArrayList;
import java.util.Map;

import mvc.model.MainCategory;
import mvc.model.Category;

public interface CategoryChangedListener {
	public void onCategoryChange(Map<MainCategory, ArrayList<Category>> categories);
}

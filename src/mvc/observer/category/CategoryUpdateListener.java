package mvc.observer.category;

import java.util.List;
import java.util.Map;

import mvc.model.Subcategory;
import mvc.model.MainCategory;

public interface CategoryUpdateListener {
	public void onCategoryUpdate(Map<MainCategory, List<Subcategory>> categories);
}

package mvc.observer.category;

import java.util.List;

import mvc.model.Subcategory;
import mvc.model.Category;

public interface CategorySelectListener {
	public void onSelectCategory(List<Category> categories);
	public void onSelectSubcategory(List<Subcategory> subcategories);
	public void onUnselectAllCategories();
}

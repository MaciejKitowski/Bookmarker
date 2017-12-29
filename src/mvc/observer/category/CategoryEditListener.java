package mvc.observer.category;

import java.util.List;

import mvc.model.Category;
import mvc.model.Subcategory;

public interface CategoryEditListener {
	public void onCategoryDelete(List<Category> categories);
	public void onSubcategoryDelete(List<Subcategory> subcategories);
}

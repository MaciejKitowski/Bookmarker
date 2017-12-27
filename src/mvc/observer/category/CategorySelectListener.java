package mvc.observer.category;

import java.util.List;

import mvc.model.Subcategory;
import mvc.model.Category;

public interface CategorySelectListener {
	public void onSelectMainCategory(List<Category> categories);
	public void onSelectCategory(List<Subcategory> subcategories);
}

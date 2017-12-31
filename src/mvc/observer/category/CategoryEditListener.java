package mvc.observer.category;

import java.util.List;

import mvc.model.Category;
import mvc.model.Subcategory;

public interface CategoryEditListener {
	public void onCategoryDelete(List<Category> categories);
	public void onSubcategoryDelete(List<Subcategory> subcategories);
	
	public void onCategoryAdd(Category category);
	public void onSubcategoryAdd(Subcategory subcategory);
	
	public void onCategoryEdit(List<Category> categories);
	public void onSubcategoryEdit(List<Subcategory> subcategories);
}

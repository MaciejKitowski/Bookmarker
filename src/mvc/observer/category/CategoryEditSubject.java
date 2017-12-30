package mvc.observer.category;

import java.util.List;

import mvc.model.Category;
import mvc.model.Subcategory;

public interface CategoryEditSubject {
	public void addCategoryEditListener(CategoryEditListener listener);
	public void removeCategoryEditListener(CategoryEditListener listener);
	
	public void deleteCategories(List<Category> categories);
	public void deleteSubcategories(List<Subcategory> subcategories);
	
	public void addCategory(Category category);
}

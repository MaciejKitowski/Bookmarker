package mvc.observer.category;

import java.util.List;

import mvc.model.Subcategory;
import mvc.model.Category;

public interface CategorySelectSubject {
	public void addCategorySelectListener(CategorySelectListener listener);
	public void removeCategorySelectListener(CategorySelectListener listener);
	
	public void selectMainCategory(List<Category> categories);
	public void selectCategory(List<Subcategory> subcategories);
}

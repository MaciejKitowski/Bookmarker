package mvc.observer.category;

import java.util.List;

import mvc.model.Category;
import mvc.model.MainCategory;

public interface CategorySelectSubject {
	public void addCategorySelectListener(CategorySelectListener listener);
	public void removeCategorySelectListener(CategorySelectListener listener);
	
	public void selectMainCategory(List<MainCategory> categories);
	public void selectCategory(List<Category> categories);
}

package mvc.observer.category;

import java.util.List;

import mvc.model.Category;
import mvc.model.MainCategory;

public interface CategorySelectListener {
	public void onSelectMainCategory(List<MainCategory> categories);
	public void onSelectCategory(List<Category> categories);
}

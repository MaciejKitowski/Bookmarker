package mvc.observer.category;

import java.util.List;

import mvc.model.Category;
import mvc.model.MainCategory;

public interface CategorySelectedListener {
	public void onMainCategorySelect(List<MainCategory> categories);
	public void onCategorySelect(List<Category> categories);
}

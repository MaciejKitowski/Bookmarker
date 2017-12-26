package mvc.view.observer.category;

import java.util.List;

import mvc.model.Category;
import mvc.model.MainCategory;

public interface CategorySelectedCaller {
	public void addListener(CategorySelectedListener listener);
	public void removeListener(CategorySelectedListener listener);
	
	public void callListenersMainCategory(List<MainCategory> categories);
	public void callListenersCategory(List<Category> categories);
}

package mvc.observer.category;

import java.util.List;

import mvc.model.Category;
import mvc.model.MainCategory;

public interface CategorySelectedCaller {
	public void addListener(CategorySelectListener listener);
	public void removeListener(CategorySelectListener listener);
	
	public void callListenersMainCategory(List<MainCategory> categories);
	public void callListenersCategory(List<Category> categories);
}

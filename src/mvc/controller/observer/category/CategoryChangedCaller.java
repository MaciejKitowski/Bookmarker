package mvc.controller.observer.category;

@Deprecated
public interface CategoryChangedCaller {
	public void addListener(CategoryChangedListener listener);
	public void removeListener(CategoryChangedListener listener);
	
	public void callListeners();
}

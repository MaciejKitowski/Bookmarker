package mvc.controller.observer.category;

public interface CategoryChangedCaller {
	public void addListener(CategoryChangedListener listener);
	public void removeListener(CategoryChangedListener listener);
}

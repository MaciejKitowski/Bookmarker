package mvc.controller.observer.url;

import java.util.List;

import mvc.model.Url;

public interface UrlChangedCaller {
	public void addListener(UrlChangedListener listener);
	public void removeListener(UrlChangedListener listener);
	
	public void callListeners(List<Url> urls);
}

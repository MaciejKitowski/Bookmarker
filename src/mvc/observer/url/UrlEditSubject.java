package mvc.observer.url;

import java.util.List;

import mvc.model.Url;

public interface UrlEditSubject {
	public void addUrlEditListener(UrlEditListener listener);
	public void removeUrlEditListener(UrlEditListener listener);
	
	public void deleteUrls(List<Url> urls);
}

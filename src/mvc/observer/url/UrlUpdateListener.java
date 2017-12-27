package mvc.observer.url;

import java.util.List;

import mvc.model.Url;

public interface UrlUpdateListener {
	public void onUrlUpdate(List<Url> urls);
}

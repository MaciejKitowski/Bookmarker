package mvc.observer.url;

import java.util.List;

import mvc.model.Url;

public interface UrlSelectListener {
	public void onSelectUrl(List<Url> urls);
	public void onUnselectAll();
}

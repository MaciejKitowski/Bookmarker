package mvc.observer.url;

import java.util.List;

import mvc.model.Url;

public interface UrlEditListener {
	public void onUrlDelete(List<Url> urls);
}

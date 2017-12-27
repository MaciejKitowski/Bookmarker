package mvc.controller.observer.url;

import java.util.List;
import mvc.model.Url;

@Deprecated
public interface UrlChangedListener {
	public void onUrlChanged(List<Url> urls);
}

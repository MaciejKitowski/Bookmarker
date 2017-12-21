package mvc.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Url {
	private Logger log = LoggerFactory.getLogger(Url.class);
	
	private int ID = 0;
	private String url = null;
	private String title = null;
	private String description = null;
	Category category = null;
	
	public Url(int ID, String url, String title, String description, Category category) {
		log.debug("New Url: ID={} url={} title={} description={} category: ID={} name={}", ID, url, title, description, category.getID(), category.getName());
		
		this.ID = ID;
		this.url = url;
		this.title = title;
		this.description = description;
		this.category = category;
	}
	
	public Url(String url, String title, String description, Category category) {
		log.debug("New Url: ID={} url={} title={} description={} category: ID={} name={}", null, url, title, description, category.getID(), category.getName());
		
		this.url = url;
		this.title = title;
		this.description = description;
		this.category = category;
	}
	
	public Url(int ID, String url, String title, Category category) {
		log.debug("New Url: ID={} url={} title={} description={} category: ID={} name={}", ID, url, title, null, category.getID(), category.getName());
		
		this.ID = ID;
		this.url = url;
		this.title = title;
		this.category = category;
	}
	
	public Url(int ID, String url, String title) {
		log.debug("New Url: ID={} url={} title={} description={} category: ID={} name={}", ID, url, title, null, null, null);
		
		this.ID = ID;
		this.url = url;
		this.title = title;
	}
	
	public Url(String url, String title, String description) {
		log.debug("New Url: ID={} url={} title={} description={} category: ID={} name={}", null, url, title, description, null, null);
		
		this.url = url;
		this.title = title;
		this.description = description;
	}
	
	public Url(String url, String title, Category category) {
		log.debug("New Url: ID={} url={} title={} description={} category: ID={} name={}", null, url, title, null, category.getID(), category.getName());
		
		this.url = url;
		this.title = title;
		this.category = category;
	}
	
	public Url(String url, String title) {
		log.debug("New Url: ID={} url={} title={} description={} category: ID={} name={}", null, url, title, null, null, null);
		
		this.url = url;
		this.title = title;
	}
	
	public Url() {
		log.debug("New Url: ID={} url={} title={} description={} category: ID={} name={}", null, null, null, null, null, null);
		
		ID = 0;
		url = "http://localhost";
		title = "EMPTY";
		description = "EMPTY";
		category = new Category();
	}
	
	public void setID(int ID) {
		log.debug("Set ID: {}", ID);
		this.ID = ID;
	}

	public void setUrl(String url) {
		log.debug("Set url: {}", url);
		this.url = url;
	}

	public void setTitle(String title) {
		log.debug("Set title: {}", title);
		this.title = title;
	}

	public void setDescription(String description) {
		log.debug("Set description: {}", description);
		this.description = description;
	}

	public void setCategory(Category category) {
		log.debug("Set category: ID={} name={}", category.getID(), category.getName());
		this.category = category;
	}

	public int getID() {
		log.trace("Get ID: {}", ID);
		return ID;
	}
	
	public String getUrl() {
		log.trace("Get url: {}", url);
		return url;
	}
	
	public String getTitle() {
		log.trace("Get title: {}", title);
		return title;
	}
	
	public String getDescription() {
		log.trace("Get description: {}", description);
		return description;
	}
	
	public Category getCategory() {
		if(category != null) log.trace("Get category: ID={} name={}", category.getID(), category.getName());
		return category;
	}
}

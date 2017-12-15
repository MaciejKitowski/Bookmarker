package mvc.model;

import java.util.logging.Logger;

public final class Url {
	private Logger log = Logger.getLogger(Url.class.getName());
	
	private int ID = 0;
	private String url = null;
	private String title = null;
	private String description = null;
	Category category = null;
	
	public Url(int ID, String url, String title, String description, Category category) {
		log.info(String.format("Create new url: ID=%d, url=%s category: ID=%d name=%s", ID, url, category.getID(), category.getName()));
		this.ID = ID;
		this.url = url;
		this.title = title;
		this.description = description;
		this.category = category;
	}
	
	public Url(String url, String title, String description) {
		log.info(String.format("Create new url: url=%s", url));
		this.url = url;
		this.title = title;
		this.description = description;
	}
	
	public Url() {
		log.info("Create empty url");
		ID = 0;
		url = "http://localhost";
		title = "EMPTY";
		description = "EMPTY";
		category = new Category();
	}

	public int getID() {
		return ID;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setID(int ID) {
		log.info("Set new ID: " + ID);
		this.ID = ID;
	}

	public void setUrl(String url) {
		log.info("Set new url: " + url);
		this.url = url;
	}

	public void setTitle(String title) {
		log.info("Set new title: " + title);
		this.title = title;
	}

	public void setDescription(String description) {
		log.info("Set new description: " + description);
		this.description = description;
	}

	public void setCategory(Category category) {
		log.info(String.format("Set new category: ID=%d name=%s", category.getID(), category.getName()));
		this.category = category;
	}
}

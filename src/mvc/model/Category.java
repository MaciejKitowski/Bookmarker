package mvc.model;

import java.util.logging.Logger;

public final class Category {
	private Logger log = Logger.getLogger(Category.class.getName());
	
	private int ID = 0;
	private String name = null;
	private MainCategory parent = null;
	
	public Category(int ID, String name, MainCategory parent) {
		log.info(String.format("New Category \n\t ID=%d \n\t name=%s \n\t parent: \n\t\t ID=%d \n\t\t name=%s", ID, name, parent.getID(), parent.getName()));
		this.ID = ID;
		this.name = name;
		this.parent = parent;
	}
	
	public Category(int ID, String name) {
		log.info(String.format("New Category \n\t ID=%d \n\t name=%s \n\t parent: \n\t\t ID=%d \n\t\t name=%s", ID, name, null, null));
		this.ID = ID;
		this.name = name;
	}
	
	public Category(String name) {
		log.info(String.format("New Category \n\t ID=%d \n\t name=%s \n\t parent: \n\t\t ID=%d \n\t\t name=%s", null, name, null, null));
		this.name = name;
	}
	
	public Category() {
		log.info(String.format("New Category \n\t ID=%d \n\t name=%s \n\t parent: \n\t\t ID=%d \n\t\t name=%s", null, null, null, null));
		ID = 0;
		name = "EMPTY";
		parent = new MainCategory();
	}
	
	public void setID(int ID) {
		log.info("Set new ID: " + ID);
		this.ID = ID;
	}
	
	public void setName(String name) {
		log.info("Set new name: " + name);
		this.name = name;
	}
	
	public void setParent(MainCategory parent) {
		log.info(String.format("Set new parent: ID=%d, name=%s", parent.getID(), parent.getName()));
		this.parent = parent;
	}
	
	public int getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public MainCategory getParent() {
		return parent;
	}
}

package mvc.model;

import java.util.logging.Logger;

public final class MainCategory {
	private static final String LoggerFormat = "MainCat(%d)";
	private Logger log = Logger.getLogger(MainCategory.class.getName());
	
	private int ID = 0;
	private String name = null;
	
	public MainCategory(int ID, String name) {
		log.info(String.format("Create new category: ID=%d, name=%s", ID, name));
		this.ID = ID;
		this.name = name;
		updateLogger();
	}
	
	public MainCategory(String name) {
		log.info("Create category without ID: " + name);
		this.name = name;
	}
	
	public MainCategory() {
		log.info("Create empty category");
		ID = 0;
		name = "EMPTY";
		updateLogger();
	}
	
	public void setID(int ID) {
		log.info("Set new ID: " + ID);
		this.ID = ID;
		updateLogger();
	}
	
	public void setName(String name) {
		log.info("Set new name: " + name);
		this.name = name;
	}
	
	public int getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	private void updateLogger() {
		log = Logger.getLogger(String.format(LoggerFormat, ID));
	}
}

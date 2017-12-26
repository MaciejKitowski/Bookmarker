package mvc.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MainCategory {
	private Logger log = LoggerFactory.getLogger(MainCategory.class);
	
	private int ID = 0;
	private String name = null;
	
	public MainCategory(int ID, String name) {
		log.debug("New category: ID={} name={}", ID, name);
			
		this.ID = ID;
		this.name = name;
	}
	
	public MainCategory(String name) {
		log.debug("New category: ID={} name={}", null, name);
		
		this.name = name;
	}
	
	public MainCategory() {
		log.debug("New category: ID={} name={}", null, null);
		
		ID = 0;
		name = "EMPTY";
	}
	
	public void setID(int ID) {
		log.debug("Set ID: {}", ID);
		this.ID = ID;
	}
	
	public void setName(String name) {
		log.debug("Set name: {}", name);
		this.name = name;
	}
	
	public int getID() {
		log.trace("Get ID: {}", ID);
		return ID;
	}
	
	public String getName() {
		log.trace("Get name: {}", name);
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

package mvc.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Subcategory {
	private Logger log = LoggerFactory.getLogger(Subcategory.class);
	
	private int ID = 0;
	private String name = null;
	private MainCategory parent = null;
	
	public Subcategory(int ID, String name, MainCategory parent) {
		log.debug("New subcategory: ID={} name={} parent: ID={} name={}", ID, name, parent.getID(), parent.getName());
		
		this.ID = ID;
		this.name = name;
		this.parent = parent;
	}
	
	public Subcategory(int ID, String name) {
		log.debug("New subcategory: ID={} name={} parent: ID={} name={}", ID, name, null, null);
		
		this.ID = ID;
		this.name = name;
	}
	
	public Subcategory(String name, MainCategory parent) {
		log.debug("New subcategory: ID={} name={} parent: ID={} name={}", null, name, parent.getID(), parent.getName());

		this.name = name;
		this.parent = parent;
	}
	
	public Subcategory(String name) {
		log.debug("New subcategory: ID={} name={} parent: ID={} name={}", null, name, null, null);

		this.name = name;
	}
	
	public Subcategory() {
		log.debug("New subcategory: ID={} name={} parent: ID={} name={}", null, null, null, null);

		ID = 0;
		name = "EMPTY";
		parent = new MainCategory();
	}
	
	public void setID(int ID) {
		log.debug("Set ID: {}", ID);
		this.ID = ID;
	}
	
	public void setName(String name) {
		log.debug("Set name: {}", name);
		this.name = name;
	}
	
	public void setParent(MainCategory parent) {
		log.debug("Set parent: ID={} name={}", parent.getID(), parent.getName());
		this.parent = parent;
	}
	
	public int getID() {
		log.trace("Get ID: {}", ID);
		return ID;
	}
	
	public String getName() {
		log.trace("Get name: {}", name);
		return name;
	}
	
	public MainCategory getParent() {
		if(parent != null) log.trace("Get parent: ID={} name={}", parent.getID(), parent.getName());
		return parent;
	}
	
	@Override
	public String toString() {
		return name;
	}
}

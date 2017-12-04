package mvc.model;

import java.util.logging.Logger;

public final class Profile {
	private static final Logger log = Logger.getLogger(Profile.class.getName());
	
	private int ID;
	private String name;
	
	public Profile(int ID, String name) {
		log.info(String.format("Create profile with ID= %d, name= %s", ID, name));
		
		this.ID = ID;
		this.name = name;
	}
	
	public void setName(String name) {
		log.info(String.format("Set new name for profile(ID= %d): %s", ID, name));
		this.name = name;
	}
	
	public void setID(int ID) {
		log.info(String.format("Set new ID for profile(name= %s): %d", name, ID));
		this.ID = ID;
	}
	
	public String getName() {
		return name;
	}
	
	public long getID() {
		return ID;
	}
}

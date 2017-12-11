package mvc.dao.postgres;

import java.util.List;
import java.util.logging.Logger;

import mvc.dao.model_interfaces.IProfileDAO;
import mvc.model.Profile;

public final class PostgresProfile implements IProfileDAO {
	private static final Logger log = Logger.getLogger(PostgresProfile.class.getName());
	
	
	private static final String CREATE_TABLE = "";
	
	private static final String INSERT = "";
	
	private static final String GET = "";
	
	private static final String GET_ALL = "";
	
	private static final String UPDATE = "";
	
	private static final String DELETE = "";
	
	
	
	/*
	 * private static final String CREATE_TABLE = 
	"CREATE TABLE profile( \n" +
			"ID 	INTEGER 	PRIMARY KEY AUTOINCREMENT, \n" +
			"name	VARCHAR(255) NOT NULL \n" + 
	")";
	
	private static final String INSERT = "INSERT INTO profile(name) VALUES(?)";
	
	private static final String GET = "SELECT ID, name FROM profile WHERE id = ?";
	
	private static final String GET_ALL = "SELECT ID, name FROM profile";
	
	private static final String UPDATE = "UPDATE profile SET name=? WHERE id = ?";
			
	private static final String DELETE = "DELETE FROM profile WHERE id = ?";
	 */

	@Override
	public void createTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insert(Profile profile) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Profile get(int ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Profile> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Profile profile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int ID) {
		// TODO Auto-generated method stub
		return false;
	}

}

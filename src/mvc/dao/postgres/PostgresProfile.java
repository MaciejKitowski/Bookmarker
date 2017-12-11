package mvc.dao.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import mvc.dao.model_interfaces.IProfileDAO;
import mvc.dao.sqlite.SqliteFactory;
import mvc.model.Profile;

public final class PostgresProfile implements IProfileDAO {
	private static final Logger log = Logger.getLogger(PostgresProfile.class.getName());
	
	public static int INSERT_FAIL = -1;
	
	private static final String CREATE_TABLE = 
		"CREATE TABLE profile( \n" +
				"ID 	SERIAL 	PRIMARY KEY," +
				"name 	VARCHAR(255) 	NOT NULL \n" +
		")";
	
	private static final String INSERT = "INSERT INTO profile(name) VALUES(?)";
	
	private static final String GET = "SELECT ID, name FROM profile WHERE id = ?";
	
	private static final String GET_ALL = "SELECT ID, name FROM profile";
	
	private static final String UPDATE = "";
	
	private static final String DELETE = "";
	
	
	
	/*
	
	private static final String GET_ALL = "SELECT ID, name FROM profile";
	
	private static final String UPDATE = "UPDATE profile SET name=? WHERE id = ?";
			
	private static final String DELETE = "DELETE FROM profile WHERE id = ?";
	 */
	
	@Override
	public void createTable() {
		log.info("Create table");
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = PostgresFactory.getConnection();
			statement = connection.createStatement();
			
			statement.execute(CREATE_TABLE);
			
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
	}
	
	@Override
	public int insert(Profile profile) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		int resultBuffer = 0;
		
		try {
			connection = PostgresFactory.getConnection();
			statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, profile.getName());
			statement.execute();
			
			result = statement.getGeneratedKeys();
			if(result != null && result.next()) resultBuffer = result.getInt(1);
			else resultBuffer = INSERT_FAIL;
			
			result.close();
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
			resultBuffer = INSERT_FAIL;
		}
		
		return resultBuffer;
	}

	@Override
	public Profile get(int ID) {
		Profile profile = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			connection = SqliteFactory.getConnection();
			statement = connection.prepareStatement(GET);
			
			statement.setInt(1, ID);
			statement.execute();
			
			result = statement.getResultSet();
			if(result != null && result.next()) {
				int foundID = result.getInt(1);
				String foundName = result.getString(2);
				
				profile = new Profile(foundID, foundName);
			}
			
			result.close();
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
		
		return profile;
	}

	@Override
	public List<Profile> getAll() {
		List<Profile> profiles = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = PostgresFactory.getConnection();
			statement = connection.createStatement();
			
			result = statement.executeQuery(GET_ALL);
			if(result != null) {
				while(result.next()) {
					int foundID = result.getInt(1);
					String foundName = result.getString(2);
					
					profiles.add(new Profile(foundID, foundName));
				}
			}
			
			result.close();
			statement.close();
			connection.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
		
		return profiles;
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

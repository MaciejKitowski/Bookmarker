package mvc.dao.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import mvc.dao.model_interfaces.IProfileDAO;
import mvc.model.Profile;

public final class SqliteProfile implements IProfileDAO {
	private static final Logger log = Logger.getLogger(SqliteProfile.class.getName());
	
	public static int INSERT_FAIL = -1;
	
	private static final String CREATE_TABLE = 
	"CREATE TABLE profile( \n" +
			"ID 	INTEGER 	PRIMARY KEY AUTOINCREMENT, \n" +
			"name	VARCHAR(255) NOT NULL \n" + 
	")";
	
	private static final String INSERT = "INSERT INTO profile(name) VALUES(?)";
	

			
	
	
	
	/*
	private static final String CREATE_QUERY = 
			"INSERT INTO klient(imie, nazwisko, miasto, rok_urodzenia)  VALUES (?,?,?,?)";
    private static final String READ_QUERY = 
    		"SELECT id, imie, nazwisko, miasto, rok_urodzenia FROM klient WHERE id = ?";
    private static final String READALL_QUERY = 
    		"SELECT id, imie, nazwisko, miasto, rok_urodzenia FROM klient";
    private static final String UPDATE_QUERY = 
    		"UPDATE klient SET imie=?, nazwisko=?, miasto=?,rok_urodzenia=?  WHERE id = ?";
    private static final String DELETE_QUERY = 
    		"DELETE FROM klient WHERE id = ?";
	 */
	
	@Override
	public void createTable() {
		log.info("Create table");
		
		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = SqliteFactory.getConnection();
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
			connection = SqliteFactory.getConnection();
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

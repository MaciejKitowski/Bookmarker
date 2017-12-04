package mvc.dao.sqlite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import mvc.dao.model_interfaces.IProfileDAO;
import mvc.model.Profile;

public final class SqliteProfile implements IProfileDAO {
	private static final Logger log = Logger.getLogger(SqliteProfile.class.getName());
	
	private static final String CREATE_TABLE = 
	"CREATE TABLE profile( \n" +
			"ID 	INTEGER 	PRIMARY KEY 	AUTOINCREMENT, \n" +
			"name	VARCHaR(255)	NOT NULL, \n" + 
	")";
	
	
	
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
	private static final String CREATE_TABLE = 
			"CREATE TABLE klient(id INTEGER PRIMARY KEY AUTOINCREMENT,imie CHAR(50), nazwisko CHAR(50), miasto CHAR(50), rok_urodzenia INT)";
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
			
			connection.close();
			statement.close();
		}
		catch(Exception ex) {
			log.warning(ex.getMessage());
		}
	}

	@Override
	public int create(Profile profile) {
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

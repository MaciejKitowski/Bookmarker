package mvc.dao.model_interfaces;

import java.util.List;

import mvc.model.Profile;

public interface IProfileDAO {
	public int createTable();
	public int create(Profile profile);
	
	public Profile get(int ID);
	public List<Profile> getAll();
	
	public boolean update(Profile profile);
	public boolean delete(int ID);
}

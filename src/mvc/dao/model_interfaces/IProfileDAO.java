package mvc.dao.model_interfaces;

import java.util.List;

import mvc.model.Profile;

public interface IProfileDAO {
	public void createTable();
	public int insert(Profile profile);
	
	public Profile get(int ID);
	public List<Profile> getAll();
	
	public boolean update(Profile profile);
	public boolean delete(int ID);
}

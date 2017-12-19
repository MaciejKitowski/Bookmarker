package mvc.dao.model;

import java.util.List;

import mvc.model.Category;
import mvc.model.Url;

public interface IUrlDAO {
	public void createTable();
	public int insert(Url url);
	
	public Url get(int ID);
	public List<Url> getAllWithCategory(Category category);
	public List<Url> getAll();
	
	public boolean update(Url url);
	public boolean delete(int ID);
}

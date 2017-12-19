package mvc.dao.model;

import java.util.List;

import mvc.model.MainCategory;

public interface IMainCategoryDAO {
	public void createTable();
	public int insert(MainCategory category);
	
	public MainCategory get(int ID);
	public List<MainCategory> getAll();
	
	public boolean update(MainCategory category);
	public boolean delete(int ID);
}

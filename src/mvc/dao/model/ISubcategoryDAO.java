package mvc.dao.model;

import java.util.List;

import mvc.model.Category;
import mvc.model.MainCategory;

public interface ISubcategoryDAO {
	public int INSERT_FAIL = -1;
	
	public void createTable();
	public int insert(Category category);
	
	public Category get(int ID);
	public List<Category> getWithMainCategory(MainCategory category);
	public List<Category> getAll();
	
	public boolean update(Category category);
	public boolean delete(int ID);
}

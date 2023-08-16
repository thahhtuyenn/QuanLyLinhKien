package service;

import java.util.List;

import dao.CategoryProductDAO;
import entity.CategoryEntity;

public class CategoryProductService {
	private CategoryProductDAO categoryProductDAO;
	public CategoryProductService() {
		categoryProductDAO = new CategoryProductDAO();
	}
	public CategoryEntity findById(int id) {
		return categoryProductDAO.findById(id);
	}
	public List<CategoryEntity> findAll(){
		return categoryProductDAO.findAll();
	}
	
	public CategoryEntity insertOne(CategoryEntity entity) {
		return categoryProductDAO.insertOne(entity);
	}
	
	public int updateOne(CategoryEntity entity) {
		return categoryProductDAO.updateOne(entity);
	}
	
	
	public int removeOne(int categoryId) {
		return categoryProductDAO.removeOne(categoryId);
	}
	
	public List<CategoryEntity> findByCategoryName(String CategoryNameSearch){
		return categoryProductDAO.findByCategoryName(CategoryNameSearch);
	}
	
}

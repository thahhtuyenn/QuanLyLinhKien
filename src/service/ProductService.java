package service;

import java.util.List;

import dao.ProductDAO;
import entity.ProductEntity;

public class ProductService {
	private ProductDAO productDAO;

	public ProductService() {
		productDAO = new ProductDAO();
	}

	public ProductEntity findById(int id) {
		return productDAO.findById(id);
	}

	public List<ProductEntity> findAll() {
		return productDAO.findAll();
	}

	public int removeOne(int productId) {
		return productDAO.removeOne(productId);

	}

	public int updateOne(ProductEntity product) {
		return productDAO.updateOne(product);
	}

	public ProductEntity insertOne(ProductEntity product) {
		return productDAO.insertOne(product);
	}

	public List<ProductEntity> findByNameAndTotalDue(String NameSearch, Double TotalDueFrom, Double TotalDueTo) {
		List<ProductEntity> list = productDAO.findByNameAndTotalDue(NameSearch, TotalDueFrom, TotalDueTo);
		return list;
	}

	public Integer count() {
		return productDAO.count();
	}
}

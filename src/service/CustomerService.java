package service;

import java.util.List;

import dao.CustomerDAO;
import dao.EmployeeDAO;
import entity.CustomerEntity;
import entity.EmployeeEntity;
import utils.PasswordUtils;

public class CustomerService {
	private CustomerDAO customerDAO;

	public CustomerService() {
		customerDAO = new CustomerDAO();
	}

	public List<CustomerEntity> findAll() {
		return customerDAO.findAll();
	}

	public CustomerEntity insert(CustomerEntity entity) {
		if (entity != null) {
			return customerDAO.insertOne(entity);
		}
		return null;
	}

	public int remove(int id) {
		return customerDAO.removeOne(id);
	}

	public int updateOne(CustomerEntity entityNew) {
		return customerDAO.updateOne(entityNew);
	}

	public List<CustomerEntity> findByFullNameAndAddress(String fullName, String address) {
		return customerDAO.findByFullNameAndAddress(fullName, address);
	}

	public CustomerEntity findById(int customerId) {
		return customerDAO.findById(customerId);
	}

	public Integer count() {
		return customerDAO.count();
	}
}

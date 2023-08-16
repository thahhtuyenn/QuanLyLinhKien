package service;

import java.sql.Date;
import java.util.List;

import dao.EmployeeDAO;
import dao.RoleDAO;
import entity.EmployeeEntity;
import entity.RoleEntity;
import utils.PasswordUtils;

public class EmployeeService {
	private EmployeeDAO employeeDAO;

	public EmployeeService() {
		employeeDAO = new EmployeeDAO();
	}

	public List<EmployeeEntity> findAll() {
		return employeeDAO.findAll();
	}

	public EmployeeEntity insert(EmployeeEntity entity) {
		if (entity != null) {
			entity.setPassword(PasswordUtils.toSHA1(entity.getPassword()));
			return employeeDAO.insertOne(entity);
		}
		return null;
	}

	public int remove(int id) {
		return employeeDAO.removeOne(id);
	}

	public int updateOne(EmployeeEntity entityNew) {
		entityNew.setPassword(PasswordUtils.toSHA1(entityNew.getPassword()));
		System.out.println(entityNew);
		return employeeDAO.updateOne(entityNew);

	}

	public EmployeeEntity findById(int employeeId) {
		return employeeDAO.findById(employeeId);
	}

	public List<EmployeeEntity> findByFullNameAndStartDateAndEndDate(String fullNameSearch, Date startDate,
			Date endDate) {
		List<EmployeeEntity> list = employeeDAO.findByFullNameAndStartDateAndEndDate(fullNameSearch, startDate,
				endDate);
		return list;

	}

	public EmployeeEntity findByUserNameAndPassword(String userName, String password) {
		password = PasswordUtils.toSHA1(password);
		return employeeDAO.findByUserNameAndPassword(userName, password);
	}

	public int updatePasswordByUserName(String userName, String newPassword) {
		int status = employeeDAO.updatePasswordByUserName(userName, newPassword);
		return status;
	}

	public Integer count() {
		return employeeDAO.count();
	}

	public EmployeeEntity findByUserNameAndPasswordAndStatus(String userName, String password, int status) {
		password = PasswordUtils.toSHA1(password);
		return employeeDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

	public List<EmployeeEntity> findByRoldId(int roleId) {

		return employeeDAO.findByRoldId(roleId);
	}

	public List<EmployeeEntity> findByFullNameAndRoleId(String name, int roleId) {
		// TODO Auto-generated method stub
		return employeeDAO.findByFullNameAndRoleId(name, roleId);
	}
}

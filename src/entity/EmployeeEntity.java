package entity;

import java.sql.Date;

public class EmployeeEntity {
	private int employeeId;
	private String fullName;
	private Date birthDate;
	private String address;
	private String phone;
	private int status;
	private String userName;
	private String password;
	private int roleId;

	public EmployeeEntity() {

	}
	

	public EmployeeEntity(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	public EmployeeEntity(int employeeId, String fullName, Date birthDate, String address, String phone, int status,
			String userName, String password, int roleId) {
		this.employeeId = employeeId;
		this.fullName = fullName;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.status = status;
		this.userName = userName;
		this.password = password;
		this.roleId = roleId;
	}

	public EmployeeEntity(String fullName, Date birthDate, String address, String phone, int status, String userName,
			String password, int roleId) {
		this.fullName = fullName;
		this.birthDate = birthDate;
		this.address = address;
		this.phone = phone;
		this.status = status;
		this.userName = userName;
		this.password = password;
		this.roleId = roleId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [employeeId=" + employeeId + ", fullName=" + fullName + ", birthDate=" + birthDate
				+ ", address=" + address + ", phone=" + phone + ", status=" + status + ", userName=" + userName
				+ ", password=" + password + ", roleId=" + roleId + "]";
	}

}

package entity;

public class CustomerEntity {

	private int customerId;
	private String fullName;
	private String address;
	private String phone;
	private int status;
	public CustomerEntity() {
	}
	public CustomerEntity(String fullName, String address, String phone, int status) {
		this.fullName = fullName;
		this.address = address;
		this.phone = phone;
		this.status = status;
		
	}

	public CustomerEntity(int customerId, String fullName, String address, String phone, int status) {
		this.customerId = customerId;
		this.fullName = fullName;
		this.address = address;
		this.phone = phone;
		this.status = status;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
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
	@Override
	public String toString() {
		return "CustomerEntity [customerId=" + customerId + ", fullName=" + fullName + ", address=" + address
				+ ", phone=" + phone + ", status=" + status + "]";
	}
	
}

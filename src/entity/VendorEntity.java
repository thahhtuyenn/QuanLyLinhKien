package entity;

public class VendorEntity {
	private int vendorId;
	private String vendorName;
	private String address;
	private String phone;
	private String fax;

	public VendorEntity() {
	}

	public VendorEntity(int vendorId, String vendorName, String address, String phone, String fax) {
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
	}
	
	public VendorEntity(String vendorName, String address, String phone, String fax) {
		this.vendorName = vendorName;
		this.address = address;
		this.phone = phone;
		this.fax = fax;
	}


	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Override
	public String toString() {
		return "VendorEntity [vendorId=" + vendorId + ", vendorName=" + vendorName + ", address=" + address + ", phone="
				+ phone + ", fax=" + fax + "]";
	}

	
}

package entity;

public class BaseEntity {
	private int salesOrderID;
	private String employeeName;
	private String customerNameOrVendorName;
	private String vendorName;
	private String productName;
	private int quantityProduct;
	private String orderDate;
	private double priceProduct;
	private double totalPrice;
	private double totalDue;

	public BaseEntity(int salesOrderID, String employeeName, String customerNameOrVendorName, String productName,
			int quantityProduct, String orderDate, double priceProduct, double totalPrice, double totalDue) {
		this.salesOrderID = salesOrderID;
		this.employeeName = employeeName;
		this.setCustomerNameOrVendorName(customerNameOrVendorName);
		this.productName = productName;
		this.quantityProduct = quantityProduct;
		this.priceProduct = priceProduct;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.setTotalDue(totalDue);

	}

	public int getSalesOrderID() {
		return salesOrderID;
	}

	public void setSalesOrderID(int salesOrderID) {
		this.salesOrderID = salesOrderID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantityProduct() {
		return quantityProduct;
	}

	public void setQuantityProduct(int quantityProduct) {
		this.quantityProduct = quantityProduct;
	}

	public double getPriceProduct() {
		return priceProduct;
	}

	public void setPriceProduct(double priceProduct) {
		this.priceProduct = priceProduct;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "BaseEntity [salesOrderID=" + salesOrderID + ", employeeName=" + employeeName + ", customerNameOrVendorName="
				+ customerNameOrVendorName + ", productName=" + productName + ", quantityProduct=" + quantityProduct
				+ ", priceProduct=" + priceProduct + ", totalPrice=" + totalPrice + "]";
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(double totalDue) {
		this.totalDue = totalDue;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getCustomerNameOrVendorName() {
		return customerNameOrVendorName;
	}

	public void setCustomerNameOrVendorName(String customerNameOrVendorName) {
		this.customerNameOrVendorName = customerNameOrVendorName;
	}

}

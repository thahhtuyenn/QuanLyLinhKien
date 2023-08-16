package entity;

public class ProductEntity {
	private int productId;
	private int categoryId;
	private String name;
	private double price;
	private int quantity;
	private int status;
	
	
	public ProductEntity(int categoryId, String name, double price, int quantity, int status) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
	}
	public ProductEntity(int productId, int categoryId, String name, double price, int quantity, int status) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
	}
	public ProductEntity() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ProductEntity [productId=" + productId + ", categoryId=" + categoryId + ", name=" + name + ", price="
				+ price + ", quantity=" + quantity + ", status=" + status + "]";
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	

}

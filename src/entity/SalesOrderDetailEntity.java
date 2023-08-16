package entity;

public class SalesOrderDetailEntity {
	private int salesOrderDetailId;
	private int salesOrderId;
	private int productId;
	private int orderQuantity;
	private double subTotal;
	private ProductEntity product;

	public SalesOrderDetailEntity() {
	}

	public SalesOrderDetailEntity(int productId, int orderQuantity, double subTotal, ProductEntity product) {
		this.productId = productId;
		this.orderQuantity = orderQuantity;
		this.subTotal = subTotal;
		this.setProduct(product);
	}

	public SalesOrderDetailEntity(int salesOrderDetailId, int salesOrderId, int productId, int orderQuantity,
			double subTotal) {
		this.salesOrderDetailId = salesOrderDetailId;
		this.salesOrderId = salesOrderId;
		this.productId = productId;
		this.orderQuantity = orderQuantity;
		this.subTotal = subTotal;
	}

	public int getSalesOrderDetailId() {
		return salesOrderDetailId;
	}

	public void setSalesOrderDetailId(int salesOrderDetailId) {
		this.salesOrderDetailId = salesOrderDetailId;
	}

	public int getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(int salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

}

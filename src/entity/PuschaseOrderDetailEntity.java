package entity;

public class PuschaseOrderDetailEntity {
	private int puschaseOrderDetailId;
	private int puschaseOrderId;
	private int productId;
	private int orderQuantity;
	private double subTotal;
	private ProductEntity product;
	public PuschaseOrderDetailEntity() {
	}

	public PuschaseOrderDetailEntity(int puschaseOrderDetailId, int puschaseOrderId, int productId, int orderQuantity,
			double subTotal) {
		this.puschaseOrderDetailId = puschaseOrderDetailId;
		this.puschaseOrderId = puschaseOrderId;
		this.productId = productId;
		this.orderQuantity = orderQuantity;
		this.subTotal = subTotal;
	}
	public PuschaseOrderDetailEntity(int productId, int orderQuantity, double subTotal, ProductEntity product) {
		this.productId = productId;
		this.orderQuantity = orderQuantity;
		this.subTotal = subTotal;
		this.setProduct(product);
	}

	public int getPuschaseOrderDetailId() {
		return puschaseOrderDetailId;
	}

	public void setPuschaseOrderDetailId(int puschaseOrderDetailId) {
		this.puschaseOrderDetailId = puschaseOrderDetailId;
	}

	public int getPuschaseOrderId() {
		return puschaseOrderId;
	}

	public void setPuschaseOrderId(int puschaseOrderId) {
		this.puschaseOrderId = puschaseOrderId;
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

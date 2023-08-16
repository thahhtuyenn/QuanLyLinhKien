package entity;

import java.sql.Date;

public class SalesOrderEntity {
	private int salesOrderID;
	private int employeeId;
	private int customerId;
	private int promotionId;
	private Date orderDate;
	private String orderTime;
	private double totalDue;
	
	public SalesOrderEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public SalesOrderEntity(int employeeId, int customerId, int promotionId, double totalDue) {
		this.employeeId = employeeId;
		this.customerId = customerId;
		this.promotionId = promotionId;
		this.totalDue = totalDue;
	}

	public SalesOrderEntity(int salesOrderID, int employeeId, int customerId, int promotionId, Date orderDate,
			String orderTime, double totalDue) {
		super();
		this.salesOrderID = salesOrderID;
		this.employeeId = employeeId;
		this.customerId = customerId;
		this.promotionId = promotionId;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.totalDue = totalDue;
	}

	public int getSalesOrderID() {
		return salesOrderID;
	}

	public void setSalesOrderID(int salesOrderID) {
		this.salesOrderID = salesOrderID;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public double getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(double totalDue) {
		this.totalDue = totalDue;
	}

	@Override
	public String toString() {
		return "SalesOrderEntity [salesOrderID=" + salesOrderID + ", employeeId=" + employeeId + ", customerId="
				+ customerId + ", promotionId=" + promotionId + ", orderDate=" + orderDate + ", orderTime=" + orderTime
				+ ", totalDue=" + totalDue + "]";
	}
	

}

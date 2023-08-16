package entity;

import java.sql.Date;

public class PuschaseOrderEntity {
	private int puschaseOrderId;
	private int vendorId;
	private int employeeId;
	private Date orderDate;
	private String orderTime;
	private double totalDue;

	public PuschaseOrderEntity() {
	}
	public PuschaseOrderEntity(int vendorId, int employeeId, double totalDue) {
		this.vendorId = vendorId;
		this.employeeId = employeeId;
		this.totalDue = totalDue;
	}

	public PuschaseOrderEntity(int puschaseOrderId, int vendorId, int employeeId, Date orderDate, String orderTime,
			double totalDue) {
		this.puschaseOrderId = puschaseOrderId;
		this.vendorId = vendorId;
		this.employeeId = employeeId;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.totalDue = totalDue;
	}

	public int getPuschaseOrderId() {
		return puschaseOrderId;
	}

	public void setPuschaseOrderId(int puschaseOrderId) {
		this.puschaseOrderId = puschaseOrderId;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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
		return "PuschaseOrderEntity [puschaseOrderId=" + puschaseOrderId + ", vendorId=" + vendorId + ", employeeId="
				+ employeeId + ", orderDate=" + orderDate + ", orderTime=" + orderTime + ", totalDue=" + totalDue + "]";
	}
	

}

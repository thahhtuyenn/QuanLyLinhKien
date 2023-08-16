package service;

import java.util.List;

import dao.SalesOrderDetailDAO;
import entity.SalesOrderDetailEntity;

public class SalesOrderDetailService {
	private SalesOrderDetailDAO salesOrderDetailDAO;
	public SalesOrderDetailService() {
		salesOrderDetailDAO = new SalesOrderDetailDAO();
	}
	
	public List<SalesOrderDetailEntity> findByOrderId(int orderId) {
		return salesOrderDetailDAO.findByOrderId(orderId);
	}
}

	package service;

import java.util.List;

import dao.PurchaseOrderDetailDAO;
import entity.PuschaseOrderDetailEntity;

public class PurchaseOrderDetailService {
	private PurchaseOrderDetailDAO purchaseOrderDetailsDao;
	
	public PurchaseOrderDetailService() {
		purchaseOrderDetailsDao = new PurchaseOrderDetailDAO();
	}
	
	public List<PuschaseOrderDetailEntity> findByOrderId(int id){
		return purchaseOrderDetailsDao.findByOrderId(id);
	}
}

package service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import dao.PurchasingOrderDAO;
import entity.BaseEntity;
import entity.PuschaseOrderDetailEntity;
import entity.PuschaseOrderEntity;

public class PurchasingOrderService {
	private PurchasingOrderDAO purchasingOrderDao;

	public PurchasingOrderService() {
		purchasingOrderDao = new PurchasingOrderDAO();
	}

	public PuschaseOrderEntity findOne(int id) {
		return purchasingOrderDao.findById(id);
	}
	public List<PuschaseOrderEntity> findAll() {
		return purchasingOrderDao.findAll();
	}

	public List<PuschaseOrderEntity> findByOrderStartDateAndEndDate(Date startDate, Date endDate) {
		return purchasingOrderDao.findByOrderStartDateAndEndDate(startDate, endDate);
	}

	public List<PuschaseOrderEntity> findByTotalDueStartAndTotalDueEnd(Double startPrice, Double endPrice) {
		return purchasingOrderDao.findByTotalDueStartAndTotalDueEnd(startPrice, endPrice);
	}

	public List<PuschaseOrderEntity> findByDateAndPrice(Date startDate, Date endDate, Double startPrice,
			Double endPrice) {
		return purchasingOrderDao.findByDateAndPrice(startDate, endDate, startPrice, endPrice);
	}

	public List<BaseEntity> statisticalAll(Map<Object, Object> searchs) {
		return purchasingOrderDao.statisticalAll(searchs);
	}
	public Integer insertOne(PuschaseOrderEntity puschaseOrderEntity, List<PuschaseOrderDetailEntity> listDetailEntity) {
		return purchasingOrderDao.insertOne(puschaseOrderEntity, listDetailEntity);
	}
}

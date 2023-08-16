package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.SalesOrderDAO;
import dao.SalesOrderDetailDAO;
import entity.BaseEntity;
import entity.SalesOrderDetailEntity;
import entity.SalesOrderEntity;

public class SalesOrderService {
	private SalesOrderDAO salesOrderDao;

	public SalesOrderService() {
		salesOrderDao = new SalesOrderDAO();
	}

	public SalesOrderEntity findOne(int id) {
		return salesOrderDao.findById(id);
	}

	public List<SalesOrderEntity> findAll() {
		return salesOrderDao.findAll();
	}

	public SalesOrderEntity insertOne(SalesOrderEntity salesOrder) {
		if (salesOrder != null) {
			return salesOrderDao.insertOne(salesOrder);
		}
		return null;
	}

	public List<SalesOrderEntity> findByStartDateAndEndDateAndPriceFromAndPriceTo(Date startDate, Date endDate,
			Double priceFrom, Double priceTo) {
		if ((startDate != null && endDate != null) && (priceFrom != null && priceTo != null))
			return salesOrderDao.findByStartDateAndEndDateAndPriceFromAndPriceTo(startDate, endDate, priceFrom,
					priceTo);
		else if ((startDate != null && endDate != null) && (priceFrom == null && priceTo == null))
			return salesOrderDao.findByStartDateAndEndDate(startDate, endDate);
		else
			return salesOrderDao.findByPriceFromAndPriceTo(priceFrom, priceTo);

	}

	public Integer insertOne(SalesOrderEntity salesOrderEntity, List<SalesOrderDetailEntity> listDetailEntities) {
		return salesOrderDao.insertOne(salesOrderEntity, listDetailEntities);
	}

	public List<BaseEntity> statisticalAll(Map<Object, Object> searchs) {
		return salesOrderDao.statisticalAll(searchs);
	}
}

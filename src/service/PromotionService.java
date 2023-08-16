package service;

import java.util.List;

import dao.PromotionDAO;
import entity.PromotionEntity;

public class PromotionService {
	private PromotionDAO promotionDAO;
	public PromotionService() {
		promotionDAO = new PromotionDAO();
	}
	
	public PromotionEntity findById(int promotionId) {
		return promotionDAO.findById(promotionId);
	}
	
	public List<PromotionEntity> findAllPromotion() {
		return promotionDAO.findAllPromotion();
	}
	
	public List<PromotionEntity> findByName(String promotionName) {
		return promotionDAO.findByName(promotionName);
	}
	
	public List<PromotionEntity> findAll() {
		return promotionDAO.findAll();
	}
	
	public PromotionEntity insertOne(PromotionEntity promotion) {
		return promotionDAO.insertOne(promotion);
	}
	
	public int removeOne(int promotionId) {
		return promotionDAO.removeOne(promotionId);
	}
	
	public int updateOne(PromotionEntity promotion) {
		return promotionDAO.updateOne(promotion);
	}
}

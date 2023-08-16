package entity;

import java.sql.Date;

public class PromotionEntity {
	private int promotionId;
	private String promotionName;
	private double promotionPercent;
	private Date startDate;
	private Date endDate;

	public PromotionEntity() {
	}

	public PromotionEntity(int promotionId, String promotionName, double promotionPercent, Date startDate,
			Date endDate) {
		this.promotionId = promotionId;
		this.promotionName = promotionName;
		this.promotionPercent = promotionPercent;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	

	public PromotionEntity(String promotionName, double promotionPercent, Date startDate, Date endDate) {
		this.promotionName = promotionName;
		this.promotionPercent = promotionPercent;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public double getPromotionPercent() {
		return promotionPercent;
	}

	public void setPromotionPercent(double promotionPercent) {
		this.promotionPercent = promotionPercent;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "PromotionEntity [promotionId=" + promotionId + ", promotionName=" + promotionName
				+ ", promotionPercent=" + promotionPercent + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	

}

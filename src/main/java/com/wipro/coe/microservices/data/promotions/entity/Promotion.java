package com.wipro.coe.microservices.data.promotions.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.data.rest.core.annotation.RestResource;

@Table( value = "promotion")
public class Promotion implements java.io.Serializable{
	
	@PrimaryKey
	//@PrimaryKeyColumn (name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private Long id;
	
	@Column
	private String description;

	@Column
	//@PrimaryKeyColumn(name = "startdate", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
	private Date promotionStartDate;

	@Column
	//@PrimaryKeyColumn(name = "enddate", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
	private Date promotionEndDate;
	
	@Column
	private String promotionOwnerName;
	
	
	@Column
	private String lastAction;
	
	
	@Column
	private String lastActionBy;

//	@RestResource(exported = false)
//	@Column
//	private List<Offer> offers ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPromotionStartDate() {
		return promotionStartDate;
	}

	public void setPromotionStartDate(Date promotionStartDate) {
		this.promotionStartDate = promotionStartDate;
	}

	public Date getPromotionEndDate() {
		return promotionEndDate;
	}

	public void setPromotionEndDate(Date promotionEndDate) {
		this.promotionEndDate = promotionEndDate;
	}

	public String getPromotionOwnerName() {
		return promotionOwnerName;
	}

	public void setPromotionOwnerName(String promotionOwnerName) {
		this.promotionOwnerName = promotionOwnerName;
	}

	public String getLastAction() {
		return lastAction;
	}

	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}

	public String getLastActionBy() {
		return lastActionBy;
	}

	public void setLastActionBy(String lastActionBy) {
		this.lastActionBy = lastActionBy;
	}

//	public List<Offer> getOffers() {
//		return offers;
//	}
//
//	public void setOffers(List<Offer> offers) {
//		this.offers = offers;
//	}
	
	
	

}

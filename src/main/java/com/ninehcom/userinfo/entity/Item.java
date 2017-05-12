package com.ninehcom.userinfo.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ninehcom.common.util.DateTimeSerializer;
import com.ninehcom.userinfo.enums.ItemType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Item implements Serializable , Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long itemId;
	
	private String userId;
	
	//用户中心
	private String nhId;
	
	private String itemTypeKey;
	
	private Integer count;
	
	private ItemType itemType;
	
	private Integer isUsed;
	
	private Date usedAt;
	
	private Date invalidAt;
	
	private Integer isBind;
	
	private String relatedId;

	// 创建时间（记录）
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected  Date createdAt;

	// 更新时间（记录）
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")

	protected Date updatedAt;


	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	

	public String getNhId() {
		return nhId;
	}

	public void setNhId(String nhId) {
		this.nhId = nhId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemTypeKey() {
		return itemTypeKey;
	}

	public void setItemTypeKey(String itemTypeKey) {
		this.itemTypeKey = itemTypeKey;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using=DateTimeSerializer.class)
	public Date getUsedAt() {
		return usedAt;
	}

	public void setUsedAt(Date usedAt) {
		this.usedAt = usedAt;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using=DateTimeSerializer.class)
	public Date getInvalidAt() {
		return invalidAt;
	}

	public void setInvalidAt(Date invalidAt) {
		this.invalidAt = invalidAt;
	}

	public Integer getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	
	public String getName(){
		if (null == itemType) {
			return null;
		}
		return itemType.getName();
	}
	
	public String getIcon(){
		if (null == itemType) {
			return null;
		}
		return itemType.getIcon();
	}
	
	public String getDescription(){
		if (null == itemType) {
			return null;
		}
		return itemType.getDescription();
	}
	
	public String getLogo(){
		if (null == itemType) {
			return null;
		}
		return itemType.getLogo();
	}

	public Integer getIsBind() {
		return isBind;
	}

	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}
	
	public String toUserBadgeCardString() throws JsonProcessingException{
		Map map = this.toMap();						
		return new ObjectMapper().writeValueAsString(map);
	}
	
	public Map toMap(){
		Map map = new HashMap();
		if (null != this.getItemId()) {
			map.put("itemId", this.getItemId());
		}
		if (null != this.getUserId()) {
			map.put("userId", this.getUserId());
		}
		
		if (null != this.getItemTypeKey()) {
			map.put("itemTypeKey", this.getItemTypeKey());
		}
		
		if (null != this.getIsUsed()) {
			map.put("isUsed", this.getIsUsed());
		}
		
		if (null != this.getUsedAt()) {
			map.put("usedAt", this.getUsedAt());
		}
		
		if (null != this.getInvalidAt()) {
			map.put("invalidAt", this.getInvalidAt());
		}
		return map;
	}
	
	public Item clone() {
        Item o = null;
        try {  
            o = (Item) super.clone();
        } catch (CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return o;  
    }

	public String getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}  
	
	
	
	
}

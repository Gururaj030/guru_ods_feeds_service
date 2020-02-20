package com.guru.feeds.dto;

import java.util.Date;

public class ConfigurationDto {
	
	private Integer id;
	
	private String tenantId;
	
	private String dataSourceId;

	private Integer dataStoreId;

	private Date createdAt;

	private Date updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public Integer getDataStoreId() {
		return dataStoreId;
	}

	public void setDataStoreId(Integer dataStoreId) {
		this.dataStoreId = dataStoreId;
	}

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

	@Override
	public String toString() {
		return "ConfigurationDto [id=" + id + ", tenantId=" + tenantId + ", dataSourceId=" + dataSourceId
				+ ", dataStoreId=" + dataStoreId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}

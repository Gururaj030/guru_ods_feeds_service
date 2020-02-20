package com.guru.feeds.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Configuration {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank(message = "tenantId cannot be empty")
	private String tenantId;
	
	@NotBlank(message = "dataSourceId cannot be empty")
	private String dataSourceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "data_store_id")
	private DataStore dataStore;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdAt;

	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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

	public DataStore getDataStore() {
		return dataStore;
	}

	public void setDataStore(DataStore dataStoreId) {
		this.dataStore = dataStoreId;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
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
		return "Configuration [id=" + id + ", tenantId=" + tenantId + ", dataSourceId=" + dataSourceId + ", dataStore="
				+ dataStore + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}

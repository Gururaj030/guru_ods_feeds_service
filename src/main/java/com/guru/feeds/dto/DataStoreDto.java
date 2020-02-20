package com.guru.feeds.dto;

import java.util.Date;

public class DataStoreDto {
	private Integer id;

	private Tenant tenant;

	private Date createdAt;

	private Date updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
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
		return "DataStoreDto [id=" + id + ", tenant=" + tenant + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}
}
